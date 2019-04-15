package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleResultsPage {

    private final WebDriver driver;

    public GoogleResultsPage(WebDriver webDriver) {
        driver = webDriver;
    }

    public Boolean hasResults(String resultUrl) {
        return driver.findElements(By.className("rc"))
                .stream()
                .filter(res -> res.findElement(By.cssSelector(".r > a")).getAttribute("href").equals(resultUrl))
                .collect(Collectors.toList())
                .size() > 0;
    }
}
