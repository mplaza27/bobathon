# DISH SDLC Custom Mode Setup Guide

This guide provides step-by-step instructions for creating and configuring the **DISH SDLC** custom mode in Bob, which integrates Jira and SonarQube for automated software development lifecycle workflows.

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Architecture](#architecture)
- [Step 1: Environment Configuration](#step-1-environment-configuration)
- [Step 2: MCP Server Configuration](#step-2-mcp-server-configuration)
- [Step 3: Custom Mode Definition](#step-3-custom-mode-definition)
- [Step 4: Rule Files Setup](#step-4-rule-files-setup)
- [Step 5: Verification](#step-5-verification)
- [Troubleshooting](#troubleshooting)
- [Usage Examples](#usage-examples)

---

## Overview

The **Smart SDLC** custom mode automates development workflows by:

- **Jira Integration**: Fetching user stories, updating issues, managing workflow states
- **SonarQube Integration**: Running code quality analysis, identifying issues, ensuring quality standards
- **Automated Development**: Implementing features based on Jira acceptance criteria
- **Quality Assurance**: Comprehensive testing and quality gate validation

### Key Features

✅ Automated Jira story fetching and parsing  
✅ Code implementation based on acceptance criteria  
✅ Integrated SonarQube quality analysis if SonarQube is configured 
✅ Automated testing and validation  
✅ Comprehensive reporting and documentation  
✅ Workflow state management  

---

## Prerequisites

### Required Tools

- **IBM Bob** (AI-assisted IDE)
- **Jira Account** with API access
- **SonarQube Instance** (optional, for quality analysis)
- **MCP Servers** for Jira and SonarQube

### Required Permissions

- Jira: Browse projects, edit issues, add comments, transition issues
- SonarQube: Browse projects, execute analysis (if using quality features)

---

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                         Bob AI                              │
│                    (AI Assisted IDE)                        │
└────────────────┬────────────────────────────────────────────┘
                 │
                 │ Uses Custom Mode
                 │
┌────────────────▼────────────────────────────────────────────┐
│                    Smart SDLC Mode                           │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  Custom Instructions + Rule Files                    │   │
│  │  - Workflow definitions                              │   │
│  │  - Jira integration rules                            │   │
│  │  - SonarQube integration rules                       │   │
│  │  - Best practices                                    │   │
│  └──────────────────────────────────────────────────────┘   │
└────────────┬───────────────────────────┬────────────────────┘
             │                           │
             │ MCP Protocol              │ MCP Protocol
             │                           │
┌────────────▼──────────┐   ┌───────────▼────────────┐
│   Jira MCP Server     │   │ SonarQube MCP Server   │
│   (SSE Transport)     │   │   (SSE Transport)      │
└────────────┬──────────┘   └───────────┬────────────┘
             │                           │
             │ REST API                  │ REST API
             │                           │
┌────────────▼──────────┐   ┌───────────▼────────────┐
│   Jira Instance       │   │  SonarQube Instance    │
│   (Atlassian Cloud)   │   │   (Self-hosted/Cloud)  │
└───────────────────────┘   └────────────────────────┘
```

---

## Step 1: MCP Server Configuration

### Step 1.1: Copy the MCP config template:

```
cp .bob/mcp.json.example .bob/mcp.json
```

### Step 1.2: Open .bob/mcp.json and replace the placeholder API keys with the values provided by your lab instructor:

- `YOUR_JIRA_MCP_API_KEY`
- `YOUR_SONARQUBE_MCP_API_KEY`
- `YOUR_OCP_MCP_API_KEY`

### Step 1.3: Ensure MCP Server Requirements:

**Jira MCP Server:**
- Must support SSE (Server-Sent Events) transport
- Should handle authentication via environment variables
- Required tools: `get_issue`, `list_issues`, `add_comment`, `transition_issue`, `update_issue`

**SonarQube MCP Server:**
- Must support SSE transport
- Should accept authentication token
- Required tools: `list_projects`, `get_project_metrics`, `get_issues`, `get_quality_gate`

---

## Step 2: Custom Mode Definition

### Step 2.1: Create `.bob/custom_modes.yaml`

```yaml
{
  "customModes": [
    {
      "slug": "smart-sdlc",
      "name": "🔄 Smart SDLC",
      "roleDefinition": "You are Bob, a comprehensive Software Development Lifecycle (SDLC) automation specialist for all java projects with integrated code quality analysis capabilities. Your expertise includes:\n\n**SDLC Automation:**\n- Reading and interpreting Jira user stories and requirements\n- Analyzing legacy Java applications and identifying modernization opportunities\n- Implementing code changes based on user story acceptance criteria\n- Following enterprise development best practices\n- Automating development workflows from story to implementation\n- Documenting implementation decisions\n\n**Code Quality Analysis (optional with SonarQube if available else with IBM Bob):**\n- Performing comprehensive static code analysis\n- Analyzing code quality metrics (bugs, vulnerabilities, code smells, security hotspots)\n- Interpreting quality gate results and providing actionable recommendations\n- Identifying technical debt and suggesting remediation strategies\n- Generating detailed quality reports with severity classifications\n- Tracking quality metrics over time and identifying trends\n- Ensuring code meets enterprise quality standards\n\nYou help teams by:\n- Fetching user stories from Jira (Project: KAN)\n- Understanding acceptance criteria and technical requirements\n- Analyzing existing codebase structure\n- Implementing required changes following best practices\n- Running SonarQube scans and code quality checks\n- Retrieving and analyzing project quality metrics\n- Identifying critical issues (bugs, vulnerabilities, security hotspots)\n- Providing detailed analysis of code smells and maintainability issues\n- Generating comprehensive quality reports\n- Suggesting prioritized remediation actions\n- Monitoring quality gate status and compliance\n- Ensuring changes meet story requirements and quality standards",
      "whenToUse": "Use this mode when you need to automate development tasks based on Jira user stories with integrated code quality analysis. This mode is ideal for:\n\n**Development Automation:**\n- Reading user stories from Jira board (Project KAN)\n- Implementing features described in user stories\n- Modernizing legacy Java applications\n- Following SDLC workflows from requirements to implementation\n- Automating repetitive development tasks based on story requirements\n\n**Code Quality Analysis:**\n- Running static code analysis on Java, JavaScript, Python, or other supported projects\n- Analyzing code quality metrics and identifying issues\n- Generating quality reports for code reviews\n- Checking quality gate compliance\n- Identifying bugs, vulnerabilities, and security hotspots\n- Tracking technical debt and code smells\n- Ensuring code meets quality standards before deployment\n- Performing post-development quality checks\n\n**Integrated Workflow:**\n- Complete SDLC automation from Jira story to quality-verified implementation\n- Automated quality checks as part of the development workflow\n- Comprehensive reporting combining functional and quality metrics",
      "groups": [
        "read",
        "edit",
        "command",
        "mcp"
      ],
      "customInstructions": "# Smart SDLC Workflow with Integrated Quality Analysis\n\n## Development Workflow\n1. Fetch and analyze Jira user stories from Project KAN\n2. Understand acceptance criteria and technical requirements\n3. Analyze existing codebase structure\n4. Implement required changes following best practices\n5. Run comprehensive quality checks\n6. Document implementation and quality results\n7. Update Jira with completion status and quality metrics\n\n## SonarQube Quality Analysis Guidelines\nWhen performing SonarQube analysis:\n1. Use the SonarQube MCP server to interact with SonarQube API\n2. Always provide project key when running scans\n3. Analyze results by severity: BLOCKER > CRITICAL > MAJOR > MINOR > INFO\n4. Focus on actionable recommendations with code examples\n5. Prioritize security vulnerabilities and bugs over code smells\n6. Include quality gate status in reports\n7. Provide clear remediation steps for identified issues\n8. Use metrics like: reliability rating, security rating, maintainability rating, coverage, duplications\n9. Generate summary reports with key findings and recommendations\n\n## Quality Standards\n- All code must pass quality gate before marking stories complete\n- Critical and blocker issues must be addressed immediately\n- Security vulnerabilities take highest priority\n- Maintain or improve code coverage with each change\n- Document any technical debt introduced with remediation plan\n\n## Reporting\nAlways include in completion reports:\n- Functional implementation summary\n- Quality metrics (bugs, vulnerabilities, code smells, coverage)\n- Quality gate status\n- Any critical issues identified and resolved\n- Recommendations for future improvements"
    }
  ]
}
```

### Step 2.2: Mode Configuration Explained

**Key Components:**

- **slug**: Unique identifier for the mode (`smart-sdlc`)
- **name**: Display name with emoji (`🔄 Smart SDLC`)
- **roleDefinition**: Defines Bob's expertise and capabilities
- **whenToUse**: Describes scenarios where this mode is appropriate
- **groups**: Permissions granted to the mode
  - `read`: File reading capabilities
  - `edit`: File editing capabilities
  - `command`: Terminal command execution
  - `mcp`: MCP server tool usage
- **customInstructions**: Specific workflow and quality guidelines

---

## Step 3: Rule Files Setup

### Step 3.1: Create Rule Files Directory

```bash
mkdir -p .bob/rules-smart-sdlc
```

### Step 3.2: Create Rule Files

Create the following XML files in `.bob/rules-smart-sdlc/`:

#### 1_workflow.xml
Defines the overall SDLC workflow phases and steps.

#### 2_jira_integration.xml
Provides detailed Jira integration instructions, including:
- Fetching issues
- Parsing acceptance criteria
- Updating issue status
- Adding comments
- Error handling

#### 3_best_practices.xml
Documents coding standards and best practices.

#### 4_examples.xml
Provides example workflows and use cases.

#### 5_deployment.xml
Provides direction on how to deploye and launch the application or website.

**Note**: The complete XML content for these files is available in the `.bob/rules-smart-sdlc/` directory of this project.

---

## Step 4: Verification

### Step 4.1: Verify File Structure

Ensure your `.bob` directory has this structure:

```
.bob/
├── README.md                    # This file
├── custom_modes.yaml            # Mode definition
├── mcp.json                     # MCP server configuration
└── rules-dish-sdlc/            # Rule files directory
    ├── 1_workflow.xml
    ├── 2_jira_integration.xml
    ├── 3_sonarqube_integration.xml
    ├── 4_best_practices.xml
    └── 5_examples.xml
```

### Step 4.2: Test Jira Connection

1. Open Bob
2. Switch to DISH SDLC mode
3. Ask: "List issues from Jira project KAN"
4. Verify Bob can fetch and display issues

### Step 4.3: Test SonarQube Connection (Optional)

1. Ensure SonarQube token has proper permissions (USER TOKEN, not GLOBAL_ANALYSIS_TOKEN)
2. Ask: "List SonarQube projects"
3. Verify Bob can access SonarQube API

### Step 4.4: Test Complete Workflow

1. Ask: "Work on SCRUM" (replace with actual issue key)
2. Verify Bob:
   - Fetches the issue from Jira
   - Parses acceptance criteria
   - Analyzes codebase
   - Implements changes
   - Runs tests
   - Updates Jira

---

## Troubleshooting

### Issue: Jira 401 Authentication Error

**Symptoms**: Unable to connect to Jira, 401 errors

**Solutions**:
1. Verify API token is valid and not expired
2. Check email address is correct in `.env`
3. Ensure MCP server URL is accessible
4. Test token with curl:
   ```bash
   curl -u your-email@example.com:your-api-token \
     https://your-instance.atlassian.net/rest/api/3/myself
   ```

### Issue: SonarQube 403 Forbidden Error

**Symptoms**: SonarQube returns 403 when trying to list projects

**Root Cause**: Token type mismatch

**Solution**:
1. Current token is likely a GLOBAL_ANALYSIS_TOKEN (only for CI/CD)
2. Generate a new **USER TOKEN** with these permissions:
   - Browse projects
   - Execute analysis
   - View quality gates
   - View issues
3. Update `SONARQUBE_TOKEN` in `.env`
4. Restart Bob to reload environment variables

**Verify Token Type**:
```bash
curl -u your-token: \
  https://your-sonarqube-url/api/user_tokens/search
```

Look for `"type": "USER_TOKEN"` (not `"GLOBAL_ANALYSIS_TOKEN"`)

### Issue: MCP Server Not Found

**Symptoms**: Bob cannot find MCP server

**Solutions**:
1. Verify `mcp.json` exists in `.bob/` directory
2. Check MCP server URLs are correct
3. Ensure environment variables are loaded
4. Restart Bob

### Issue: Custom Mode Not Appearing

**Symptoms**: DISH SDLC mode not visible in Bob's mode selector

**Solutions**:
1. Verify `custom_modes.yaml` syntax is correct (valid YAML/JSON)
2. Check file is in `.bob/` directory
3. Restart Bob
4. Check Bob extension logs for errors

### Issue: Rule Files Not Loading

**Symptoms**: Bob doesn't follow DISH SDLC workflow

**Solutions**:
1. Verify all XML files are in `.bob/rules-smart-sdlc/`
2. Check XML syntax is valid
3. Ensure file names match exactly (case-sensitive)
4. Restart Bob

---

## Usage Examples

### Example 1: Implement a Jira Story

```
User: Work on KAN-2

Bob: [Fetches issue KAN-2 from Jira]
     [Displays summary and acceptance criteria]
     [Analyzes codebase]
     [Implements changes]
     [Runs tests]
     [Updates Jira with completion status]
```

### Example 2: Run Quality Analysis

```
User: Run SonarQube analysis on the project

Bob: [Connects to SonarQube MCP server]
     [Triggers analysis]
     [Retrieves quality metrics]
     [Reports findings with severity breakdown]
     [Suggests remediation actions]
```

### Example 3: Complete SDLC Workflow

```
User: Implement the feature in KAN-5 and ensure it meets quality standards

Bob: [Fetches KAN-5 from Jira]
     [Implements feature based on acceptance criteria]
     [Runs unit tests]
     [Performs SonarQube analysis]
     [Addresses quality issues]
     [Verifies quality gate passes]
     [Updates Jira with implementation and quality report]
```

---

## Advanced Configuration
### Multiple Jira Projects

To support multiple projects, update `.env`:

```bash
JIRA_PROJECT_KEY=KAN,PROJ2,PROJ3
```

And modify Jira integration rules to handle multiple projects.

---

## Security Best Practices

1. **Never commit `.env` file** - Add to `.gitignore`
2. **Rotate tokens regularly** - Update every 90 days
3. **Use least privilege** - Grant only necessary permissions
4. **Secure token storage** - Use environment variables, not hardcoded values
5. **Monitor access logs** - Review Jira and SonarQube audit logs

---

## Support and Resources

### Documentation
- [Bob AI Documentation](https://docs.bob.build)
- [Jira REST API](https://developer.atlassian.com/cloud/jira/platform/rest/v3/)
- [SonarQube Web API](https://docs.sonarqube.org/latest/extend/web-api/)
- [MCP Protocol](https://modelcontextprotocol.io/)

### Community
- Bob AI Discord/Slack
- DISH Network Developer Portal
- Internal Wiki/Confluence

---

## Changelog

### Version 1.0.0 (2026-03-24)
- Initial release
- Jira integration with MCP
- SonarQube integration with MCP
- Complete SDLC workflow automation
- Comprehensive rule files
- Quality analysis and reporting

---

## License

Internal use only - DISH Network

---

## Contributors

- Bob AI Team
- DISH Network Development Team

---

**Made with Bob** 🤖
