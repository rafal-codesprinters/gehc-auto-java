package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage extends GooglePage {
    public static final String GOOGLE_MAIN_PAGE_URL = "http://www.google.com";
    public static final By LOC_SEARCH_BOX = By.cssSelector(".gLFyf.gsfi");

    public GoogleMainPage(WebDriver webDriver) {
        super(webDriver);
        driver.get(GOOGLE_MAIN_PAGE_URL);
    }

    public GoogleResultsPage searchFor(String searchQuery) {
        WebElement searchBox = driver.findElement(LOC_SEARCH_BOX);
        searchBox.click();
        searchBox.sendKeys(searchQuery);
        searchBox.submit();
        return new GoogleResultsPage(driver);
    }
}
