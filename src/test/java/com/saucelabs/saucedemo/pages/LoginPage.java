package com.saucelabs.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    //Package-protected constructor--only the Navigator object can construct this page
    LoginPage() {}

    String getUrl() { return "https://www.saucedemo.com/"; }

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(className = "btn_action")
    private WebElement submitButton;

    @FindBy(id = "login_credentials")
    private WebElement credentialsInfo;

    @FindBy(className = "svg-inline--fa")
    private WebElement epicSadFace;

    /**
     * Checks to see if the "credentials info" box is displayed toward the bottom of the page. If it is present,
     * this will return "true", otherwise "false"
     * @return
     */
    public boolean isOnPage() {
        return credentialsInfo.isDisplayed();
    }

    /**
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public boolean epicSadFaceDisplayed() {
        return epicSadFace.isDisplayed();
    }
}
