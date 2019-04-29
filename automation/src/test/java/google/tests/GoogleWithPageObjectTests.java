package google.tests;

import google.pageobjects.GoogleMainPage;
import google.pageobjects.GoogleResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagetests.BaseTest;

public class GoogleWithPageObjectTests extends BaseTest {
    @Test
    public void googlePopTest() {
        GoogleMainPage mainPage = new GoogleMainPage(getDriver());

        GoogleResultsPage resultsPage = mainPage.searchFor("codesprinters");

        Assert.assertTrue(resultsPage.hasResults("http://agileszkolenia.pl/"));
    }
}
