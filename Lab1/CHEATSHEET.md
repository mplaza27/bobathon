# Bob Quick Reference Guide

## Essential Commands

### Navigation and Discovery

**List Files**
```
List all files in this workspace
List all Java files in this project
Show files in the [directory] directory
```

**Code Definitions**
```
Show me all code definitions in [file/directory]
List all classes and methods in [file]
What functions are defined in [file]?
```

**Search**
```
Search for "[keyword]" in all files
Find files containing "[text]"
Search for [pattern] in [directory]
```

**Project Structure**
```
What is the structure of this project?
Explain the purpose of each directory
What are the main components of this codebase?
```

---

## Code Analysis

### Reading and Understanding

**Explain Code**
```
Read [filename] and explain what it does
Explain the [method/class] in [filename]
What is the purpose of [code element]?
Summarize the main functionality of [file]
```

**Analyze Flow**
```
How does [feature/process] work in this code?
Trace the execution flow of [method]
What happens when [event] occurs?
```

**Dependencies**
```
What dependencies does [file] have?
How does [component A] interact with [component B]?
What files import or use [class/function]?
```

---

## Visualization

### Creating Diagrams

**Architecture Diagrams**
```
Create a Mermaid diagram showing the system architecture
Visualize how [component A] connects to [component B]
Generate a component diagram for this project
```

**Flow Diagrams**
```
Create a flowchart of the [process/method]
Show the message flow through the system
Diagram the data transformation pipeline
```

**Sequence Diagrams**
```
Create a sequence diagram for [workflow]
Show the interaction between [components]
Visualize the call sequence for [operation]
```

**Refining Diagrams**
```
Make this diagram more detailed
Simplify this diagram
Add [specific element] to the diagram
```

---

## Code Modification

### Making Changes

**Adding Features**
```
Add [feature] to [filename]
Implement [functionality] in [class/method]
Create a new [method/class] that [does X]
```

**Error Handling**
```
Add error handling to [method] in [filename]
Add try-catch blocks to handle [exception type]
Implement defensive programming in [method]
Add null checks to [method]
```

**Logging**
```
Add logging to [method] in [filename]
Add debug logging for [operation]
Implement structured logging in [class]
```

**Refactoring**
```
Refactor [method] to improve readability
Extract [code block] into a separate method
Simplify [complex logic]
Optimize [method] for performance
```

---

## Code Quality

### Analysis and Review

**Bug Detection**
```
Analyze [filename] for potential bugs
What could cause errors in [method]?
Find potential null pointer exceptions in [file]
Identify race conditions in [code]
```

**Security Review**
```
Review [filename] for security vulnerabilities
What are the security risks in [code]?
Check for SQL injection vulnerabilities
Analyze input validation in [method]
```

**Performance Analysis**
```
Review [filename] for performance issues
What are the performance bottlenecks in [method]?
Suggest optimizations for [code]
Analyze the time complexity of [algorithm]
```

**Best Practices**
```
Review [filename] for best practice violations
Does this code follow [language] conventions?
Suggest improvements for code quality
Check for code smells in [file]
```

---

## Documentation

### Generating Documentation

**Project Documentation**
```
Create a README for [directory/project]
Generate setup instructions for this project
Create a user guide for [feature]
Write deployment documentation
```

**Code Documentation**
```
Add JavaDoc/JSDoc comments to [filename]
Document all methods in [class]
Add inline comments explaining [complex logic]
Create API documentation for [module]
```

**Troubleshooting Guides**
```
Create a troubleshooting guide for [feature]
Document common errors and solutions
Generate FAQ for [component]
```

---

## Exercise-Specific Prompts

### Exercise 1: Navigation

```
List all files in this project organized by directory
Show me all code definitions in ace-project/JavaCompute
Search for files containing "error" or "exception"
What is the purpose of each directory in this project?
```

### Exercise 2: Visualization

