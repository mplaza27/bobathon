## Custom ACE Flow Lab Exercise

Welcome to creating your very own custom ACE workflow! In this lab, you'll create your own custom Bob Mode from scratch, and then you'll perform some modifications on it and perform a security scan as well. All you'll have to do is instruct Bob on what to do, and he'll do the heavy lifting!
Please reference the steps below to get started.

### Section 0: Prerequisites
- Bob installed and logged in
- Open Bob in the project directory "Custom-ACE-Flow"
  - All you should see is this Readme file and the example_prompts.txt

### Section 1: Creating your First Custom ACE Flow

#### Command 1.1: Create your Custom ACE Flow

Let's get started building your own custom ACE flow! You can put Bob to work from scratch, by starting with the example prompt below. Please open a new Bob chat and copy/paste this prompt to get started.

```text
Prompt #1: Create ACE Flow

Please construct a minimal, demo-ready IBM App Connect Enterprise environment here in this folder. Please create the simplest possible ACE workflow that proves ACE can receive an HTTP request, transform/enrich the message, and return a clean JSON response, to meet the example request below.

Input JSON:
{
  "customerId": "123",
  "customerName": "Acme Corp",
  "requestType": "status"
}

Return JSON:
{
  "customerId": "123",
  "customerName": "Acme Corp",
  "requestType": "status",
  "status": "Processed by IBM App Connect Enterprise",
  "timestamp": "<current timestamp>"
}

Keep this intentionally minimal. Do not add MQ, databases, Kafka, authentication, CI/CD, or complex integrations unless absolutely necessary. Prioritize a clean local/containerized demo that can be shown in 5 minutes.
```


You'll see Bob iteratively walk you through several steps, where it may ask you for your preferences when creating your own ACE workflow. For the sake of time in this lab, just instruct Bob to construct assets for you ACE Flow. This can be assets such as:
 - ESQL files
 - YAML files
 - BAR file
 - msgflow files
 - Dockerfile
 - Quick start guide:
   - Instructions for integrating into ACE Toolkit
   - Instructions for standalone deployment
 - Architecture diagram


After a few minutes, Bob should finish creating these files. Then you're ready to try running a few of your own commands to modify and test your custom ACE flow. Let's try a few here.


  
#### Command 1.2: Modifying the API Endpoint
Please copy/paste this command below into your Bob Terminal:
```text
Now please modify my API endpoint to be /requests/outgoing/custom1
```

Bob will now edit your message flow file and also the test api to perform this change. Observe how Bob asks you for permissions, makes it clear what it will want to change, and let's you actively intervene at any point. It may even update all user manuals and guides to reflect these changes. At the end, Bob should check to ensure all proper changes were made across your application.

#### Command 1.3: Changing the Deployment Method
To make another modification, this time to say your deployment method, you can try running this command to the Bob chat:
```text
Now please change the deployment method to Podman instead of Docker
```

You should see Bob now making the necessary file modifications to switch the deployment method to Podman if it isn't already using Podman. If Docker was previously used, you'll see Bob delete any old Docker files to clean up your repository. You should also see Bob change any downstream files depending on this deployment method, such as the setup instructions and quick start guide. Bob should verify that the proper modifications were made also.

#### Command 1.4: Adding Additional Security Layer
Now let's investigate this newly-constructed ACE flow from a security perpsective. Since this repository has just been created, it likely may have some exposed vulnerabilities. Try running the command below to see if there are any key vulnerabilities worth noting:

```text
Please now run a preliminary vulnerability scan and let me know if any key security issues are apparent with this flow
```

Bob will then generate a complete security vulnerability analysis report. To view it, simply hit Cntrl + Shift + V. This report will present key security vunerabilities to you, breaking them down by severity level and informing you how crucial they are to address. It will likely list key issues such as the lack of proper authorization and encryption first, alerting you that these issues are critical and need to be addressed immediately. This analysis will not only inform you what they are and how serious they are; it will diagnose where the risks are coming from as well as specific steps and commands you can take to fix them. You can ask Bob for further clarifications here if you have any questions or want help implementing these vulnerabilities.

