# Project Overview

This is a playable Sudoku application built with the following programming languages and technologies:
 * Java
 * JavaFX
 * CSS
 * Maven
  
To download and open the application, proceed as follows:
 * Click on the ```target``` folder
 * Click on the ```jar``` file
 * Click ```download```

## Installation

Clone the repo to your computer by typing the following command in your terminal or command line:

```bash
git clone https://github.com/ccaskey06/sudoku.git
```

## Requirements

Before building and running the application locally, make sure you have the latest versions of Java and Maven installed

In your terminal, type ```java --version``` and ```mvn --version``` to see your current versions

If you get ```command not found```, you'll need to install one or both

### Installing Java

If you don't have Java installed, follow these steps:

 * Download the latest release zip-file [here](https://www.oracle.com/java/technologies/javase-downloads.html)
  * Click ```JDK Download```
  * Download the corresponding installer for your device type:
    * Mac — ```dmg``` file
    * Windows — ```exe``` file
  * Once the installation process is finished, the JDK should now reside in ```/Library/Java/JavaVirtualMachines/```
  * Type the following commands in your terminal:
  
    ```bash
    export JAVA_HOME={INSERT_PATH_TO_JDK_HERE}
    export PATH=$JAVA_HOME/bin:$PATH
    ```
    
  * Type ```java --version``` in the terminal to make sure Java is now installed

### Installing Maven

If you don't have Maven installed, follow these steps:

 * Download the latest release bin zip-file [here](https://maven.apache.org/download.cgi)
 * Unzip the file in a meaningful location on your device, like ```/Library/Maven/```
 * Type the following commands in your terminal:
 
   ```bash
   export M2_HOME={INSERT_PATH_TO_MAVEN_RELEASE}
   export PATH=$M2_HOME/bin:$PATH
   ```
 
 * Type ```mvn --version``` in the terminal to make sure Maven is now installed

## Usage

In your terminal, navigate into the cloned repo folder and type ```mvn clean javafx:run``` to build and run the application locally

OR

Import the application into an IDE, like Eclipse or IntelliJ, and create a run configuration with the command ```clean javafx:run```

## Assets

All icons were found at https://icons8.com
