package steps;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ResultsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeMeSteps {

    private ChromeDriver driver;
    private HomePage homePage;
    private ResultsPage resultsPage;

    @Given("I am conducting a TradeMe search")
    public void i_am_conducting_a_trade_me_search() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.tmsandbox.co.nz/");
        homePage = new HomePage(driver);
    }

    @When("I search for {string}")
    public void i_search_for(String string) {
        homePage.searchFor(string);
    }

    @Then("I see {int} results")
    public void i_see_results(Integer int1) {
        assertEquals(int1, resultsPage.getTotalCount());
    }

    @After
    public void cleanUp(){
        driver.quit();
    }

}
