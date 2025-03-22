# Git User Activity
Is a JAVA command line app to track activity of GitHub user from REST API GitHub event.

Project base on: **[GitHub User Activity
](https://roadmap.sh/projects/github-user-activity)**

# Requirement
- Java 21 or higher
- Maven 3.6 or higher

# Feature
- Track user public activity on Google

# How to use

##### 1. Clone the repository with the following command:
```
    git clone https://github.com/abdullahfikri/github-user-activity
    cd ./github-user-activity
```

##### 2. Run the following command to build and run the app:
```
    mvn clean dependency:copy-dependencies package
    java -cp target/github-user-activity-1.0-SNAPSHOT.jar:target/dependency/* dev.mfikri.App
```

##### 3. Type the command

```
    # Type the user, example track the activity of username abdullahfikri 
    abdullahfikri
    
    # Close the programm
    exit
```

