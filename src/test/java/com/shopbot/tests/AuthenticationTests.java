package com.shopbot.tests;

import com.shopbot.pages.LoginPage;
import com.shopbot.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AuthenticationTests extends BaseTest {
    @DataProvider(name = "loginUsers")
    public Object[][] loginUsers() {
        return new Object[][]{
                {"standardUser", true, ""},
                {"lockedOutUser", false, "locked out"},
                {"invalidUser", false, "do not match"}
        };
    }

    @Test(dataProvider = "loginUsers")
    public void verifyLoginWithMultipleUserTypes(String usernameKey, boolean shouldLogin, String expectedMessage) {
        LoginPage loginPage = loginPage();
        ProductListPage productListPage = loginPage.loginAs(config(usernameKey), config("password"));

        if (shouldLogin) {
            Assert.assertTrue(productListPage.isLoaded(), "Standard user should reach the products page");
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().contains(expectedMessage), "Login error message should match user type");
        }
    }

    @Test
    public void verifySuccessfulLoginWithStandardUser() {
        ProductListPage productListPage = loginAsStandardUser();
        Assert.assertTrue(productListPage.isLoaded(), "Products page should load after valid login");
    }

    @Test
    public void verifyLockedOutUserSeesCorrectError() {
        LoginPage loginPage = loginPage();
        loginPage.attemptLogin(config("lockedOutUser"), config("password"));
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"), "Locked out user should be blocked");
    }

    @Test
    public void verifyEmptyUsernameAndPasswordShowsError() {
        LoginPage loginPage = loginPage();
        loginPage.submitEmptyLogin();
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Empty login should show username error");
    }

    @Test
    public void verifyLogoutRedirectsToLoginPage() {
        ProductListPage productListPage = loginAsStandardUser();
        LoginPage loginPage = productListPage.logout();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Logout should return to login page");
    }
}
