# IBM Bob Lab — Java Modernization Lab Guide

**Duration:** 45 minutes
**Objective:** Learn to use Bob as an AI-powered Java Modernization Expert.

## Learning Objectives

By completing this lab, you will be able to:
- Connect MCP servers to Bob IDE
- Create and Understand Bob custom modes
- See Bob understand and build off of a natural language userstory
- Make code modifications with AI assistance
- Create comprehensive documentation automatically

## About This Lab
In this lab we will use a generic user story defined in Jira tasks board to modify a legacy java app to a newer stack. We will leverage MCP servers to connect, read and modify a Jira task/ticket and a custom Bob mode to enhance the web application.

### Understand the need:
#### What is This Application?
The **Simple Pharmacy Management System** provided to you in the lab is a web-based application designed to manage pharmacy operations including:
- **Prescriptions**: Create and validate patient prescriptions
- **Orders**: Process medication orders and payments
- **Medicines**: Manage medicine inventory
- **Dashboard**: Monitor pending prescriptions and orders

#### What is Java Modernization?
Java Modernization is the process of upgrading legacy Java applications to modern versions and platforms. This typically involves:
- **Java Version Upgrade**: Moving from older Java versions (like Java 8) to newer LTS versions (like Java 21)
- **Application Server Migration**: Transitioning from traditional servers (like WebSphere) to lightweight runtimes (like Liberty)
- **Dependency Updates**: Modernizing libraries and frameworks to current, supported versions
- **Code Transformation**: Updating code patterns to leverage modern Java features

In this lab, you'll use a custom sdlc mode that you define to modernize a legacy pharmacy management application. The application currently runs on:
- **Java Version**: 8
You'll modernize it to:
- **Java Version**: 21

## Prerequisites

- IBM Bob IDE
  - Ensure you have IBM Bob latest version installed
  - Login through Bob to get connected
> If you just completed `Lab1` remember to close out of Bob and re-open again inside Folder `Lab2`. The goal is to have `Lab2` be your base folder so that Bob automatically picks up the .bob folder corresponding with this Lab.

- Atlassian account with Jira project access
  - The instructor should set up a free atlassian jira account here - https://www.atlassian.com/software/jira
  - Use your gmail id or any other id since IBM id will require adding you to IBM account and it would be difficult to add the Bobathon participants to it.
  - Continue to create your org space with atlassian.net domain and note it - eg: https://ce-squad-7.atlassian.net/
  - Create a new space in your jira dashboard. Click on the '+' sign next to the "Spaces" option on the left navigation options.

![screenshots/image.png](screenshots/image.png)
  - Start with a Scrum or a Kanban board. Click on Scrum option and click on use template.

![img1.png](screenshots/img1.png)

  - Add your board name, for space managed chose team managed option, for access choose limited and add the Key. This key is important since this acts as the tag for all stories you create in the board and how Bob would access the stories. For us the key is "SCRUM"

![img2.png](screenshots/img2.png)

  - On the next page you should be able to add all the members to the board
  - Now you can start creating user stories on the board. Note the url of the board. You will need to add this to the custom mode docs to ensure the Smart SDLC mode accesses the correct user stories for all users. You only need To Do, In Progress and Done for this Lab.

![img.png](screenshots/img.png)

- Custom mode updates (this can also be done by the participants)
  - Modify the custom mode Lab with the jira board information. This can also be done via the env file and by prompting bob to use the jira board details from the env file. But for this Lab we would be hard coding the board details in the rules of the custom mode.
  - In the .bob/rules-smart-sdlc/1_workflow.xml file, search for the **Fetch Jira User Story** block. For the mcp configuration section add the following details:
    ```bash
    <mcp_configuration>
        Project: <Your project or space name - Eg.: VM Projects> (< link to your scrum or kanban board eg: https://ce-squad-7.atlassian.net/jira/software/projects/SCRUM/boards/1>)
        Base URL: <Your atlassian.net url eg - https://ce-squad-7.atlassian.net/>
        Email: <instructor or participant email id>
      </mcp_configuration>
    ```
