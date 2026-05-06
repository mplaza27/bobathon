# .bob — Bob Configuration Directory

This directory contains everything Bob needs to operate as your AI-powered development partner in this lab. It includes custom mode definitions, MCP server credentials, deployment credentials, and the workflow rules that govern Bob's behaviour.

---

## Directory Structure

```
.bob/
├── README.md                      # This file
├── Custom_mode.md                 # Step-by-step guide: how the Dish SDLC custom mode was built
├── custom_modes.yaml              # Custom mode definitions loaded by Bob on startup
├── mcp.json                       # Your MCP API keys (pre-populated, gitignored)
├── mcp.json.example               # Template showing the expected mcp.json structure
├── gitea.json                     # Your Gitea credentials for pushing code (pre-populated, gitignored)
├── gitea.json.example             # Template showing the expected gitea.json structure
├── commands/
│   └── load-skills.md             # Defines the /load-skills command for discovering Agent Skills
└── rules-dish-sdlc/               # Rule files that encode the Dish SDLC workflow
    ├── 1_workflow.xml             # End-to-end SDLC workflow steps
    ├── 2_jira_integration.xml     # Jira story fetch, update, and transition rules
    ├── 3_sonarqube_integration.xml # SonarQube quality gate and analysis rules
    ├── 4_best_practices.xml       # Java modernization and code quality guidelines
    ├── 5_examples.xml             # Worked examples for common patterns
    └── 6_ocp_deployment.xml       # OpenShift deployment rules, Gitea push, Nexus mirror
```

---

## What Each Part Does

### `custom_modes.yaml`
Defines the **Dish SDLC** custom mode that Bob loads when you select it from the mode selector. This file wires together the MCP servers (Jira, SonarQube, OCP) and tells Bob which rule files to follow.

See [`Custom_mode.md`](Custom_mode.md) for a full walkthrough of how this mode was created.

### `mcp.json` / `mcp.json.example`
Contains the API keys Bob uses to authenticate with the three MCP servers:
- **Jira MCP** — fetches user stories, posts completion comments, transitions ticket status
- **SonarQube MCP** — triggers analysis, reads quality gate results, reports metrics
- **OCP MCP** — lists allowed namespaces, applies manifests, triggers builds, monitors deployments

`mcp.json` is **pre-populated for you** by the lab instructor and is listed in `.gitignore` — it will never be committed to git. Copy `mcp.json.example` to see the expected structure.

### `gitea.json` / `gitea.json.example`
Contains your Gitea username and personal access token. Bob reads this file when it pushes the modernized application code to your Gitea repository before triggering an OpenShift build.

`gitea.json` is **pre-populated for you** and is listed in `.gitignore`. Copy `gitea.json.example` to see the expected structure.

### `commands/load-skills.md`
Defines the `/load-skills` slash command. When run in Bob, it scans `.bob/skills/` (project-level) and `~/.bob/skills/` (user-level) for Agent Skills and loads them into context. Not required for this lab.

### `rules-dish-sdlc/`
These XML rule files are the brain of the Dish SDLC custom mode. They tell Bob *what to do* and *how to do it* at every step of the workflow — so you don't have to prompt Bob step by step.

| File | Purpose |
|---|---|
| `1_workflow.xml` | Overall SDLC sequence: story → code → test → quality → deploy → Jira |
| `2_jira_integration.xml` | How to fetch stories, parse acceptance criteria, post comments, transition status |
| `3_sonarqube_integration.xml` | How to trigger scans, read quality gates, interpret results |
| `4_best_practices.xml` | Java 21 modernization patterns, Spring Boot 3.x guidelines, Jackson config |
| `5_examples.xml` | Worked examples Bob can reference during implementation |
| `6_ocp_deployment.xml` | OpenShift deployment sequence, Nexus Maven mirror, manifest rules, Gitea push |

---

## What Is and Isn't Committed

| File | In Git? | Reason |
|---|---|---|
| `custom_modes.yaml` | ✅ Yes | Shared mode definition for all participants |
| `Custom_mode.md` | ✅ Yes | Documentation |
| `mcp.json.example` | ✅ Yes | Safe template, no real keys |
| `gitea.json.example` | ✅ Yes | Safe template, no real credentials |
| `rules-dish-sdlc/` | ✅ Yes | Workflow rules — synced to all participant repos |
| `mcp.json` | ❌ No | Contains real API keys |
| `gitea.json` | ❌ No | Contains real Gitea credentials |
