package com.saucelabs.saucedemo.tests;

import com.saucelabs.saucedemo.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseWebDriverTest {
    @Test
    public void testItem3Label() {
        InventoryPage inventoryPage = navigation().login("standard_user", "secret_sauce");
        Assert.assertEquals("Sauce Labs Bolt T-Shirt", inventoryPage.getItemName(3));
    }

    @Test
    public void testItem3Price() {
        InventoryPage inventoryPage = navigation().login("standard_user", "secret_sauce");
        Assert.assertEquals("$15.99", inventoryPage.getItemPrice(3));
    }

    @Test
    public void testItem3Description() {
        InventoryPage inventoryPage = navigation().login("standard_user", "secret_sauce");
        Assert.assertEquals("Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.", inventoryPage.getItemDescription(3));
    }

    @Test
    public void testAddOneItem() {
        InventoryPage inventoryPage = navigation().login("standard_user", "secret_sauce");
        inventoryPage.addItemToCart(3);
        Assert.assertTrue(inventoryPage.itemAddedToCart(3));
    }
}
