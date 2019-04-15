package dayone;

import driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DayOneTests {

    final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public void setupDriver() {
        driver.set(DriverFactory.getDriver());
    }

    @Test
    public void googleTest() {

        driver.get().navigate().to("http://google.com");
        assert driver.get().findElements(By.name("q")).size() == 1;
    }

    @Test
    public void csTest() {
        driver.get().get("http://agileszkolenia.pl");
        assert driver.get().findElements(By.id("mce-FNAME")).size() == 1;
    }

    @AfterMethod
    public void quitDriver() {
        driver.get().quit();
    }

}
