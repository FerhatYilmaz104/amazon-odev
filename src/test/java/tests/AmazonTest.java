package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.ProductDetailPage;
import pages.CartPage;


public class AmazonTest extends BaseTest {
    

    @Test
    public void testAmazonShoppingFlow() {

        HomePage homePage = new HomePage(driver);
        homePage.verifyHomePageLoaded();
        
        homePage.searchProduct("Samsung");

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySearchResultsLoaded();
        searchResultsPage.clickSecondSearchPageButton();
        searchResultsPage.verifySecondSearchPageLoaded();
        searchResultsPage.clickThirdProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.verifyProductDetailPageLoaded();
        productDetailPage.getProductName();
        productDetailPage.addToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.verifyProductAddedToCart();
        cartPage.clickCartButton();
        cartPage.verifyCartPageLoaded();
        cartPage.checkProduct();
        cartPage.removeProductFromCart();
        cartPage.verifyCartEmpty();

        homePage.navigateToHomePage();
    }
} 