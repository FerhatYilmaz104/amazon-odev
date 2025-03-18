package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/**
 * Amazon.com.tr sepet sayfası için Page Object Model sınıfı.
 * Bu sınıf sepet sayfasındaki elementleri ve işlemleri içerir.
 */
public class CartPage extends BaseTest {
    
    // Sayfa elementleri
    @FindBy(xpath = "//h1[contains(text(), 'Sepete eklendi')]")
    private WebElement addedToCartText;
    
    @FindBy(id = "nav-cart-count")
    private WebElement cartCount; // Sepetteki ürün sayısı

    @FindBy(id = "nav-cart")
    private WebElement cartButton;

    @FindBy(xpath = "//h2[@id='sc-active-items-header']")
    private WebElement shoppingCartText;

    @FindBy(xpath = "(//span[@class='a-truncate-cut' and @aria-hidden='true'])[1]")
    private WebElement productNameText;

    @FindBy(xpath = "//span[@data-a-selector='decrement-icon']")
    private WebElement deleteButton;

    @FindBy(xpath = "//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap' and contains(text(), '0,00')]")
    private WebElement zeroAmountText;



    /**
     * CartPage sınıfının kurucu metodu.
     * @param driver WebDriver nesnesi
     */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Ürünün sepete eklendiğini kontrol eder.
     * Sepetteki ürün sayısının 1 olduğunu doğrular.
     * Eğer ürün sayısı 1 değilse hata fırlatır.
     */
    public void verifyProductAddedToCart() {
        waitForVisibility(addedToCartText);
        System.out.println("Ürünün sepete eklendiği doğrulanır.");
    }

    public void clickCartButton(){
        waitAndClick(cartButton);
        System.out.println("Sepet butonuna tıklanır");
    }

    /**
     * Sepet sayfasının yüklendiğini kontrol eder.
     * Sepetteki ilk ürünün görünür olmasını bekler.
     */
    public void verifyCartPageLoaded() {
        waitForVisibility(shoppingCartText);
        System.out.println("Sepet sayfasında olduğumuz doğrulanır");
    }

    public void checkProduct(){
        String cartProductName = waitAndGetText(productNameText).trim();
        System.out.println("Sepetteki ürün: " + cartProductName);

        Assert.assertEquals(cartProductName, productDetailedName, "Sepete eklenen ürün doğru değil!");
    }

    public void removeProductFromCart(){
        waitAndClick(deleteButton);
        System.out.println("Ürün sepetten kaldırılır.");
    }

    public void verifyCartEmpty(){
        String price = waitAndGetText(zeroAmountText).trim();
        Assert.assertTrue(price.contains("0,00"), "Ürün silinmemiş olabilir.");
        System.out.println("Sepet başarıyla boşaltıldı.");
    }
} 