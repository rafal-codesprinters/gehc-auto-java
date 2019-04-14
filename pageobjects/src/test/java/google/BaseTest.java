package google;

import driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void startDriver() {
       driver.set(DriverFactory.getDriver());
    }

    @AfterMethod
    public void quitDriver() {
       driver.get().quit();
    }

    public void waitForElementPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
