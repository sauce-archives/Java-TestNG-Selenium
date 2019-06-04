package com.yourcompany.Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class AddItemsToCartTest extends TestBase {

    
    @Test
    public void addOneItem(){
        getDriver().get("http://www.saucedemo.com/inventory.html");
        getDriver().findElement(By.className("btn_primary")).click();

        Assert.assertEquals(getDriver().findElement(By.className("shopping_cart_badge")).getText(), "1");

        getDriver().get("http://www.saucedemo.com/cart.html");
        long expected = getDriver().findElements(By.className("inventory_item_name")).size();

        Assert.assertEquals(expected, 1);

    }

    @Test
    public void addTwoItems(){
        getDriver().get("http://www.saucedemo.com/inventory.html");
        getDriver().findElement(By.className("btn_primary")).click();
        getDriver().findElement(By.className("btn_primary")).click();

        Assert.assertEquals(getDriver().findElement(By.className("shopping_cart_badge")).getText(), "2");

        getDriver().get("http://www.saucedemo.com/cart.html");
        long expected = getDriver().findElements(By.className("inventory_item_name")).size();

        Assert.assertEquals(expected, 2);
    }
}
