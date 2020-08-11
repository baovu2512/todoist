# todoist-automation
Automation Testing Practical Assignment

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Make sure you have installed all of the following prerequisites on your development machine:
* JDK 8 - [Download and install JDK 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
* Maven - [Download & Install Maven](http://maven.apache.org/)
* Android Studio - [Download & Install Android Studio](https://developer.android.com/studio).
* Appium and appium-doctor - [How to install Appium](http://appium.io/docs/en/about-appium/getting-started/)

## Test Configuration

### Device Cofiguration
To Find the udid of the device you want to run test
```
adb devices -l
```
Then make change on configuration file `src/test/resources/configs/android/devices.json`. You should give the correct value to `platformVersion` and `udid` ortherwise the test might not be able to run on the device. If the device is emulator so please set isReal = false.

## Run Test

### Test Configuration
From your terminal/command prompt, change directory to folder where you clone the project. 
```
mvn clean test
```
After the test finishes, you can find the html report `\ExtentReports\ExtentReportResults.html`
