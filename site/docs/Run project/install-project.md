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

## Next steps
&nbsp;&nbsp;&nbsp;&nbsp; [**Run configuration**](Run%20project/run-configurations.md)