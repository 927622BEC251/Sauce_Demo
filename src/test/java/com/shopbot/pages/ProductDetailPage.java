package com.shopbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {
    private final By productName = By.cssSelector("[data-test='inventory-item-name']");
    private final By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private final By backToProductsButton = By.id("back-to-products");
    private final By addToCartButton = By.cssSelector("button[data-test^='add-to-cart']");
    private final By removeButton = By.cssSelector("button[data-test^='remove']");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return textOf(productName);
    }

    public String getProductPrice() {
        return textOf(productPrice);
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public boolean isRemoveButtonDisplayed() {
        return isDisplayed(removeButton);
    }

    public ProductListPage backToProducts() {
        click(backToProductsButton);
        return new ProductListPage(driver);
    }
}
