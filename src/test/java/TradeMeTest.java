import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ResultsPage;

import java.util.List;

public class TradeMeTest {

    public WebDriver driver;
    private HomePage homePage;
    private ResultsPage resultsPage;

    @BeforeAll
    private static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    private void teardownBrowser() {
        if (driver != null){
            driver.quit();
        }
    }

    @BeforeEach
    public void setupBrowser(){
        driver = new ChromeDriver();
        driver.get("https://www.tmsandbox.co.nz/");
        homePage = new HomePage(driver);
    }

    @Test
    public void returnKey() throws Exception {

        homePage.searchFor("gold");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("site-footer")));

        /*
        String price = driver.findElement(By.id("SuperGridGallery_BucketList_ClassifiedPrice_listingClassifiedPriceAmountPoa")).getText();
        System.out.println(price);
        */

        driver.findElement(By.id("ListingViewBar_listViewTab_icon_a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("site-footer")));

        Select select = new Select(driver.findElement(By.id("listingTitleBarSelect")));
        select.selectByVisibleText("Lowest Buy Now");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("site-footer")));

        List<WebElement> lowestPrices = driver.findElements(By.className("listingBidPrice"));
        lowestPrices.addAll(driver.findElements(By.className("listing-classified-price-amount")));

        for (int i = 0; i < 3; i++){
            System.out.println(i+1 + ") " + lowestPrices.get(i).getText());
        }

    }

    @Test
    public void printTopTenListings() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("site-footer")));

        List<WebElement> titles = driver.findElements(By.cssSelector(".title"));

        for (int i = 0; i < 10; i++){
            System.out.println(i+1 + ") " + titles.get(i).getText());
        }
    }


    @Test
    public void printOutNumberOfListings() throws InterruptedException {
        resultsPage = homePage.searchFor("gold");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("site-footer")));
        int listingCount = resultsPage.getTotalCount();
        System.out.println(listingCount);
    }



    /*
    @Test
    public void submitForm() throws Exception {

        WebElement queryBox = driver.findElement(By.id("searchString"));
        queryBox.sendKeys("gold");
        driver.findElement(By.id("generalSearch")).submit();
        Thread.sleep(5000);

    }

    @Test
    public void searchButton() throws Exception {

        WebElement queryBox = driver.findElement(By.id("searchString"));
        queryBox.sendKeys("gold");
        driver.findElement(By.xpath("//*[@id='generalSearch']/div[2]/button")).click();
        Thread.sleep(5000);

    }

     */

}