---
# For Bobathon Participants:

## Step 1: Set up your Atlassian account and API
Accept the invite from the instructor ro join a jira space, after which the instructor will be able to add you to the Jira Board.
Generate your Atlassian Jira api token from here - https://id.atlassian.com/manage-profile/security/api-tokens
- Click on Create API Token. (not the scope ones)
- ![img_6.png](screenshots/img_6.png)
- Give a name and set an expiry for the token and click on Create
- ![img_7.png](screenshots/img_7.png)
- Copy and save the api token. You will need to add this to the MCP server connection later.
- ![img_8.png](screenshots/img_8.png)

## Step 2: Set up the MCP Server

- First setup UV so the MCP can be installed correctly:

To ensure the mcp server works, you will need to have installed uv on your local. If not already installed prompt bob to install it with the following prompt:

```text
Install uv uvx package for package management for python.
```

**MCP Server (need to be set up in bob global mcp setting)**:
- **Atlassian Jira MCP** — Bob fetches user stories and posts completion comments
  - Note: This mcp server has a lot of functions and can cause context bloat. The mcp block mentions important functions needed. If tokens and bob coins is a concern, turn off function not needed for the Lab in the global mcp settings.
  - Open your Bob IDE and navigate to 'Bob - Settings' option at the bottom of the IDE and click on it.
  - ![img_2.png](screenshots/img_2.png)
  - Click on MCP and open the global mcp setting
  - ![img_3.png](screenshots/img_3.png)
  - Copy the following MCP server connection details as a new mcp in the json file that opens: (you can remove the disabled tools section, we only added it to restrict scope of the mcp server)
  > **IMPORTANT** Make sure to change `JIRA_URL`, `JIRA_USERNAME`, `JIRA_API_TOKEN` to your actual `URL`, `USERNAME` and `API TOKEN` accordingly.


  ```bash
          "mcp-atlassian": {
            "command": "uvx",
            "args": [
                "mcp-atlassian"
            ],
            "env": {
                "JIRA_URL": "<your atlassian.net url> eg: https://ce-squad-7.atlassian.net",
                "JIRA_USERNAME": "<your email id>",
                "JIRA_API_TOKEN": "<your atlassian token>",
                "CONFLUENCE_URL": "",
                "CONFLUENCE_USERNAME": "",
                "CONFLUENCE_API_TOKEN": ""
            },
            "timeout": 300,
            "alwaysAllow": [
                "jira_get_board_issues",
                "jira_get_issue",
                "jira_add_comment",
                "jira_transition_issue",
                "jira_get_transitions"
            ],
            "disabledTools": [
                "jira_get_issue_watchers",
                "jira_add_watcher",
                "jira_remove_watcher",
                "jira_search",
                "jira_search_fields",
                "jira_get_field_options",
                "jira_get_project_issues",
                "jira_get_worklog",
                "jira_download_attachments",
                "jira_get_issue_images",
                "jira_get_agile_boards",
                "jira_get_sprints_from_board",
                "jira_get_sprint_issues",
                "jira_get_link_types",
                "jira_create_issue",
                "jira_batch_create_issues",
                "jira_batch_get_changelogs",
                "jira_delete_issue",
                "jira_edit_comment",
                "jira_add_worklog",
                "jira_link_to_epic",
                "jira_create_issue_link",
                "jira_create_remote_issue_link",
                "jira_remove_issue_link",
                "jira_create_sprint",
                "jira_update_sprint",
                "jira_add_issues_to_sprint",
                "jira_get_project_components",
                "jira_get_all_projects",
                "jira_get_project_versions",
                "jira_get_service_desk_for_project",
                "jira_get_service_desk_queues",
                "jira_get_queue_issues",
                "jira_create_version",
                "jira_batch_create_versions",
                "jira_get_issue_proforma_forms",
                "jira_get_proforma_form_details",
                "jira_update_proforma_form_answers",
                "jira_get_issue_dates",
                "jira_get_issue_sla",
                "jira_get_issue_development_info",
                "jira_get_issues_development_info"
            ]
        }
  ```
    -  Once added refresh all icon next to the 'All' dropdown to refresh the servers on the Bob - Settings MCP main page (where you clicked on Open for the json) just to ensure bob connects successfully. You can ignore this step if you already see the server in your mcp list with a green dot.
    - ![img_4.png](screenshots/img_4.png)


