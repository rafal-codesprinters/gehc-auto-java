package dayone;

import driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.GoogleMainPage;
import pageobjects.GoogleResultsPage;

public class DayOneTests extends BaseTest {

    @Test
    public void googlePopTest() {
        // arrange
        GoogleMainPage mainPage = new GoogleMainPage(driver.get());
        // act
        GoogleResultsPage resultsPage = mainPage.searchFor("codesprinters");
        // assert
        Assert.assertTrue(resultsPage.hasResults("http://agileszkolenia.pl/"));
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

}
