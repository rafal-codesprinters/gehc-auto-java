package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericPage {
    protected final WebDriver driver;

    public GenericPage(WebDriver webDriver) {
        driver = webDriver;
    }

    protected void waitForElementPresence(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(selector));
    }
}
