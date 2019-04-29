package pagetests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.google.GoogleMainPage;
import pageobjects.google.GoogleResultsPage;

public class GoogleTests extends BaseTest {

    @Test
    public void googlePopTest() {
        // arrange
        GoogleMainPage mainPage = new GoogleMainPage(getDriver());
        // act
        GoogleResultsPage resultsPage = mainPage.searchFor("codesprinters");
        // assert
        Assert.assertTrue(resultsPage.hasResults("http://agileszkolenia.pl/"));
    }

    @Test
    public void googleTest() {

        getDriver().navigate().to("http://google.com");
        assert getDriver().findElements(By.name("q")).size() == 1;
    }

    @Test
    public void csTest() {
        getDriver().get("http://agileszkolenia.pl");
        assert getDriver().findElements(By.id("mce-FNAME")).size() == 1;
    }

}
