package poptests;

import driverfactory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public void setupDriver() {
        driver.set(DriverFactory.getDriver());
        driver.get().manage().window().maximize();
    }

    @AfterMethod
    public void quitDriver() {
        driver.get().quit();
    }
}
