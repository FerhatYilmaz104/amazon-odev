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

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;


    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        

        options.addArguments("--start-maximized"); // Tarayıcıyı tam ekran aç
        options.addArguments("--disable-notifications"); // Bildirimleri kapat
        options.addArguments("--disable-popup-blocking"); // Pop-up engelleyiciyi kapat
        

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        driver.get("https://www.amazon.com.tr");

        acceptCookies();
    }


    private void acceptCookies() {
        try {

            WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("sp-cc-accept")
            ));
            cookieAcceptButton.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie popup'ı görünmedi veya zaten kabul edilmiş.");
        }
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    protected void waitAndSendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }


    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    protected String waitAndGetText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public static String productDetailedName;
} 