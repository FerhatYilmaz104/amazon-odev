package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage extends BaseTest {

    @FindBy(xpath = "//div[@id='addToCart_feature_div']//input[@id='add-to-cart-button']\n")
    private WebElement addToCartButton;

    @FindBy(id = "buy-now-button")
    private WebElement buyNowButton;

    @FindBy(xpath = "//span[@id='productTitle' and contains(@class, 'product-title-word-break')]")
    private WebElement productNameText;



    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void verifyProductDetailPageLoaded() {
        waitForVisibility(buyNowButton);
        System.out.println("Detay sayfasında olduğumuz doğrulanır");
    }

    public void getProductName(){
        productDetailedName = waitAndGetText(productNameText).trim();
        System.out.println("Detay sayfasındaki ürün: " + productDetailedName);
    }


    public void addToCart() {
        waitAndClick(addToCartButton);
        System.out.println("Sepete ekle butonuna tıklanır");
    }
} 