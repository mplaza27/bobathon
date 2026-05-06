package com.ibm.ace.orderprocessing;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;

/**
 * TransformMessage - Java Compute Node for Order Processing
 * 
 * This compute node transforms incoming order messages from the HTTP input format
 * to the internal format required for downstream processing and MQ output.
 * 
 * Transformation includes:
 * - Field mapping and renaming
 * - Data type conversions
 * - Adding metadata (timestamps, processing info)
 * - Calculating derived fields (order total, tax, etc.)
 * 
 * @author Order Processing Team
 * @version 1.0
 */
public class TransformMessage extends MbJavaComputeNode {

    /**
     * Main evaluation method called by ACE runtime
     * 
     * @param inAssembly The input message assembly
     * @throws MbException If processing fails
     */
    public void evaluate(MbMessageAssembly inAssembly) throws MbException {
        MbOutputTerminal out = getOutputTerminal("out");
        MbOutputTerminal alt = getOutputTerminal("alternate");
        MbOutputTerminal failure = getOutputTerminal("failure");

        MbMessage inMessage = inAssembly.getMessage();
        MbMessageAssembly outAssembly = null;

        try {
            // Create new message for output
            MbMessage outMessage = new MbMessage(inMessage);
            outAssembly = new MbMessageAssembly(inAssembly, outMessage);

            // Get input data
            MbElement inputRoot = inMessage.getRootElement();
            MbElement inputBody = inputRoot.getLastChild().getFirstChild();

            // Create output structure
            MbElement outputRoot = outMessage.getRootElement();
            MbElement outputBody = outputRoot.createElementAsLastChild(MbElement.TYPE_NAME, "JSON", null);
            MbElement outputData = outputBody.createElementAsLastChild(MbElement.TYPE_NAME, "Data", null);
            MbElement order = outputData.createElementAsLastChild(MbElement.TYPE_NAME, "Order", null);

            // Transform order fields
            transformOrderFields(inputBody, order);

            // Add metadata
            addMetadata(order);

            // Calculate totals
            calculateOrderTotals(order);

            // Validate transformed message
            validateOrder(order);

            // Send to output terminal
            out.propagate(outAssembly);

        } catch (MbException e) {
            // Log error and propagate to failure terminal
            System.err.println("Error transforming message: " + e.getMessage());
            failure.propagate(outAssembly);
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("Unexpected error: " + e.getMessage());
            throw new MbUserException(this, "evaluate()", "", "", e.toString(), null);
        }
    }

    /**
     * Transform order fields from input to output format
     * 
     * @param inputBody Input message body
     * @param order Output order element
     * @throws MbException If field access fails
     */
    private void transformOrderFields(MbElement inputBody, MbElement order) throws MbException {
        // Map orderId
        MbElement orderId = inputBody.getFirstElementByPath("orderId");
        if (orderId != null) {
            order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "OrderID", orderId.getValueAsString());
        }

