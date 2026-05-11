package com.shopbot.tests;

import com.shopbot.pages.CartPage;
import com.shopbot.pages.CheckoutPage;
import com.shopbot.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {
    @Test
    public void verifyCheckoutAcceptsValidCustomerDetails() {
        CheckoutPage checkoutPage = createCheckoutWithSingleProduct();

        checkoutPage.fillCustomerDetails(config("firstName"), config("lastName"), config("postalCode"));

        Assert.assertEquals(checkoutPage.getPageTitle(), "Checkout: Overview");
    }

    @Test
    public void verifyOrderSummaryShowsCorrectProductName() {
        ProductListPage productListPage = loginAsStandardUser();
        String productName = productListPage.addFirstProductToCartAndGetName();
        CheckoutPage checkoutPage = productListPage.openCart().checkout();

        checkoutPage.fillCustomerDetails(config("firstName"), config("lastName"), config("postalCode"));

        Assert.assertTrue(checkoutPage.getSummaryProductNames().contains(productName), "Summary should show selected product");
    }

    @Test
    public void verifyOrderSummaryShowsCorrectProductPriceSubtotal() {
        ProductListPage productListPage = loginAsStandardUser();
        String productPrice = productListPage.getFirstProductPrice();
        productListPage.addFirstProductToCart();
        CheckoutPage checkoutPage = productListPage.openCart().checkout();

        checkoutPage.fillCustomerDetails(config("firstName"), config("lastName"), config("postalCode"));

        Assert.assertTrue(checkoutPage.getSubtotalText().contains(productPrice.replace("$", "")), "Subtotal should include product price");
    }

    @Test
    public void verifyOrderTotalIsDisplayedOnSummaryPage() {
        CheckoutPage checkoutPage = createCheckoutWithSingleProduct();

        checkoutPage.fillCustomerDetails(config("firstName"), config("lastName"), config("postalCode"));

        Assert.assertTrue(checkoutPage.getTotalText().startsWith("Total: $"), "Total should be visible on overview page");
    }

    @Test
    public void verifyCompleteOrderShowsConfirmationMessage() {
        CheckoutPage checkoutPage = createCheckoutWithSingleProduct();

        checkoutPage.fillCustomerDetails(config("firstName"), config("lastName"), config("postalCode"));
        checkoutPage.finishOrder();

        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }

    private CheckoutPage createCheckoutWithSingleProduct() {
        ProductListPage productListPage = loginAsStandardUser();
        productListPage.addFirstProductToCart();
        CartPage cartPage = productListPage.openCart();
        return cartPage.checkout();
    }
}
