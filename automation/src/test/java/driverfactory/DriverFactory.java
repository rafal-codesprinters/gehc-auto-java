package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class DriverFactory {
    public static WebDriver getDriver() {

        switch (System.getProperty("browser", System.getProperty("default.browser"))) {
            case "CHROME" :
                return new ChromeDriver();
            case "FIREFOX" :
                return new FirefoxDriver();
        }

        return null;

    }
}
