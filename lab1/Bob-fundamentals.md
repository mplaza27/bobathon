# Lab 1: Bob Fundamentals

**Duration:** 45 minutes  
**Objective:** Learn to use Bob as an AI-powered coding assistant for everyday development tasks

## Learning Objectives

By completing this lab, you will be able to:
- Navigate and explore unfamiliar codebases efficiently using Bob
- Generate visual diagrams to understand system architecture
- Analyze code for potential issues and improvements
- Make code modifications with AI assistance
- Create comprehensive documentation automatically

## Prerequisites

- BOB installed
- Basic familiarity with VS Code
- Access to this repository

## Setup

### Project Context


Open up lab 1 from the unzipped file into your bob IDE ‼️

This repository contains a sample message processing system using:
- **IBM MQ** for message queuing
- **ACE (App Connect Enterprise)** for message flows
- **Java** for message transformation logic

The technical details aren't important for this lab - we're focusing on learning Bob's capabilities ‼️.

---

## Exercise 1: Code Navigation and Discovery

### Objective
Learn how to efficiently explore and understand a codebase using Bob's navigation tools.

### Background
When joining a new project or working with unfamiliar code, you need to quickly understand the structure and locate relevant files. Bob can help you navigate without manually browsing directories.

### Tasks

**1.1 Explore the Project Structure**

Prompt:
```
List all files in this project organized by directory
```

Expected outcome: You'll see the complete file structure including ace-project, mq-config, and documentation files.

**1.2 Identify Code Components**

Prompt:
```
Show me all code definitions (classes, methods, functions) in the ace-project/JavaCompute directory
```

Expected outcome: Bob will list all classes and methods in the Java files.

**1.3 Search for Specific Functionality**

Prompt:
```
Search for files containing "error" or "exception" handling
```

Expected outcome: Bob will find files that implement error handling logic.

**1.4 Understand Directory Purpose**

Prompt:
```
What is the purpose of each directory in this project? Explain the ace-project and mq-config directories.
```

Expected outcome: Bob will explain what each directory contains and its role in the system.

### Key Takeaways
- Bob can quickly map out project structure
- You can search across files without opening them
- Code definitions help you understand what's implemented
- Natural language queries work for navigation

---

## Exercise 2: Visual Architecture Mapping

### Objective
Use Bob to create visual representations of system architecture and data flows.

### Background
Understanding how components interact is crucial for working with integration systems. Bob can generate Mermaid diagrams to visualize architecture, making complex systems easier to understand.

### Tasks

**2.1 Understand the Message Flow**

Prompt:
```
Read the ace-project/MessageFlows/MainFlow.msgflow file and explain the message processing flow step by step
```

Expected outcome: Bob will describe how messages move through the system.

**2.2 Create an Architecture Diagram**

Prompt:
```
Create a Mermaid diagram showing how messages flow from MQ through ACE to the Java transformation component and back
```

Expected outcome: Bob will generate a flowchart showing the complete message path.

**2.3 Visualize Error Handling**

Prompt:
```
Analyze the ErrorHandling.subflow file and create a Mermaid flowchart showing the error handling process
```

Expected outcome: A diagram showing error detection, logging, and recovery paths.

**2.4 Document Data Transformation**

Prompt:
```
Read TransformMessage.java and create a diagram showing the data transformation logic - what comes in, what processing occurs, and what goes out
```

Expected outcome: A visual representation of the transformation pipeline.

### Key Takeaways
- Bob can generate Mermaid diagrams for documentation
- Visual representations help communicate architecture
- Diagrams can be embedded in documentation or presentations
- You can iterate on diagrams by asking Bob to refine them

---

## Exercise 3: Code Analysis and Improvement

### Objective
Learn to use Bob for code review, bug detection, and implementing improvements.

### Background
Production code needs proper error handling, logging, and defensive programming. Bob can analyze code for potential issues and help implement best practices.

### Tasks

**3.1 Analyze for Potential Issues**

Prompt:
```
Analyze the TransformMessage.java file for potential null pointer exceptions, missing error handling, and other code quality issues
```

Expected outcome: Bob will identify areas where the code could fail or needs improvement.

**3.2 Review Error Handling**

Prompt:
```
Review the error handling in TransformMessage.java. Is it sufficient? What's missing?
```

Expected outcome: Bob will assess current error handling and suggest improvements.

**3.3 Add Defensive Programming**

Prompt:
```
Add null checks and proper error handling to the evaluate() method in TransformMessage.java
```

Expected outcome: Bob will modify the code to include null checks and try-catch blocks.

**3.4 Implement Logging**

Prompt:
```
Add appropriate logging statements to TransformMessage.java for debugging and monitoring purposes
```

Expected outcome: Bob will add logging at key points (method entry, exit, errors).

**3.5 Validate Changes**

Prompt:
```
Review the changes made to TransformMessage.java and explain what was improved and why
```

Expected outcome: Bob will summarize the improvements and their benefits.

