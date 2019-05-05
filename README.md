## Java-TestNg-Selenium
[![Travis Status](https://travis-ci.org/saucelabs-sample-test-frameworks/Java-TestNG-Selenium.svg?branch=master)](https://travis-ci.org/saucelabs-sample-test-frameworks/Java-TestNG-Selenium)

This code is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. Your tests and testing environments may require you to modify this framework. Issues regarding this framework should be submitted through GitHub. For questions regarding Sauce Labs integration, please see the Sauce Labs documentation at https://wiki.saucelabs.com/. This framework is not maintained by Sauce Labs Support.

### Environment Setup

1. Global Dependencies
    * [Install Maven](https://maven.apache.org/install.html)
    * Or Install Maven with [Homebrew](http://brew.sh/)
    ```
    $ brew install maven
    ```
2. Sauce Credentials
    * In the terminal export your Sauce Labs Credentials as environmental variables:
    ```
    $ export SAUCE_USERNAME=<your Sauce Labs username>
    $ export SAUCE_ACCESS_KEY=<your Sauce Labs access key>
    ```
3. Project Dependencies
	* Check that Packages are available
	```
	$ cd Java-TestNg-Selenium
	$ mvn test-compile
	```
	
	* You may also want to run the command below to check for outdated dependencies. Please be sure to verify and review 
	updates before editing your pom.xml file. We try to keep this project up-to-date, but it's not always possible:
	 
	```
	$ mvn versions:display-dependency-updates
	```
### Running Tests

Tests in Parallel (these default to Chrome on MacOS):
	```
	$ mvn clean test
	```
	
If you want to run all the tests in parallel on a particular platform, pass the platform details as System Properties:
	
	mvn test -DbrowserName=chrome -DbrowserVersion=74.0 -DplatformName="macOS 10.14"
	mvn test -DbrowserName=safari -DbrowserVersion=12.0 -DplatformName="macOS 10.14"
	mvn test -DbrowserName=firefox -DbrowserVersion=66.0 -DplatformName="Windows 10"
    mvn test -DbrowserName=edge -DbrowserVersion=16.16299 -DplatformName="Windows 10" 

Note that this project does not attempt to enforce any platform rules: Safari can only run on MacOS, Edge can only run 
on Windows, and this project assumes the user will pass valid combinations: no such errors will be thrown by this code.

[Sauce Labs Dashboard](https://saucelabs.com/beta/dashboard)

### Advice/Troubleshooting
1. A Java IDE such as IntelliJ or Eclipse will help troubleshoot any issues. 
2. There may be additional latency when using a remote webdriver to run tests on Sauce Labs. Timeouts or Waits may need to be increased.
    * [Selenium tips regarding explicit waits](https://wiki.saucelabs.com/display/DOCS/Best+Practice%3A+Use+Explicit+Waits)

### Resources
##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [TestNg Documentation](http://testng.org/javadocs/index.html)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)
