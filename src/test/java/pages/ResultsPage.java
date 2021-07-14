package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {
    private final WebDriver driver;

    @FindBy(id="totalCount")
    private WebElement totalCount;

    @FindBy(className = "site-footer")
    private WebElement footer;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(totalCount));
        wait.until(ExpectedConditions.visibilityOf(footer));
    }

    public int getTotalCount() {
        return Integer.parseInt(totalCount.getText());
    }
}