## Step 3: Setup Java

Great, now navigate to Bob and enter the following in the Bob chat window. This will automate the install process and have Bob take care of it for you.

![bob_open_image%20copy](./screenshots/bob_open_image%20copy.png)

Paste the following:

```
Please walk step-by-step through the commands below, telling me each step of the way where you are at when following the commands provided below, and please run each command in a separate step, in order. Please start from the top and work your way down, running each command you see one-by-one in the steps below:

# OpenShift VM Setup Instructions

## 3.1 Java 21 and Maven - OpenShift VM

For a Red Hat OpenShift VM, the smoothest first-time setup is to use the native RHEL packages for Java and Maven instead of installing Java through SDKMAN!. This avoids version mismatches where Maven picks up Java 17 even though Java 21 is installed.

### Install Java 21 and Maven

sudo dnf update -y
sudo dnf install -y java-21-openjdk java-21-openjdk-devel maven


This installs the Java 21 runtime, Java 21 development tools, and Maven from the RHEL repositories.

### Set Java 21 as the Active Default

If multiple Java versions are installed, explicitly switch both `java` and `javac` to Java 21:


sudo alternatives --config java
sudo alternatives --config javac

When prompted, select the option for Java 21, which typically points to a path like:

/usr/lib/jvm/java-21-openjdk-21.0.11.0.10-1.el9.x86_64/bin/java
/usr/lib/jvm/java-21-openjdk-21.0.11.0.10-1.el9.x86_64/bin/javac

Using `alternatives` is the key step on RHEL systems when both Java 17 and Java 21 are installed, because the system may otherwise continue using Java 17 by default.

### Set JAVA_HOME

After switching the alternatives, set `JAVA_HOME` to the Java 21 JDK path:

export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export PATH="$JAVA_HOME/bin:$PATH"

To make this persistent for future sessions:

echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk' >> ~/.bash_profile
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.bash_profile
source ~/.bash_profile

On RHEL, `/usr/lib/jvm/java-21-openjdk` is typically a symlink managed by the alternatives system, which makes it a stable `JAVA_HOME` value. [file:61]

### Verify Java and Maven

java -version
javac -version
mvn -version

Expected result:
- `java -version` should show OpenJDK 21.
- `javac -version` should show version 21.
- `mvn -version` should show Maven 3.x and report Java 21 as the runtime.

```

![bob_java_prompt%20copy](./screenshots/bob_java_prompt%20copy.png)

There are two potential steps where Bob might need to be reinitialized for the install to continue and be finalized, if this happens you will have to close out of the Bob Chat window and re-open the messages that you were just on:

`sceneraio A`:

![bob_java_block_a%20copy](./screenshots/bob_java_block_a%20copy.png)

or `scenario B`:

![bob_java_block_b%20copy](./screenshots/bob_java_block_b%20copy.png)

Re-open the messages that you were just on:

![bob_java_continue%20copy](./screenshots/bob_java_continue%20copy.png)

and then continue with `Resume Task`:

![bob_resume_task%20copy](./screenshots/bob_resume_task%20copy.png)

### Check Maven

You should see Maven version information along with the Java version that Maven is using at the end of this prompt.


### **Important Note on Java Versions:**
When you run `mvn --version`, it shows the Java version that Maven will use to build your project. This may differ from your system's default Java version (shown by `java -version`). Maven uses the Java version specified by the `JAVA_HOME` environment variable or the Java installation that SDKMAN! has configured.

### Important: Restart Bob After Maven Installation