### Key Takeaways
- Bob can identify code quality issues proactively
- AI assistance helps implement best practices consistently
- You should always review and understand Bob's changes
- Iterative refinement produces better results

---

## Exercise 4: Documentation Generation

### Objective
Use Bob to create comprehensive documentation for code and projects.

### Background
Good documentation is essential but time-consuming to write. Bob can generate professional documentation based on code analysis, saving significant time while maintaining quality.

### Tasks

**4.1 Create Project Documentation**

Prompt:
```
Create a comprehensive README.md for the ace-project directory that includes:
- Project overview
- Architecture description
- Setup instructions
- How to run and test
- Common troubleshooting steps
```

Expected outcome: A complete README file suitable for team members.

**4.2 Add Code Documentation**

Prompt:
```
Add JavaDoc comments to all public methods in TransformMessage.java, including parameter descriptions and return values
```

Expected outcome: Professional inline documentation for the Java class.

**4.3 Generate Code Review**

Prompt:
```
Perform a comprehensive code review of TransformMessage.java covering:
- Code quality and readability
- Security considerations
- Performance optimization opportunities
- Best practice adherence
```

Expected outcome: A detailed review with specific recommendations.

**4.4 Create Troubleshooting Guide**

Prompt:
```
Based on the error handling in this project, create a troubleshooting guide for common issues developers might encounter
```

Expected outcome: A practical guide for debugging common problems.

**4.5 Optional Work**
  - If finished early go ahead and be creative with prompts to learn more about bob and the repository.

### Key Takeaways
- Bob can generate multiple types of documentation
- Documentation quality is professional and comprehensive
- You can specify the format and content you need
- Generated docs should be reviewed and customized as needed

---

## Lab Summary

### What You Learned

In this lab, you practiced using Bob for:

1. **Navigation** - Exploring codebases efficiently without manual browsing
2. **Visualization** - Creating diagrams to understand and communicate architecture
3. **Analysis** - Identifying code issues and implementing improvements
4. **Documentation** - Generating comprehensive project and code documentation

### Bob's Core Capabilities

| Capability | What You Did | Real-World Application |
|------------|--------------|------------------------|
| File Navigation | Listed files, searched content | Quickly orient in new projects |
| Code Analysis | Read and explained code | Understand unfamiliar code |
| Visualization | Created Mermaid diagrams | Document architecture |
| Code Modification | Added error handling, logging | Implement improvements |
| Documentation | Generated README, JavaDoc | Onboard team members |
| Code Review | Analyzed quality and security | Maintain code standards |

### Time Efficiency

Consider the time savings Bob provides:

- **Code Navigation**: 30 minutes → 2 minutes (93% faster)
- **Creating Diagrams**: 2 hours → 5 minutes (96% faster)
- **Code Analysis**: 1 hour → 10 minutes (83% faster)
- **Writing Documentation**: 3 hours → 15 minutes (92% faster)

### Best Practices for Using Bob

**Be Specific**
- Instead of: "Add logging"
- Try: "Add logging to the evaluate() method in TransformMessage.java with INFO level for entry/exit and ERROR level for exceptions"

**Iterate and Refine**
- Start with a broad question
- Ask follow-up questions to clarify
- Request alternatives if the first answer isn't ideal
- Build on previous responses

**Always Verify**
- Review all code changes before accepting
- Test modifications in your environment
- Understand why Bob made specific choices
- You're responsible for the final code

**Provide Context**
- Reference specific files and methods
- Explain what you're trying to achieve
- Include error messages when debugging
- Mention relevant constraints or requirements

---

## Next Steps

### Continue Learning

**Lab 2**
- More Advanced Bob features

**Try Bob on Your Own Projects**
- Apply these techniques to your actual work
- Experiment with different types of prompts
- Find workflows that work best for you

**Explore Advanced Features**
```
Refactor this code to use modern Java features
Write unit tests for the TransformMessage class
Optimize this method for better performance
Suggest design pattern improvements
```

**Share Knowledge**
- Document useful prompts for your team
- Share time-saving workflows you discover
- Help colleagues learn Bob effectively

### Additional Resources

- Bob documentation and best practices
- ACE/MQ integration patterns
- Mermaid diagram syntax reference
- Code review checklists

---

## Troubleshooting

### Bob Not Responding
- Verify internet connection
- Restart BOB if needed
- Ensure you're signed into Bob

### Unclear or Incorrect Responses
- Provide more specific context
- Break complex requests into smaller steps
- Ask Bob to explain its reasoning
- Try rephrasing your question

### Undoing Changes
- Use undo (Cmd+Z / Ctrl+Z)
- Review changes before accepting them
- Check git diff to see modifications
- Bob shows changes before applying - review carefully

---

## Feedback

We'd love to hear about your experience with this lab:
- What worked well?
- What could be improved?
- What additional Bob features would you like to learn?

Contact your instructor or submit feedback through your organization's channels.

---

**Lab Complete!** You now have practical experience using Bob as your AI coding assistant.
