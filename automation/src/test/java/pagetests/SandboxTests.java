package pagetests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageobjects.wordpress.blog.WpLoginPage;

import java.util.UUID;

public class SandboxTests extends BaseTest {

    @Test
    public void canSwitchToFrame() {

        String user = "jan-automatyczny";
        String password = "Cod@Sprint3rs2019";

        WpLoginPage loginPage = new WpLoginPage(getDriver());
        loginPage.login(user, password);

        getDriver().get("http://www.automation.markowicz.pro/wp-admin/post-new.php");
        getDriver().findElement(By.id("content-tmce")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        getDriver().findElement(By.id("tinymce")).sendKeys(UUID.randomUUID().toString());
        getDriver().switchTo().parentFrame();
    }
}
