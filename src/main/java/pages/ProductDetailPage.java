package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Amazon.com.tr ürün detay sayfası için Page Object Model sınıfı.
 * Bu sınıf ürün detay sayfasındaki elementleri ve işlemleri içerir.
 */
public class ProductDetailPage extends BaseTest {
    // Sayfa elementleri
    @FindBy(xpath = "//div[@id='addToCart_feature_div']//input[@id='add-to-cart-button']\n")
    private WebElement addToCartButton; // Sepete ekle butonu

    @FindBy(id = "nav-cart-count")
    private WebElement cartCount; // Sepetteki ürün sayısı

    @FindBy(id = "buy-now-button")
    private WebElement buyNowButton;

    @FindBy(id = "gw_layout")
    private WebElement mainLayout; // Sayfa layout elementi

    @FindBy(xpath = "//span[@id='productTitle' and contains(@class, 'product-title-word-break')]")
    private WebElement productNameText;


    /**
     * ProductDetailPage sınıfının kurucu metodu.
     * @param driver WebDriver nesnesi
     */
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Ürün detay sayfasının yüklendiğini kontrol eder.
     * Ürün başlığının görünür olmasını bekler.
     */
    public void verifyProductDetailPageLoaded() {
        waitForVisibility(buyNowButton);
        System.out.println("Detay sayfasında olduğumuz doğrulanır");
    }

    public void getProductName(){
        productDetailedName = waitAndGetText(productNameText).trim();
        System.out.println("Detay sayfasındaki ürün: " + productDetailedName);
    }



    /**
     * Ürünü sepete ekler.
     * Sepete ekle butonuna tıklar.
     */
    public void addToCart() {
        waitAndClick(addToCartButton);
        System.out.println("Sepete ekle butonuna tıklanır");
    }
} 