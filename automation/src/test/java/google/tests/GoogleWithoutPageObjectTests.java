package google.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import webpage.testsuite.BaseTest;

public class GoogleWithoutPageObjectTests extends BaseTest {
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
