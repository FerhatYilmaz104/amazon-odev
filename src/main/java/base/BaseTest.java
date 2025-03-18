package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * BaseTest sınıfı, tüm test sınıfları için temel işlevselliği sağlar.
 * Bu sınıf:
 * - WebDriver kurulumunu yapar
 * - Ortak bekleme metodlarını içerir
 * - Cookie yönetimini sağlar
 * - Tarayıcı ayarlarını yapar
 */
public class BaseTest {
    // WebDriver ve WebDriverWait nesneleri tüm alt sınıflar tarafından kullanılabilir
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Her test metodundan önce çalışır.
     * WebDriver'ı hazırlar ve başlatır.
     */
    @BeforeMethod
    public void setUp() {
        // WebDriver'ı hazırla
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Temel ayarlar
        options.addArguments("--start-maximized"); // Tarayıcıyı tam ekran aç
        options.addArguments("--disable-notifications"); // Bildirimleri kapat
        options.addArguments("--disable-popup-blocking"); // Pop-up engelleyiciyi kapat
        
        // WebDriver'ı başlat
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Amazon.com.tr'ye git
        driver.get("https://www.amazon.com.tr");
        
        // Cookie'leri kabul et
        acceptCookies();
    }

    /**
     * Amazon'un cookie popup'ını otomatik olarak kabul eder.
     * Eğer popup görünmezse veya zaten kabul edilmişse hata vermeden devam eder.
     */
    private void acceptCookies() {
        try {
            // Cookie kabul butonunu bul ve tıkla
            WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("sp-cc-accept")
            ));
            cookieAcceptButton.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie popup'ı görünmedi veya zaten kabul edilmiş.");
        }
    }

    /**
     * Her test metodundan sonra çalışır.
     * WebDriver'ı kapatır.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Bir elementin tıklanabilir olmasını bekler ve tıklar.
     * @param element Tıklanacak element
     */
    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Bir elementin görünür olmasını bekler ve metin girer.
     * @param element Metin girilecek element
     * @param text Girilecek metin
     */
    protected void waitAndSendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    /**
     * Bir elementin görünür olmasını bekler.
     * @param element Beklenecek element
     */
    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Bir elementin görünür olmasını bekler ve metnini alır.
     * @param element Metni alınacak element
     * @return Elementin metni
     */
    protected String waitAndGetText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public static String productDetailedName;
} 