        // Map customerId
        MbElement customerId = inputBody.getFirstElementByPath("customerId");
        if (customerId != null) {
            order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "CustomerID", customerId.getValueAsString());
        }

        // Map orderAmount
        MbElement orderAmount = inputBody.getFirstElementByPath("orderAmount");
        if (orderAmount != null) {
            order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Amount", orderAmount.getValueAsDouble());
        }

        // Map orderDate
        MbElement orderDate = inputBody.getFirstElementByPath("orderDate");
        if (orderDate != null) {
            order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "OrderDate", orderDate.getValueAsString());
        }

        // Map items array
        MbElement items = inputBody.getFirstElementByPath("items");
        if (items != null) {
            MbElement outputItems = order.createElementAsLastChild(MbElement.TYPE_NAME, "Items", null);
            transformOrderItems(items, outputItems);
        }

        // Map shipping address
        MbElement shippingAddress = inputBody.getFirstElementByPath("shippingAddress");
        if (shippingAddress != null) {
            MbElement outputAddress = order.createElementAsLastChild(MbElement.TYPE_NAME, "ShippingAddress", null);
            transformAddress(shippingAddress, outputAddress);
        }
    }

    /**
     * Transform order items array
     * 
     * @param inputItems Input items element
     * @param outputItems Output items element
     * @throws MbException If processing fails
     */
    private void transformOrderItems(MbElement inputItems, MbElement outputItems) throws MbException {
        MbElement item = inputItems.getFirstChild();
        while (item != null) {
            MbElement outputItem = outputItems.createElementAsLastChild(MbElement.TYPE_NAME, "Item", null);
            
            // Map item fields
            MbElement itemId = item.getFirstElementByPath("itemId");
            if (itemId != null) {
                outputItem.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "ItemID", itemId.getValueAsString());
            }

            MbElement quantity = item.getFirstElementByPath("quantity");
            if (quantity != null) {
                outputItem.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Quantity", quantity.getValueAsInt());
            }

            MbElement price = item.getFirstElementByPath("price");
            if (price != null) {
                outputItem.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "UnitPrice", price.getValueAsDouble());
            }

            // Calculate line total
            if (quantity != null && price != null) {
                double lineTotal = quantity.getValueAsInt() * price.getValueAsDouble();
                outputItem.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "LineTotal", lineTotal);
            }

            item = item.getNextSibling();
        }
    }

    /**
     * Transform shipping address
     * 
     * @param inputAddress Input address element
     * @param outputAddress Output address element
     * @throws MbException If processing fails
     */
    private void transformAddress(MbElement inputAddress, MbElement outputAddress) throws MbException {
        String[] addressFields = {"street", "city", "state", "zipCode", "country"};
        String[] outputFields = {"Street", "City", "State", "ZipCode", "Country"};

        for (int i = 0; i < addressFields.length; i++) {
            MbElement field = inputAddress.getFirstElementByPath(addressFields[i]);
            if (field != null) {
                outputAddress.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, 
                    outputFields[i], field.getValueAsString());
            }
        }
    }

    /**
     * Add metadata to the order
     * 
     * @param order Order element
     * @throws MbException If processing fails
     */
    private void addMetadata(MbElement order) throws MbException {
        // Add processing timestamp
        order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, 
            "ProcessedTimestamp", System.currentTimeMillis());

        // Add processing system
        order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, 
            "ProcessedBy", "ACE-OrderProcessing-v1.0");

        // Add status
        order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, 
            "Status", "PROCESSED");
    }

    /**
     * Calculate order totals including tax and shipping
     * 
     * @param order Order element
     * @throws MbException If processing fails
     */
    private void calculateOrderTotals(MbElement order) throws MbException {
        // Get base amount
        MbElement amountElement = order.getFirstElementByPath("Amount");
        double amount = 0.0;
        if (amountElement != null) {
            amount = amountElement.getValueAsDouble();
        }

        // Calculate tax (8% for example)
        double tax = amount * 0.08;
        order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Tax", tax);

        // Calculate shipping (flat rate for example)
        double shipping = 9.99;
        order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Shipping", shipping);

        // Calculate total
        double total = amount + tax + shipping;
        order.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Total", total);
    }

    /**
     * Validate the transformed order
     * 
     * @param order Order element to validate
     * @throws MbException If validation fails
     */
    private void validateOrder(MbElement order) throws MbException {
        // Check required fields
        if (order.getFirstElementByPath("OrderID") == null) {
            throw new MbUserException(this, "validateOrder()", "", "", 
                "OrderID is required", null);
        }

        if (order.getFirstElementByPath("CustomerID") == null) {
            throw new MbUserException(this, "validateOrder()", "", "", 
                "CustomerID is required", null);
        }

        // Validate amount
        MbElement totalElement = order.getFirstElementByPath("Total");
        if (totalElement != null && totalElement.getValueAsDouble() <= 0) {
            throw new MbUserException(this, "validateOrder()", "", "", 
                "Order total must be greater than zero", null);
        }
    }
}

// Made with Bob
