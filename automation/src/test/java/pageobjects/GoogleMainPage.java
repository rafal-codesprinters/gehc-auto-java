package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage {
    public static final String GOOGLE_MAIN_PAGE_URL = "http://www.google.com";

    private final WebDriver driver;

    public GoogleMainPage(WebDriver webDriver) {
        driver = webDriver;
        driver.get(GOOGLE_MAIN_PAGE_URL);
    }

    public GoogleResultsPage searchFor(String searchQuery) {
        WebElement searchBox = driver.findElement(By.cssSelector(".gLFyf.gsfi"));
        searchBox.click();
        searchBox.sendKeys(searchQuery);
        searchBox.submit();
        return new GoogleResultsPage(driver);
    }
}