**⚠️ CRITICAL STEP:** After installing Maven via SDKMAN!, you **MUST** close and restart IBM Bob (or your IDE) for the changes to take effect. This ensures Bob can detect and use the newly installed Maven.

**To restart Bob:**
1. Close your IDE completely
2. Reopen your IDE
3. Verify Maven is detected by running the command to verify its installation in Bob's terminal



## Step 4: Set Up Jira User Stories
Every participant needs a Jira User Story to work with. Here is the story that needs to be added to the board in the TO-DO list. This is a basic modernization request, feel free to add more details or functionality request to this if you want to experiment more.:

### 4.1 Open up Jira
To add a Jira Ticket go to the following board - https://ce-squad-7.atlassian.net/jira/software/projects/SCRUM/boards/1

### 4.2 Click Create
Click on `+Create` on either the top of the board or on the bottom of the `TO-DO` list

![jira_create](./screenshots/jira_create.png)
> This shows a jira board that's already populated, the steps are the same even if you have an empty board.

### 4.3 Paste Task Information
Start by adding the following `Summary` and `Description` to the task and press `Create`.

`Summary`:
```
Modernize the Pharmacy App from Java 8 / WebSphere Liberty to Java 21 / Liberty with Jakarta EE 8 and Struts 2
```

`Description`:

```text
User story

Modernize the Pharmacy application in Lab2/snapB-java-upgrade to run on Java 21 and a compatible Liberty runtime with Jakarta EE 8 with the minimum code and configuration changes required to preserve existing behavior and get the application compiling, testing, packaging, and starting successfully.

Context

The goal of this story is platform compatibility first, not code redesign. The application is a Struts 2 web application with models, repositories, actions, JSP views, and Liberty configuration. This story should avoid broad refactoring and should preserve existing class shapes, method contracts, repository behavior, and action/view behavior unless a change is strictly required for Java 21 or Jakarta EE 8 compatibility.

Acceptance criteria

1. Runtime and dependency modernization

Update pom.xml to compile with Java 21.

Upgrade only the dependencies required for Java 21 and Liberty compatibility.

Migrate javax.* to jakarta.* for Jakarta EE 8 compatibility only where required by the selected runtime and libraries.

Update web.xml and Liberty configuration only as needed for compatibility.

Verify Struts, Liberty, ASM, Javassist, and test dependencies are compatible with the chosen stack.

2. Minimal-change implementation rules

Do not convert models, actions, or request-bound classes to records in this story.

Do not change enum values, enum descriptions, parser semantics, or helper-method semantics in this story.

Do not change repository seeding, filtering logic, or null-handling behavior unless required to restore legacy compatibility.

Do not replace BigDecimal logic with double or primitive arithmetic.

Preserve existing getters, setters, constructors, and public method signatures unless a compatibility fix requires otherwise.

3. Required validation only
The story is complete when the following pass:

    mvn clean test

    mvn clean package

    mvn liberty:run or ./run-liberty.sh

    browser verification of dashboard, medicine list, prescription list, and order list pages

4. Required targeted test suite
Create or maintain only the following targeted tests in this story:

Compile-safety checks for enums and public model APIs used by actions and repositories

One test verifying OrderStatus / PrescriptionStatus / PaymentMethod symbols and parsing methods still exist if present in the legacy app

One test verifying money calculations still use BigDecimal-compatible behavior

One MedicineRepository smoke test for findAll()

One MedicineRepository smoke test for updateStock()

One Medicine helper-method smoke test

One OrderRepository smoke test

One PrescriptionRepository smoke test

One action or integration smoke test per key page: dashboard, medicines, prescriptions, orders

5. Quality requirements

All required tests must pass on Java 21.

JaCoCo report must be generated.

Coverage target for this story is 30% minimum, focused on risky modernization paths rather than broad business-rule regression coverage.

Zero Blocker or Critical issues.

Zero newly introduced security vulnerabilities.

6. Reporting

Move the story to In Progress before starting.

Add comments summarizing:

    - dependency/runtime changes

    - whether ports changed

    - build result

    - test result

    - page verification result for dashboard, medicines, prescriptions, and orders

    - total time taken

Move the story to Done only after all required checks pass.
```

