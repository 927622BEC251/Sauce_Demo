package com.shopbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductListPage extends BasePage {
    private final By title = By.cssSelector("[data-test='title']");
    private final By productCards = By.cssSelector("[data-test='inventory-item']");
    private final By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By productPrices = By.cssSelector("[data-test='inventory-item-price']");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By productImages = By.cssSelector(".inventory_item_img img");

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return textOf(title).equals("Products");
    }

    public int getProductCount() {
        return waitForElements(productCards).size();
    }

    public void sortByNameAscending() {
        selectByValue(sortDropdown, "az");
    }

    public void sortByPriceLowToHigh() {
        selectByValue(sortDropdown, "lohi");
    }

    public String getFirstProductName() {
        return waitForElements(productNames).get(0).getText();
    }

    public String getFirstProductPrice() {
        return waitForElements(productPrices).get(0).getText();
    }

    public double getFirstProductPriceValue() {
        return parsePrice(getFirstProductPrice());
    }

    public List<Double> getAllProductPrices() {
        return waitForElements(productPrices).stream()
                .map(WebElement::getText)
                .map(this::parsePrice)
                .collect(Collectors.toList());
    }

    public ProductDetailPage openFirstProductDetail() {
        click(productNames);
        return new ProductDetailPage(driver);
    }

    public void addFirstProductToCart() {
        waitForElements(productCards).get(0).findElement(By.tagName("button")).click();
    }

    public String addFirstProductToCartAndGetName() {
        WebElement product = waitForElements(productCards).get(0);
        String name = product.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText();
        product.findElement(By.tagName("button")).click();
        return name;
    }

    public void addProductsToCart(int productCount) {
        List<WebElement> products = waitForElements(productCards);
        for (int index = 0; index < productCount; index++) {
            products.get(index).findElement(By.tagName("button")).click();
        }
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(textOf(cartBadge));
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public LoginPage logout() {
        jsClick(menuButton);
        waitForVisible(logoutLink);
        jsClick(logoutLink);
        return new LoginPage(driver);
    }

    public boolean allProductImagesShareSameBrokenSource() {
        List<String> sources = waitForElements(productImages).stream()
                .map(image -> image.getAttribute("src"))
                .distinct()
                .collect(Collectors.toList());
        return sources.size() == 1 && sources.get(0).contains("sl-404");
    }

    public String getFirstProductImageSource() {
        return waitForElements(productImages).get(0).getAttribute("src");
    }

    public boolean isInventorySortedByVisiblePrice() {
        List<Double> prices = getAllProductPrices();
        for (int index = 1; index < prices.size(); index++) {
            if (prices.get(index - 1) > prices.get(index)) {
                return false;
            }
        }
        return true;
    }

    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
    }
}
