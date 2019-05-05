package com.saucelabs.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoNavigation {
    private WebDriver driver;
    public SauceDemoNavigation(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Returns an initialized instance of the LoginPage. This is the only way to get a fully operational instance of
     * this class, due to the fact that Pages do not have access to the WebDriver, only the WebElements within the page
     * @return an initialized instance of the LoginPage
     */
    public LoginPage getLoginPage() {
        LoginPage loginPage = new LoginPage();
        PageFactory.initElements(driver, loginPage);
        return loginPage;
    }

    /**
     * Use this method to log into the SauceDemo web site. By providing a username and a password, you can initialize
     * a SauceDemo session. Note that this assumes a legitimate user--if the Login operation fails, the browser
     * will not be on the Inventory Page as expected.
     * @param username The username to log in as
     * @param password The password to use for this login
     * @return an initialized instance of the InventoryPage
     */
    public InventoryPage login(String username, String password) {
        LoginPage loginPage = getLoginPage();
        driver.get(loginPage.getUrl());

        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage();
        PageFactory.initElements(driver, inventoryPage);
        return inventoryPage;
    }

    /**
     * Returns an initialized instance of the InventoryPage. This is the only way to get a fully operational instance of
     * this class, due to the fact that Pages do not have access to the WebDriver, only the WebElements within the page
     * @return an initialized instance of the InventoryPage
     */
    public InventoryPage getInventoryPage() {
        InventoryPage inventoryPage = new InventoryPage();
        PageFactory.initElements(driver, inventoryPage);
        return inventoryPage;
    }
}
