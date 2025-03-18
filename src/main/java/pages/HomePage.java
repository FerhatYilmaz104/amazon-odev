package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Amazon.com.tr ana sayfası için Page Object Model sınıfı.
 * Bu sınıf ana sayfadaki elementleri ve işlemleri içerir.
 */
public class HomePage extends BaseTest {
    // Sayfa elementleri
    @FindBy(xpath = "//form[@id='nav-search-bar-form']//input[@id='twotabsearchtextbox']")
    private WebElement searchBox; // Arama kutusu

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton; // Arama butonu

    @FindBy(id = "pageContent")
    private WebElement mainLayout; // Ana sayfa layout elementi

    /**
     * HomePage sınıfının kurucu metodu.
     * @param driver WebDriver nesnesi
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Ana sayfanın yüklendiğini kontrol eder.
     * Ana sayfa layout elementinin görünür olmasını bekler.
     */
    public void verifyHomePageLoaded() {
        waitForVisibility(mainLayout);
        System.out.println("Anasayfada olduğu doğrulanır");
    }

    /**
     * Belirtilen ürünü arar.
     * @param productName Aranacak ürün adı
     */
    public void searchProduct(String productName) {
        waitAndSendKeys(searchBox, productName);
        waitAndClick(searchButton);
    }

    /**
     * Ana sayfaya döner ve yüklendiğini kontrol eder.
     */
    public void navigateToHomePage() {
        driver.get("https://www.amazon.com.tr");
        verifyHomePageLoaded();
    }
} 