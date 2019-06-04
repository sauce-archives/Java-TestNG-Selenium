package com.yourcompany.Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ValidLoginTest extends TestBase {

    @Test
    public void standardUser(){
        getDriver().get("http://www.saucedemo.com");

        getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        getDriver().findElement(By.cssSelector(".btn_action")).click();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"));
    }
}
