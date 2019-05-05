package com.saucelabs.saucedemo.tests;

import com.saucelabs.saucedemo.pages.SauceDemoNavigation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple TestNG test, which demonstrates takes System properties and converts them to browser configurations
 */
public class BaseWebDriverTest {
    private RunType runType;

    // ThreadLocal variables containing WebDriver instance, the Sauce Job Id, and the Navigation object--these variables
    // ensure that TestNG stays thread-safe
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private ThreadLocal<String> sessionId = new ThreadLocal<>();
    private ThreadLocal<SauceDemoNavigation> navigation = new ThreadLocal<>();

    public enum RunType { LOCAL, SAUCE }

    /**
     * @return the {@link WebDriver} for the current thread
     */
    private WebDriver getWebDriver() {
        return webDriver.get();
    }

    protected SauceDemoNavigation navigation() {
        return navigation.get();
    }

    private void createSauceDriver(MutableCapabilities capabilities, String methodName) {
        String username = System.getenv("SAUCE_USERNAME");
        String accesskey = System.getenv("SAUCE_ACCESS_KEY");


        //You can pass the buildName in as a -D property from Jenkins, or update this default so that it generates a reasonable build name whenever you run
        String defaultBuildName = getDefaultBuildName(capabilities);
        String buildName = System.getProperty("buildName") == null ? defaultBuildName : System.getProperty("buildName");

        //Create a map of capabilities called "sauce:options", which contain the info necessary to run on Sauce
        // Labs, using the credentials stored in the environment variables. Also runs using the new W3C standard.
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accesskey);
        sauceOptions.setCapability("seleniumVersion", "3.141.59");
        sauceOptions.setCapability("name", methodName);
        sauceOptions.setCapability("build", buildName);

        //Assign the Sauce Options to the base capabilities
        capabilities.setCapability("sauce:options", sauceOptions);

        //Create a new RemoteWebDriver, which will initialize the test execution on Sauce Labs servers
        String SAUCE_REMOTE_URL = "https://ondemand.saucelabs.com/wd/hub";
        try {
            webDriver.set(new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), capabilities));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        sessionId.set(((RemoteWebDriver)webDriver.get()).getSessionId().toString());

        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
    }

    private String getDefaultBuildName(MutableCapabilities capabilities) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|hh:mm:ss");
        return String.format("saucedemo-[%s-%s-%s][%s]", capabilities.getPlatform(), capabilities.getBrowserName(), capabilities.getVersion(), sdf.format(new Date()));
    }

    //Note that LOCAL builds only run in Chrome (intended for debugging)
    private void createLocalDriver(MutableCapabilities capabilities) {
        webDriver.set(new ChromeDriver((ChromeOptions) capabilities));
    }

    @BeforeMethod
    protected void createDriver(Method method) {
        String browserName = System.getProperty("browserName") == null ? "chrome" : System.getProperty("browserName");
        String browserVersion = System.getProperty("browserVersion") == null ? "73.0" : System.getProperty("browserVersion");
        String platformName = System.getProperty("platformName") == null ? "macOS 10.14" : System.getProperty("platformName");
        String methodName = method.getName();
        this.runType = System.getProperty("runType") == null ? RunType.SAUCE : RunType.valueOf(System.getProperty("runType"));

        //Set up the ChromeOptions object, which will store the capabilities
        MutableCapabilities capabilities = new MutableCapabilities();

        switch (browserName) {
            case "chrome":
                ChromeOptions caps = new ChromeOptions();
                //This line can go away after Chrome version 75.0 ships
                caps.setExperimentalOption("w3c", true);
                capabilities = caps;
                break;
            case "firefox":
                capabilities = new FirefoxOptions();
                break;
            case "iedriver":
                capabilities = new InternetExplorerOptions();
                break;
            case "edge":
                capabilities = new EdgeOptions();
                break;
            case "safari":
                capabilities = new SafariOptions();
                break;
        }

        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("platformName", platformName);

        switch(runType) {
            case LOCAL:
                createLocalDriver(capabilities);
                break;
            case SAUCE:
                createSauceDriver(capabilities, methodName);
                break;
        }

        navigation.set(new SauceDemoNavigation(getWebDriver()));
    }

    //Method that gets invoked after test--it tears down the webdriver and sends the job-result to Sauce (if applicable)
    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (runType.equals(RunType.SAUCE)) {
                ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
            }
        }
        finally {
            if (webDriver.get() != null) {
                webDriver.get().quit();
            }
        }
    }

    protected void annotate(String text) {
        if (runType.equals(RunType.SAUCE)) {
            ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
        }
    }
}
