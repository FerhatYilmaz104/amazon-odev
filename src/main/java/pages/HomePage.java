package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePage extends BaseTest {

    @FindBy(xpath = "//form[@id='nav-search-bar-form']//input[@id='twotabsearchtextbox']")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "pageContent")
    private WebElement mainLayout;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void verifyHomePageLoaded() {
        waitForVisibility(mainLayout);
        System.out.println("Anasayfada olduğu doğrulanır");
    }


    public void searchProduct(String productName) {
        waitAndSendKeys(searchBox, productName);
        waitAndClick(searchButton);
    }


    public void navigateToHomePage() {
        driver.get("https://www.amazon.com.tr");
        verifyHomePageLoaded();
    }
} 