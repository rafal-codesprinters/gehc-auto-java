package pageobjects.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericPage {
    protected final WebDriver driver;

    public GenericPage(WebDriver webDriver) {
        driver = webDriver;
    }

    protected void waitForElementPresence(By selector) {
        int timeOutInSeconds = 10;
        WebDriverWait wait = foo(timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    private WebDriverWait foo(int timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds);
    }

    protected void waitForElementNotPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    protected void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    protected void waitForElementClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
