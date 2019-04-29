package driverfactory;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class DriverFactory {
    public static WebDriver getDriver() {

        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy("proxy.net:3337");
        proxy.setSslProxy("proxy.net:3337");

        if (System.getProperty("grid.address") != null && System.getProperty("grid.port") !=null) {
            URL gridUrl = null;
            try {
                gridUrl = new URL("http://" + System.getProperty("grid.address") + ":" + System.getProperty("grid.port") + "/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            switch (Browser.valueOf(System.getProperty("browser", System.getProperty("default.browser")).toUpperCase())) {
                case EDGE:
                    EdgeOptions edgeOptions= new EdgeOptions();
                    return new RemoteWebDriver(gridUrl, edgeOptions);
                case MSIE:
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    return new RemoteWebDriver(gridUrl, ieOptions);
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    return new RemoteWebDriver(gridUrl, chromeOptions);
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-headless");
                    return new RemoteWebDriver(gridUrl, firefoxOptions);
            }
        }
        switch (Browser.valueOf(System.getProperty("browser", System.getProperty("default.browser")
                .toUpperCase()))) {
            case CHROME :
                ChromeOptions chromeOptions = new ChromeOptions();
                /*chromeOptions.setCapability(CapabilityType.PROXY, proxy);
                chromeOptions.addArguments("--headless");*/
                return new ChromeDriver(chromeOptions);
            case FIREFOX :
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                /*firefoxOptions.setCapability(CapabilityType.PROXY, proxy);
                firefoxOptions.addArguments("-headless");*/
                return new FirefoxDriver(firefoxOptions);
            case EDGE :
                return new EdgeDriver();
            case MSIE :
                return new InternetExplorerDriver();
        }

        return null;

    }
}
