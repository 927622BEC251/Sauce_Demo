package com.shopbot.tests;

import com.shopbot.pages.ProductDetailPage;
import com.shopbot.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProblemUserTests extends BaseTest {
    @Test
    public void verifyProblemUserCanLoginToProductListing() {
        ProductListPage productListPage = loginAsProblemUser();
        Assert.assertTrue(productListPage.isLoaded(), "Problem user should reach product listing page");
    }

    @Test
    public void verifyProblemUserProductImagesUseBrokenAsset() {
        ProductListPage productListPage = loginAsProblemUser();
        Assert.assertTrue(productListPage.getFirstProductImageSource().contains("sl-404"), "Problem user should see broken product image asset");
    }

    @Test
    public void verifyProblemUserImagesAreNotLoadingCorrectly() {
        ProductListPage productListPage = loginAsProblemUser();
        Assert.assertTrue(productListPage.allProductImagesShareSameBrokenSource(), "Problem user images should share the broken source");
    }

    @Test
    public void verifyProblemUserAddProductBehaviorUpdatesCartBadge() {
        ProductListPage productListPage = loginAsProblemUser();
        productListPage.addFirstProductToCart();
        Assert.assertEquals(productListPage.getCartBadgeCount(), 1, "Problem user add behavior is documented by badge update");
    }

    @Test
    public void verifyProblemUserProductDetailShowsBrokenNavigationBehavior() {
        ProductListPage productListPage = loginAsProblemUser();
        String listingName = productListPage.getFirstProductName();
        ProductDetailPage detailPage = productListPage.openFirstProductDetail();

        Assert.assertNotEquals(detailPage.getProductName(), listingName, "Problem user product detail link should expose broken behavior");
    }
}
