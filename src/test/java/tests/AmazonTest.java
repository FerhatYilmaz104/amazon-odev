package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.ProductDetailPage;
import pages.CartPage;

/**
 * Amazon.com.tr için test senaryolarını içeren test sınıfı.
 * Bu sınıf, alışveriş akışını test eden senaryoları içerir.
 */
public class AmazonTest extends BaseTest {
    
    /**
     * Amazon alışveriş akışını test eden senaryo.
     * Bu test:
     * 1. Ana sayfaya gider
     * 2. Ürün arar
     * 3. Arama sonuçlarından ürün seçer
     * 4. Ürünü sepete ekler
     * 5. Sepette ürünü kontrol eder
     */
    @Test
    public void testAmazonShoppingFlow() {
        // Ana sayfaya git ve yüklendiğini kontrol et
        HomePage homePage = new HomePage(driver);
        homePage.verifyHomePageLoaded();
        
        // Ürün ara
        homePage.searchProduct("Samsung");


        
        // Arama sonuçlarından 3. ürünü seç
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySearchResultsLoaded();
        searchResultsPage.clickSecondSearchPageButton();
        searchResultsPage.verifySecondSearchPageLoaded();
        searchResultsPage.clickThirdProduct();


        // Ürün detay sayfasında sepete ekle
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.verifyProductDetailPageLoaded();
        productDetailPage.getProductName();
        productDetailPage.addToCart();

       // Sepette ürünü kontrol et
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