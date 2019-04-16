package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Collectors;

public class GoogleResultsPage extends GooglePage {


    public static final By LOC_SEARCH_RESULTS = By.id("search");
    public static final By LOC_RESULT_ANCHOR = By.cssSelector(".r > a");

    public GoogleResultsPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(LOC_SEARCH_RESULTS));
    }

    public Boolean hasResults(String resultUrl) {
        return driver.findElements(By.className("rc"))
                .stream()
                .filter(res -> res.findElement(LOC_RESULT_ANCHOR).getAttribute("href").equals(resultUrl))
                .collect(Collectors.toList())
                .size() > 0;
    }
}
