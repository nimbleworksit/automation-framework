# GATEWAY AUTOMATION BY ENSONO #

This is the repository for the Gateway 3.0 test automation framework by Ensono

### Set Up ###

The Gateway 3.0 test automation framework uses Selenium Web Driver with Java as the scripting language
Following instructions guides in installing the necessary software to set up the run time and development environments for the test automation framework
The framework uses 'Maven' to download/install the necessary libraries/dependencies

### Install Java ###

•	Download the latest stable version of Java(JDK) from the Oracle Site 
•	Run the .exe file to install java
•	Add system variable : Variable name = JAVA_HOME, Variable value = C:\Program Files\Java\jdk1.8.0_91 (i.e., path where java has been installed) 
•	Edit 'Path' system variable : Variable name = Path, Variable value = C:\Program Files\Java\jdk1.8.0_91\bin\ (i.e., path_where_java_has_been_installed\bin)
•	Make sure java is installed correctly: Open cmd prompt and run the command 'java -version' and you should see the version of JDK printed 


### Install Eclipse IDE ###

•	Download 'Eclipse IDE for Java Developers' from the Eclispse Site
•	Extract the downloaded zip folder to any path
•	Run the eclipse.exe file from extracted folder and this will open the IDE 

### Add cucumber plug-in ###

•	Help --> Install New Software --> Add --> Name : Cucumber; Location : http://file:path_to_repo/cucumber.eclipse.p2updatesite/target/repository 
•	Accept license and finish
(You can add any plug-in you prefer. Above is a reference for Eclipse IDE to support cucumber) 


### Import project and run locally ###

•	Open Eclipse IDE
•	Click File --> Import --> General --> Import existing project into workspace --> Select root directory
•	Browse (the path where automation project is cloned)
•	Select the project from the list in the Projects box --> Next --> Finish.
•	Now you should see the project in the 'Project Explorer' pane
•	Right click on project name --> Run As --> Maven clean
•	Right click on the project name --> Run As --> Maven build --> (In the Main tab) enter test in Goals textbox --> Apply --> Run (This will run all the tests and you can see the test results in the console)

Note : If Maven build throws error which says 'No goals have been specified for this build' then Right click on project --> Run AS --> Run Configurations --> Maven Build --> gateway-automation --> (In the Main tab) enter test in Goals textbox --> Apply --> OK.

Use 'Maven build' to run the tests with the configuration you have set.

User 'Maven build...' to create a new build configuration.

### Install Eclipse IDE ###

•	Windows comes with JRE installed by default. So, if eclipse throws some errors then make sure that it is using the installed JDK instead of default JRE
•	Goto Window --> Preferences --> Java --> Installed JREs --> Select the JDK installed --> OK 