```
Read MainFlow.msgflow and explain the message processing flow
Create a Mermaid diagram showing message flow from MQ through ACE to Java
Analyze ErrorHandling.subflow and create a flowchart
Create a diagram showing data transformation in TransformMessage.java
```

### Exercise 3: Code Analysis

```
Analyze TransformMessage.java for potential null pointer exceptions
Review error handling in TransformMessage.java
Add null checks and error handling to the evaluate() method
Add logging statements to TransformMessage.java
Review the changes and explain what was improved
```

### Exercise 4: Documentation

```
Create a comprehensive README for the ace-project directory
Add JavaDoc comments to all methods in TransformMessage.java
Perform a code review of TransformMessage.java
Create a troubleshooting guide for common issues
```

---

## Advanced Techniques

### Testing

```
Write unit tests for [class/method]
Generate test cases for [functionality]
Create mock objects for [dependency]
What edge cases should I test for [method]?
```

### Refactoring

```
Refactor this code to use [design pattern]
Convert this code to use [modern language features]
Extract this logic into separate methods
Simplify this conditional logic
```

### Optimization

```
Optimize [method] for better performance
Reduce memory usage in [code]
Improve the efficiency of [algorithm]
Suggest caching strategies for [operation]
```

### Architecture

```
Suggest architectural improvements for [component]
How would you restructure this code?
Recommend design patterns for [problem]
Evaluate the current architecture
```

---

## Best Practices

### Effective Prompting

**Be Specific**
- Include file names and paths
- Reference specific methods or classes
- Describe the desired outcome clearly
- Provide relevant context

**Iterate**
- Start with broad questions
- Ask follow-up questions to refine
- Request alternatives if needed
- Build on previous responses

**Provide Context**
- Explain what you're trying to achieve
- Include error messages when debugging
- Mention constraints or requirements
- Reference related code or documentation

### Working with Bob

**Review Changes**
- Always review code modifications
- Understand why changes were made
- Test in your environment
- You're responsible for the final code

**Ask for Explanations**
```
Why did you choose this approach?
Explain the reasoning behind this change
What are the trade-offs of this solution?
Are there alternative approaches?
```

**Verify Understanding**
```
Summarize what this code does
Explain this in simpler terms
What are the key concepts here?
How does this relate to [other concept]?
```

---

## Common Use Cases

### Onboarding to New Code

1. List project structure
2. Identify main components
3. Understand key workflows
4. Review architecture diagrams
5. Read critical code sections

### Debugging Issues

1. Analyze error messages
2. Review relevant code
3. Identify potential causes
4. Suggest fixes
5. Add defensive programming

### Code Review

1. Check for bugs and issues
2. Review security concerns
3. Assess performance
4. Verify best practices
5. Suggest improvements

### Documentation

1. Generate project README
2. Add inline code comments
3. Create API documentation
4. Write troubleshooting guides
5. Document architecture

---

## Troubleshooting Bob

### Bob Not Responding

- Check internet connection
- Verify Bob extension is enabled
- Restart VS Code
- Check Bob service status

### Unclear Responses

- Be more specific in your prompt
- Provide additional context
- Break complex requests into steps
- Ask follow-up questions
- Try rephrasing

### Incorrect Suggestions

- Verify Bob understood your request
- Provide more context
- Ask Bob to explain its reasoning
- Request alternative approaches
- Always review and test suggestions

---

## Quick Tips

- **Start simple**: Begin with basic prompts and refine
- **Be patient**: Complex requests may take time
- **Iterate**: Build on responses with follow-ups
- **Verify**: Always review and test Bob's suggestions
- **Learn**: Ask Bob to explain its reasoning
- **Document**: Save useful prompts for reuse
- **Share**: Help teammates learn effective prompts

---

## Additional Resources

- Bob documentation
- Mermaid diagram syntax
- Language-specific best practices
- Code review checklists

---

**Remember**: Bob is a tool to enhance your productivity. Use it to handle routine tasks so you can focus on complex problem-solving and creative work.