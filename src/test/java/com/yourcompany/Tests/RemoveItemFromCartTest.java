package com.yourcompany.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;


public class RemoveItemFromCartTest extends TestBase {


    @Test
    public void removeOne() {

        getDriver().get("http://www.saucedemo.com/inventory.html");
        getDriver().findElement(By.className("btn_primary")).click();
        getDriver().findElement(By.className("btn_primary")).click();
        getDriver().findElement(By.className("btn_secondary")).click();

        Assert.assertEquals(getDriver().findElement(By.className("shopping_cart_badge")).getText(), "1");

        getDriver().get("http://www.saucedemo.com/cart.html");
        long expected = getDriver().findElements(By.className("inventory_item_name")).size();
        Assert.assertEquals(expected, 1);
    }
}
