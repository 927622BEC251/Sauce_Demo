package com.shopbot.tests;

import com.shopbot.pages.CartPage;
import com.shopbot.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
    @Test
    public void verifyAddOneProductUpdatesCartBadgeToOne() {
        ProductListPage productListPage = loginAsStandardUser();
        productListPage.addFirstProductToCart();
        Assert.assertEquals(productListPage.getCartBadgeCount(), 1);
    }

    @Test
    public void verifyAddTwoProductsUpdatesCartBadgeToTwo() {
        ProductListPage productListPage = loginAsStandardUser();
        productListPage.addProductsToCart(2);
        Assert.assertEquals(productListPage.getCartBadgeCount(), 2);
    }

    @Test
    public void verifyRemoveProductFromCartPage() {
        ProductListPage productListPage = loginAsStandardUser();
        String productName = productListPage.addFirstProductToCartAndGetName();
        CartPage cartPage = productListPage.openCart();

        cartPage.removeFirstItem();

        Assert.assertFalse(cartPage.containsProduct(productName), "Removed product should not appear in cart");
    }

    @Test
    public void verifyCartRetainsItemsAfterNavigatingBackToProductListing() {
        ProductListPage productListPage = loginAsStandardUser();
        productListPage.addFirstProductToCart();
        CartPage cartPage = productListPage.openCart();
        ProductListPage returnedProductListPage = cartPage.continueShopping();

        Assert.assertEquals(returnedProductListPage.getCartBadgeCount(), 1);
    }

    @Test
    public void verifyCartPageShowsAddedProductName() {
        ProductListPage productListPage = loginAsStandardUser();
        String productName = productListPage.addFirstProductToCartAndGetName();
        CartPage cartPage = productListPage.openCart();

        Assert.assertTrue(cartPage.containsProduct(productName), "Cart should contain the added product");
    }
}
