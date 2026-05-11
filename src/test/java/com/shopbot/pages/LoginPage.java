package com.shopbot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage loginAs(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        return new ProductListPage(driver);
    }

    public void attemptLogin(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
    }

    public void submitEmptyLogin() {
        click(loginButton);
    }

    public String getErrorMessage() {
        return textOf(errorMessage);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }
}
