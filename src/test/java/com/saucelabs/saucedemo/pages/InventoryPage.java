package com.saucelabs.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage {
    //Package-protected constructor--only the Navigator object can construct this page
    InventoryPage() {}

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    @FindBy(className = "inventory_container")
    private WebElement inventoryContainer;

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(className = "header_secondary_container")
    private WebElement inventoryHeader;

    /**
     * Returns the Div containing the item specified (zero-indexed)
     * @param itemNumber The number of items down on the page you'd like to select (zero-indexed)
     * @return The WebElement representing the whole Item (call findElement() off of this element to find what you're looking for)
     */
    private WebElement getItemNumber(int itemNumber) {
        return inventoryList.findElement(By.cssSelector(String.format("div.inventory_item:nth-of-type(%d)", itemNumber)));
    }

    /**
     * For the Nth item on the page (starting with zero), what is the name of the item?
     * @param itemNumber The number of items down on the page you'd like to select (starting with zero)
     * @return The name of the item at <itemNumber>
     */
    public String getItemName(int itemNumber) {
        WebElement itemName = getItemNumber(itemNumber).findElement(By.className("inventory_item_name"));
        return itemName.getText();
    }

    /**
     * For the Nth item on the page (starting with zero), what is the item description?
     * @param itemNumber The number of items down on the page you'd like to select (starting with zero)
     * @return The Description of the item at <itemNumber>
     */
    public String getItemDescription(int itemNumber) {
        WebElement itemDesc = getItemNumber(itemNumber).findElement(By.className("inventory_item_desc"));
        return itemDesc.getText();
    }

    /**
     * For the Nth item on the page (starting with zero), what is the price of the item?
     * @param itemNumber The number of items down on the page you'd like to select (starting with zero)
     * @return The price of the item at <itemNumber>
     */
    public String getItemPrice(int itemNumber) {
        WebElement itemPrice = getItemNumber(itemNumber).findElement(By.className("inventory_item_price"));
        return itemPrice.getText();
    }

    /**
     * Adds the Nth item on the page to the cart (starting with zero)
     * @param itemNumber The number of items down on the page you'd like to add to the cart (starting with zero)
     */
    public void addItemToCart(int itemNumber) {
        WebElement addToCart = getItemNumber(itemNumber).findElement(By.className("btn_primary"));
        addToCart.click();
    }

    public boolean isOnPage() {
        return inventoryHeader.isDisplayed();
    }

    public boolean itemAddedToCart(int itemNumber) {
        return getItemNumber(itemNumber).findElement(By.className("btn_secondary")).isDisplayed();
    }
    public int getNumberOfItemsInCart() {
        return Integer.valueOf(shoppingCartBadge.getText());
    }

    public int getItemsInPage() {
        return Integer.valueOf(inventoryContainer.getText());
    }
}
