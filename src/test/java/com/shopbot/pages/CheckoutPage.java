package com.shopbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutPage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By title = By.cssSelector("[data-test='title']");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By subtotal = By.cssSelector("[data-test='subtotal-label']");
    private final By total = By.cssSelector("[data-test='total-label']");
    private final By confirmationMessage = By.cssSelector("[data-test='complete-header']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCustomerDetails(String firstName, String lastName, String postalCode) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
        jsClick(continueButton);
    }

    public String getPageTitle() {
        return textOf(title);
    }

    public List<String> getSummaryProductNames() {
        return waitForElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getSubtotalText() {
        return textOf(subtotal);
    }

    public String getTotalText() {
        return textOf(total);
    }

    public void finishOrder() {
        jsClick(finishButton);
    }

    public String getConfirmationMessage() {
        return textOf(confirmationMessage);
    }
}
