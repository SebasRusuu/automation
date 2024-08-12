# First Automation - Sebastião Rusu
This is my first automation project.
It's dedicated to Open the web -> accept cookies -> fill the form and confirmed it.
-----

## 🛠️ Setup Project

<details>
    <summary><b>Pre-requisites</b></summary>

- ### [**Java ♨️**](https://www.oracle.com/java/technologies/sdk-downloads.html )
- ### [**Maven** 🪶](https://maven.apache.org/)
  - ##### [MacOS ](https://formulae.brew.sh/formula/maven)
  - ##### [Windows 🪟](https://maven.apache.org/download.cgi)
- ### [**Allure** ✨](https://allurereport.org/docs/)
  - ##### [MacOs ](https://formulae.brew.sh/formula/allure)
  - ##### [Windows 🪟](https://allurereport.org/docs/install-for-windows/)
- ### [**Selenium** 🟢](https://www.selenium.dev/downloads/)
- ### [**JUnit** 🇯](https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html)
- ### **SonarLint** 📡
  - Plugin installed in the IDE itself
 </details>

<details>
    <summary><b>Install project</b></summary>

### ♨️ Java 21 or later

Please ensure the compatibility and optimal perfomance by installing java:
1. Visit the official Java SE Development Kit (JDK).
2. Download the latest JDK version for your operating system.
3. Run the installer and follow the on-screen instructions to complete the installation.

And verify the installation:
```bash
java -version
```

### 🛠️ Maven Setup

Installing maven on MacOS:

### 1. Install homebrew 🍺:
   - https://brew.sh/

#### With homebrew installed, start installing maven:

### 2. Install maven
 - Open terminal
 - Write the following command "brew install maven"
 - Wait for the installation to finish
 - Check if maven it's intalled by writing "mvn -version" or "brew list"
### 3. Check if Installed
```bash
mvn -version
```
or

```bash
brew list
```

## ⚠️ Allert  
##### If, after installing brew and maven, 
##### you open the terminal again and do "brew list" 
##### or "mvn -version" and "command not found mvn" or "command not found brew" appears,
##### install brew again and add those commands at the end of the installation:
- echo 'eval "$(/usr/local/bin/brew shellenv)"' >> ~/.zprofile
- eval "$(/usr/local/bin/brew shellenv)" 

Installing maven on Windows:

### 1. Download maven on Windows
   - https://maven.apache.org/download.cgi
   - Choose the latest version
   - Download the zip file
   - Extract the zip file to a directory of your choice
   - Add the bin directory to your system PATH for global access
   - Check if maven is installed by writing "mvn -version"

#### after you have installed all the requirements.
1. Clone the repository.

```git
git clone https://github.com/SebasRusuu/automation.git
```
2. Install dependencies and generate code.

```bash
mvn clean install -DskipTests
```
</details>



<details>
    <summary><b>Run configurations</b></summary>

### Selenium Properties
- Selenium properties are located in the `src/main/resources/selenium.properties`[selenium.properties](java-selenium4/exccelent/src/main/resources/selenium.properties).

| Variable(s)               | Value(s)            | Description                      |
|---------------------------|---------------------|----------------------------------|
| selenium.browser          | [Browser](#browser) | Browsers allowed                 |
| selenium.browser.headless | true\false          | Run the browser in headless mode |
| selenium.implicit.wait    | 5s                  | Driver implicit wait in Seconds  |
| selenium.page.fullscreen  | true\false          | Driver sets fullscreen or not    |
| selenium.wait.timeout     | 10s                 | Time to wait for a condition     |

<details>
    <summary>Browsers</summary>

### Browser

| Browser       | Name(s)                  |
|---------------|--------------------------|
| Google Chrome | googlechrome, chrome, gc |
| Firefox       | firefox, ff              |

</details>
</details>
  
# How to run the project 
Now you have installed all the requirements, it's time to run it up.

#### To understand better:
- To run the tests, you can do the following command:
  - ```bash
    mvn clean test
    ```
- After each test, it will generate a report in the target folder, 
where you can check the results of the tests by doing next command:
  - ```bash
    mvn allure:serve
    ```
## Objective
The main objective of this project is to understand how does the automation works,
how to create an automation project using selenium, from the most hardcoding way to the most dynamic way.
