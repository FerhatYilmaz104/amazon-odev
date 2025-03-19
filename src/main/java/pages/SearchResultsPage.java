package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class SearchResultsPage extends BaseTest {
    

    @FindBy(xpath = "//h2[.//span[contains(text(), 'Aranan ürün:')] and .//span[contains(text(), 'Samsung')]]")
    private WebElement searchResults;

    @FindBy(xpath = "//a[@aria-label='2 sayfasına git' and text()='2']")
    private WebElement secondSearchPageBtn;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    private List<WebElement> productList;


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    

    public void verifySearchResultsLoaded() {
        if (searchResults.isDisplayed()) {
            System.out.println("Samsung sonuçları başarıyla listelendi.");
        } else {
            System.out.println("Samsung sonuçları görünmüyor.");
        }
    }

    public void clickSecondSearchPageButton(){
        waitAndClick(secondSearchPageBtn);
        System.out.println("2.sayfaya geçiliyor");

    }

    public void verifySecondSearchPageLoaded(){
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("page=2")) {
            System.out.println("2. sayfa başarıyla açıldı.");
        } else {
            System.out.println("2. sayfa AÇILMADI!");
        }
    }
    

    public void clickThirdProduct() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitAndClick(productList.get(2));
        System.out.println("3.ürüne tıklanır");
    }
} 