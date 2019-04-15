package dayone;

import driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DayOneTests {

    @Test
    public void GoogleTest() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("http://google.com");
        assert driver.findElements(By.name("q")).size() == 1;
        driver.quit();
    }

}
