package com.shopbot.tests;

import com.shopbot.driver.DriverManager;
import com.shopbot.pages.LoginPage;
import com.shopbot.utils.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void launchApplication() {
        DriverManager.createDriver();
        DriverManager.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void closeApplication() {
        DriverManager.quitDriver();
    }

    protected LoginPage loginPage() {
        return new LoginPage(DriverManager.getDriver());
    }

    protected com.shopbot.pages.ProductListPage loginAsStandardUser() {
        return loginPage().loginAs(config("standardUser"), config("password"));
    }

    protected com.shopbot.pages.ProductListPage loginAsProblemUser() {
        return loginPage().loginAs(config("problemUser"), config("password"));
    }

    protected String config(String key) {
        return ConfigReader.get(key);
    }
}