![jira_fill_out](./screenshots/jira_fill_out.png)

or if you have an empty board, it might look as follows:

![empty_board](./screenshots/empty_board.png)

### 4.4 Click on the Ticket to Open the Details
Once you click `Create`, there should be a pop up for the ticket. When the pop up shows up, click on the ticket name.
> **Important**
> Write down/remember the name of the ticket (i.e. SCRUM-11, SCRUM-12, or SCRUM-[your-number])

![jira_view_ticket](./screenshots/jira_view_ticket.png)

### 4.5 Assign Ticket to Yourself
Then assign the ticket to yourself and close out of the ticket screen. 

![jira_ticket_screen](./screenshots/jira_ticket_screen.png)

### 4.6 Create Sprint
Now we have to create a sprint and assign the ticket to the sprint.

First, create a sprint by navigating to `Backlog` and clicking on `Create sprint`:

![jira_create_sprint](./screenshots/jira_create_sprint.png)

### 4.7 Add Ticket to Sprint
Now, drag the ticket into the sprint you just created and start the sprint.

![jira_drag_and_start_sprint](./screenshots/jira_drag_and_start_sprint.png)

### 4.8 Start the Sprint
Click through the next window to start the sprint.

![jira_start](./screenshots/jira_start.png)

### 4.9 Validate Completion
Now you should see the ticket on your `Board` page. If you do, you have successfully completed the Jira setup process and can proceed.

![jira_complete](./screenshots/jira_complete.png)


##### Important:
> Ensure the participant is marked as the assignee to the user story
> The participant needs to note the exact Jira key (e.g. `SCRUM-7`) when running Bob.
> 
![img_5.png](screenshots/img_5.png)

## Step 5: Custom Mode and MCP Check:
- In the project folder, you should see a .bob folder.
- In the .bob/rules-smart-sdlc/1_workflow.xml file, search for the **Fetch Jira User Story** block. For the mcp configuration section add or verify the following details (depending on what you instructor has mentioned):
```bash
    <mcp_configuration>
          Project: Your project or space name - Eg.: VM Projects (link to your scrum or kanban board eg: https://ce-squad-7.atlassian.net/jira/software/projects/SCRUM/boards/1)
          Base URL: Your atlassian.net url eg - https://ce-squad-7.atlassian.net/
          Email: instructor or participant email id
    </mcp_configuration>
```
- Save the file and open the bob chat interface.
- At the bottom of the Bob chat pane, you'll see the current mode (e.g., "💻 Code" or "❓ Ask"). Click on the current mode name at the bottom of the chat.
- You should see a "Smart SDLC" option now in the Bob Modes dropdown.
- ![img_9.png](screenshots/img_9.png)
- For ease of doing the Lab you can also set all auto approvals on
  - ![img_11.png](screenshots/img_11.png)
    - ## Auto-Approval Settings
      Here are some of the setting how can control what Bob does automatically:
          - **Read**: Let Bob read files without asking
          - **Write**: Let Bob modify files without asking
          - **Execute**: Let Bob run commands without asking
          - **Todo**: Let Bob create task lists without asking
          - **MCP**: Let's Bob access and connect to MCP servers automatically without asking.
- You can also check the custom mode details on the Bob Settings page by navigating to the Modes tab
- ![img_10.png](screenshots/img_10.png)
- Now that you have your mode and mcp setup. Let's confirm if Bob can actually connect to the Jira Board. Switch to Advanced or Code mode and ask the following:
```text
Do I have an jira board with a user story assigned to me?
```

![SCRUM_end_of_step_5](./screenshots/SCRUM_end_of_step_5.png)
> Example result.

- Bob should be able to connect to the board via the mcp, verify your email id based on the connection you have set up and fetch you the correct information.

- Once this works you are all set to work on your tickets.


