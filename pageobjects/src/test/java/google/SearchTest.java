package google;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class SearchTest extends BaseTest {

    @Test
    public void canOpenGooglePage() {
        driver.get().navigate().to("http://google.com");
        By locator = By.name("q");
        waitForElementPresent(locator);
        assert driver.get().findElements(locator).size() == 1;
    }

    @Test
    public void canOpenYahooPage() {
        driver.get().navigate().to("http://yahoo.com");
        By locator = By.name("agree");
        waitForElementPresent(locator);
        assert driver.get().findElements(locator).size() == 1;
    }

    @Test
    public void canOpenCodeSprintersPage() {
        driver.get().navigate().to("http://agileszkolenia.pl");
        By locator = By.id("mce-FNAME");
        waitForElementPresent(locator);
        assert driver.get().findElements(locator).size() == 1;
    }

    @Test
    public void canOpenMicrosoftPage() {
        driver.get().navigate().to("http://microsoft.com");
        By locator = By.id("mectrl_headerPicture");
        waitForElementPresent(locator);
        assert driver.get().findElements(locator).size() == 1;
    }
}

