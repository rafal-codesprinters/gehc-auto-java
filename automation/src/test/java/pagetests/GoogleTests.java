package pagetests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.google.GoogleMainPage;
import pageobjects.google.GoogleResultsPage;

public class GoogleTests extends BaseTest {
    @Test
    public void googlePopTest() {
        GoogleMainPage mainPage = new GoogleMainPage(getDriver());

        GoogleResultsPage resultsPage = mainPage.searchFor("codesprinters");

        Assert.assertTrue(resultsPage.hasResults("http://agileszkolenia.pl/"));
    }

    @Test
    public void googleTest() {
        getDriver().navigate().to("http://google.com");

        Assert.assertEquals(1, getDriver().findElements(By.name("q")).size());
    }

    @Test
    public void csTest() {
        getDriver().get("http://agileszkolenia.pl");

        Assert.assertEquals(1, getDriver().findElements(By.id("mce-FNAME")).size());
    }
}
