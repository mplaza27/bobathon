> TEMP README

# Start and Initial Setup

Navigate and complete the [PREREQUISITES](./PREREQUISITE.md) section first, then return here and start with lab 1.

# LAB 1

## Overview

This hands-on lab introduces you to Bob, an AI coding assistant integrated into VS Code. You'll work with a sample ACE/MQ integration project to practice Bob's core capabilities: code navigation, analysis, modification, and documentation.

---

### Project Structure

After cloning, your workspace should contain:

```
bobathon-lab1/
├── README.md                           # Main lab guide
├── SETUP.md                            # This file
├── CHEATSHEET.md                       # Quick reference for Bob commands
├── LICENSE                             # MIT License
├── .gitignore                          # Git ignore rules
├── ace-project/                        # ACE integration project
│   ├── project.json                    # Project metadata
│   ├── JavaCompute/                    # Java transformation code
│   │   └── TransformMessage.java       # Main transformation class
│   └── MessageFlows/                   # ACE message flows
│       ├── MainFlow.msgflow            # Primary message flow
│       └── ErrorHandling.subflow       # Error handling logic
└── mq-config/                          # MQ configuration files
    ├── queue-definitions.mqsc          # Queue definitions
    └── qm-config.yaml                  # Queue manager configuration
```

---

### Lab Timeline

| Time | Activity | Duration |
|------|----------|----------|
| 0:00 | Setup and Introduction | 5 min |
| 0:05 | Exercise 1: Code Navigation | 10 min |
| 0:15 | Exercise 2: Visual Architecture Mapping | 12 min |
| 0:27 | Exercise 3: Code Analysis and Improvement | 10 min |
| 0:37 | Exercise 4: Documentation Generation | 8 min |
| 0:45 | Summary and Q&A | 5 min |

Total: 50 minutes (includes 5 minutes buffer)

---

## Setup

### [HERE](./lab1/LAB-1-SETUP.md)
> Notes:

> abcd


# LAB 2

## Overview

This guide covers the complete setup of the IBM Bob Smart SDLC lab environment. It is written for lab administrators as well as participants and accounts for the necessary setup and for **every issue encountered during the initial setup**.

---

### Architecture Overview

```
GitHub IBM         
  .bob/rules-*/                  ──►  lab
  lab2/snapB-java-upgrade/           .bob/ (rules + credentials to the jira board)
                                        lab2-snapB-java-upgrade/
                                        lab2-modernized-pharmacy-app/  ◄── Bob pushes here based on Jira ticket
                                                │
                                                ▼
                                       Update Jira ticket with final bob run details
```


---

## Setup

### [HERE](./lab2/LAB-2-SETUP.md)
> Notes:

> abcd


# LAB 3

## Overview
abcd

## Setup

### [HERE](./lab3/LAB-3-SETUP.md)
> Notes:

> abcd