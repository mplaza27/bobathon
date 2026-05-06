# Lab Setup Guide

## Prerequisites

Before starting these labs, ensure you have:

- VS Code installed
- Bob extension installed and configured
- Git installed
- Access to the lab repository
- Internet connection (required for Bob)

---

## Setup Instructions

### Step 1: Clone the Repository

```bash
git clone Costco-Bob-Labs
cd Costco-Bob-Labs
```

### Step 2: Open BOB

```bash
bobide .
```

Alternatively, open BOB and use `File > Open Folder` to navigate to the `Costco-Bob-Labs` directory.

### Step 3: Verify Bob Installation

1. Open Bob
   - Click the Bob icon in the sidebar

2. Test Bob with this prompt:
   ```
   List all files in this workspace
   ```

3. Expected result: Bob should display the project structure including:
   - 

If you see the file listing, your setup is complete and you're ready to begin the lab.


## Troubleshooting

### Bob Not Responding

**Symptoms**: Bob doesn't respond to prompts or shows connection errors

**Solutions**:
1. Verify your internet connection is active
2. Restart VS Code
3. If issues persist, check with your instructor

### Repository Clone Fails

**Symptoms**: `git clone` command fails with authentication error

**Solutions**:
1. Verify you have access to the repository
2. Check your SSH key configuration:
   ```bash
   ssh -T git@github.ibm.com
   ```
3. If using HTTPS, ensure you have a valid personal access token
4. Contact your instructor if access issues persist

### Bob Gives Unexpected Results

**Symptoms**: Bob's responses don't match your expectations

**Solutions**:
1. Review your prompt for clarity and specificity
2. Provide more context (file names, what you're trying to accomplish)
3. Ask follow-up questions to clarify Bob's response
4. Try rephrasing your question

### Files Not Found

**Symptoms**: Bob reports that files don't exist

**Solutions**:
1. Verify you're in the correct directory (`Costco-Bob-Labs`)
2. Check file paths are correct (case-sensitive on some systems)
3. Ensure the repository cloned completely
4. Try refreshing VS Code's file explorer

## Ready to Begin?

Navigate to the [README](./README.md) and continue to start the lab.

---

## Feedback

Your feedback helps improve this lab for future participants. After completing the lab, please share:
- What worked well
- What could be improved
- Suggestions for additional content
- Overall experience rating

Contact your instructor or submit feedback through your organization's channels.

---
