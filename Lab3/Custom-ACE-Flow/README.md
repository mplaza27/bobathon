## Custom ACE Flow Lab Exercise

Welcome to creating your very own custom ACE workflow! This lab consists of two sections: first, you'll create your own custom ACE flow from scratch using Bob, and second you'll create your own personalized, finely-tuned custom Bob mode to make your ACE flow repeatable.
Please reference the steps below to get started.

### Section 0: Prerequisites
- Bob installed and logged in
- Custom-ACE-Flow files (example_prompts.txt) downloaded on your VM

### Section 1: Creating your First Custom ACE Flow
To create your custom ACE flow, please copy and paste the first example prompt. Let Bob do the building for you!


##### Command 1.1: Create your Custom ACE Flow
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


  
##### Command 1.2: Modifying the API Endpoint
Please copy/paste this command below into your Bob Terminal:
`Now please modify my API endpoint to be /requests/outgoing/custom1`

Bob will now edit your message flow file and also the test api to perform this change. Observe how Bob asks you for permissions, makes it clear what it will want to change, and let's you actively intervene at any point. It may even update all user manuals and guides to reflect these changes. At the end, Bob should check to ensure all proper changes were made across your application.

##### Command 1.3: Changing the Deployment Method
To make another modification, this time to say your deployment method, you can try running this command to the Bob chat:
`Now please change the deployment method to Podman instead of Docker`

You should see Bob now making the necessary file modifications to switch the deployment method to Podman if it isn't already using Podman. If Docker was previously used, you'll see Bob delete any old Docker files to clean up your repository. You should also see Bob change any downstream files depending on this deployment method, such as the setup instructions and quick start guide. Bob should verify that the proper modifications were made also.

##### Command 1.4: Adding Additional Security Layer
Now let's investigate this newly-constructed ACE flow from a security perpsective. Since this repository has just been created, it likely may have some exposed vulnerabilities. Try running the command below to see if there are any key vulnerabilities worth noting:

`Please now run a preliminary vulnerability scan and let me know if any key security issues are apparent with this flow`









### Section 2: Creating your Repeatable ACE Expert Bob Mode
To create your own custom ACE Expert Bob Mode, please select "Mode Builder", and then copy and paste the second example prompt. From there you will get your own custom Bob mode created!
   
Follow Bob's steps to help you create your custom Bob mode. Here, Bob will construct your custom_modes.yaml file for you, which is the basis for your custom mode. Bob will then use that to create and launch your custom ACE Expert mode, which should then appear in the Mode Selection Section of your Bob IDE Console. If for some reason the newly-created ACE Expert Mode isn't appearing in this menu, you can simply instruct Bob to launch your mode and it will execute the steps necessary to get it up and running.

Now you're ready to select your newly-constructed ACE Expert Mode to get started writing customized ACE flows!