## Step 6: Let's Ask Bob to Do All Our Work Now!

---
### Main Workflow: See how the defined custom mode interacts with Jira MCP server and rules we have defined to perform Java modernization
- Open Bob chat and chose the 'Smart SDLC' Mode

![img_9.png](screenshots/img_9.png)

- Now ask bob the following in the chat:
> **IMPORTANT** Remember to change your `STORY KEY` Place holder value to your actual SCRUM identifier that you created in `Step 4`.

```text
Retrieve the user story whose key is <YOUR-STORY-KEY eg: SCRUM-4> and implement it
```
- And that's it. The mode will come up with an execution plan based on the requirement and acceptance criteria mentioned in the user story and work on it. It will also update the user-story with details of the entire workflow Bob did once the deployment is successful.
- the mode does have checkpoint prints so you will be able to see what Bob is trying throughout the process.
- When Bob starts working on the task, check your Jira Board and you should see the task/ticket been moved to 'In progress' status
- ![jiraboard_ip](./screenshots/jiraboard_ip.png)
- Once the task is completed, it will be moved to "Done" and you would have a comment with details on the tasks Bob did.
- ![jiraboard_done](./screenshots/jiraboard_done.png)
- ![jiraboard_comment](./screenshots/jiraboard_comment.png)
- At one of the checkpoints bob will also prompt you to open the new modernized dashboard and you can see the java app working.
- ![javadashboard1](./screenshots/javadashboard1.png)
- ![javadashboard2](./screenshots/javadashboard2.png)
- ![javadashboard3](./screenshots/javadashboard3.png)
- ![javadashboard4](./screenshots/javadashboard4.png)

This concludes the main Java modernization Lab!

---

### Optional: Initiate the built-in Java Modernization workflow without interaction with Jira User Story
Have Bob use the Java Modernization workflow to analyze your current application, use a migration plan to upgrade the application, and validate the application post-upgrade.
1. **Start the Workflow**
   * In the Bob chat window, you will see the Java Modernization workflow under Workflows. Select the Start button on the workflow to have Bob begin the Java modernization flow.
2. **Analyze**
   * **1.1 Analyze Java Project**
      Ensure that the Project Path that Bob populates in your current project directory and select **Analyze Project**.
   * **1.2 Select Java modernization type**
      Select **Java Upgrade** for modernization type. Toggle **Git Flow** off.
3. **Upgrade**
   a. **Java Upgrade**
   * **2.1 Run Java upgrade recipes**
      Select your **Java 21** as your target. Toggle Jakarta EE 8 migration option off and click **Run Recipes**.
* **2.2 Perform agentic upgrade**
   Bob will proceed with the upgrade task involving several subtasks. Bob will create to do list(s) and complete tasks agentically, while also allowing user intervention and approvals.
4**Validate**
   Select the option for Bob to Validate your application
5**Run the application**
   Prompt Bob to run the Simple Pharmacy application. Follow the URL the Bob provide to view the UI of the local application.
---


# Troubleshooting

## Issue 1: Bob Can't Read Project Files

**Symptom:**
Bob says "I cannot access that file" or "File not found"

**Solution:**
1. Verify you're in the correct directory
2. Check file permissions: `ls -la`
3. Ensure Bob has read access to the workspace
4. Try referencing files with `@filename` syntax

## Issue 2: Maven or Java version not printing
**Symptom:**
maven is not getting recognized

**Solution:**
Restart Bob IDE after the package install

---
### Getting Help

#### During the Lab
1. **Check Troubleshooting Section** - Most common issues are covered
2. **Ask Bob** - Bob can help explain errors and suggest fixes
3. **Ask Your Instructor** - Don't hesitate to raise your hand
4. **Collaborate** - Discuss with classmates 

---

## Conclusion

Congratulations! You've completed the Java Modernization Lab. You should now be able to:

✅ Set up mcp-servers
✅ Set up and use Bob's Custom Modes
✅ Use Bob to analyze and upgrade the Pharmacy application and validate its java modernization

---



