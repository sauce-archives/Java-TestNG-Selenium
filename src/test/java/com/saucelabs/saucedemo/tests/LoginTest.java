package com.saucelabs.saucedemo.tests;

import com.saucelabs.saucedemo.pages.InventoryPage;
import com.saucelabs.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseWebDriverTest {
    @Test
    public void loginTestValid() {
        InventoryPage inventoryPage = navigation().login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isOnPage());
    }

    @Test
    public void loginTestValidPerfGlitch() {
        InventoryPage inventoryPage = navigation().login("performance_glitch_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isOnPage());
    }

    @Test
    public void loginTestValidLockedOut() {
        navigation().login("locked_out_user", "secret_sauce");
        LoginPage loginPage = navigation().getLoginPage();
        Assert.assertTrue(loginPage.epicSadFaceDisplayed());
    }

    @Test
    public void loginTestValidProblem() {
        InventoryPage inventoryPage = navigation().login("problem_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isOnPage());
    }

    @Test
    public void loginTestInvalidUsername() {
        navigation().login("invalid_user", "secret_sauce");
        LoginPage loginPage = navigation().getLoginPage();
        Assert.assertTrue(loginPage.isOnPage());
    }

    @Test
    public void loginTestInvalidPassword() {
        LoginPage loginPage = navigation().getLoginPage();
        navigation().login("standard_user", "invalid_password");
        Assert.assertTrue(loginPage.isOnPage());
    }
}