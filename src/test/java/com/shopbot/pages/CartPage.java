package com.shopbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    private final By cartItems = By.cssSelector("[data-test='inventory-item']");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean containsProduct(String productName) {
        return getItemNames().contains(productName);
    }

    public void removeFirstItem() {
        waitForElements(cartItems).get(0).findElement(By.tagName("button")).click();
    }

    public ProductListPage continueShopping() {
        click(continueShoppingButton);
        return new ProductListPage(driver);
    }

    public CheckoutPage checkout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}
