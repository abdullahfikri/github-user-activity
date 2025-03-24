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

##### 2. Setting Environment variable for redis in terminal (Linux/Mac)
```
    export REDIS_ID=java  # default null
    export REDIS_PASSWORD=rahasia # default null
    export REDIS_HOST=192.168.1.10 # default 127.0.0.1
    export REDIS_PORT=6379 # default 6379
```

#### 3. Check if your environment variable is already created (Linux/Mac)
```
    echo $REDIS_ID
    echo $REDIS_PASSWORD
    echo $REDIS_HOST
    echo $REDIS_PORT
```

##### 4. Run the following command to build and run the app:
```
    mvn clean dependency:copy-dependencies package
    java -cp target/github-user-activity-1.0-SNAPSHOT.jar:target/dependency/* dev.mfikri.App
```

##### 5. Type the command

```
    # Type the user, example track the activity of username abdullahfikri 
    abdullahfikri
    
    # Close the programm
    exit
```
