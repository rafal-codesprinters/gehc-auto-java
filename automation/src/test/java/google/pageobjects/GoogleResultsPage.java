package google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.page.GenericPage;

public class GoogleResultsPage extends GenericPage {
    private static final By LOC_SEARCH_RESULTS = By.id("search");
    private static final By LOC_RESULT_ANCHOR = By.cssSelector(".r > a");

    GoogleResultsPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(LOC_SEARCH_RESULTS));
    }

    public Boolean hasResults(String resultUrl) {
        return driver.findElements(By.className("rc"))
                .stream()
                .anyMatch(res -> res.findElement(LOC_RESULT_ANCHOR).getAttribute("href").equals(resultUrl));
    }
}
