package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static driverfactory.Browser.*;


public abstract class DriverFactory {
    public static WebDriver getDriver() {

        switch (Browser.valueOf(System.getProperty("browser", System.getProperty("default.browser")
                .toUpperCase()))) {
            case CHROME :
                return new ChromeDriver();
            case FIREFOX :
                return new FirefoxDriver();
            case EDGE :
                return new EdgeDriver();
            case MSIE :
                return new InternetExplorerDriver();
        }

        return null;

    }
}
