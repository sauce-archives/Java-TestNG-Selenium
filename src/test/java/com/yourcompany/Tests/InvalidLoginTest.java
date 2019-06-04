package com.yourcompany.Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class InvalidLoginTest extends TestBase {


    @Test
    public void standardUser(){
        getDriver().get("http://www.saucedemo.com");

        getDriver().findElement(By.id("user-name")).sendKeys("");
        getDriver().findElement(By.id("password")).sendKeys("");
        getDriver().findElement(By.cssSelector(".btn_action")).click();

        Assert.assertTrue(getDriver().findElement(By.cssSelector(".error-button")).isDisplayed());
    }
}
