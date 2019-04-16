package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericPage {
    protected final WebDriver driver;

    public GenericPage(WebDriver webDriver) {
        driver = webDriver;
    }

    protected WebElement waitForElementPresence(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    protected void waitForElementNotPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}
