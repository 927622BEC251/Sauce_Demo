package com.shopbot.tests;

import com.shopbot.pages.ProductDetailPage;
import com.shopbot.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {
    @Test
    public void verifyProductListingShowsProducts() {
        ProductListPage productListPage = loginAsStandardUser();
        Assert.assertTrue(productListPage.getProductCount() > 0, "At least one product should be visible");
    }

    @Test
    public void verifySortProductsByNameAToZ() {
        ProductListPage productListPage = loginAsStandardUser();
        productListPage.sortByNameAscending();
        Assert.assertEquals(productListPage.getFirstProductName(), "Sauce Labs Backpack");
    }

    @Test
    public void verifySortProductsByPriceLowToHigh() {
        ProductListPage productListPage = loginAsStandardUser();
        productListPage.sortByPriceLowToHigh();
        Assert.assertEquals(productListPage.getFirstProductPrice(), "$7.99");
    }

    @Test
    public void verifyProductDetailNameAndPriceMatchListing() {
        ProductListPage productListPage = loginAsStandardUser();
        String listingName = productListPage.getFirstProductName();
        String listingPrice = productListPage.getFirstProductPrice();

        ProductDetailPage detailPage = productListPage.openFirstProductDetail();

        Assert.assertEquals(detailPage.getProductName(), listingName);
        Assert.assertEquals(detailPage.getProductPrice(), listingPrice);
    }

    @Test
    public void verifyBackNavigationFromProductDetailReturnsToProductListing() {
        ProductListPage productListPage = loginAsStandardUser();
        ProductDetailPage detailPage = productListPage.openFirstProductDetail();
        ProductListPage returnedListPage = detailPage.backToProducts();
        Assert.assertTrue(returnedListPage.isLoaded(), "Back button should return to product listing");
    }
}
