package poptests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageobjects.WpAdminPage;
import pageobjects.WpLoginPage;

import java.util.UUID;

public class SandboxTests extends BaseTest {

    @Test
    public void canSwitchToFrame() {

        String user = "jan-automatyczny";
        String password = "Cod@Sprint3rs2019";
        String noteTitle = UUID.randomUUID().toString();
        String noteContent = UUID.randomUUID().toString();

        WpLoginPage loginPage = new WpLoginPage(driver.get());
        WpAdminPage adminPage = loginPage.login(user, password);

        driver.get().get("http://www.automation.markowicz.pro/wp-admin/post-new.php");

        driver.get().findElement(By.id("content-tmce")).click();


        //driver.get().switchTo().frame(0);

        WebDriverWait wait = new WebDriverWait(driver.get(), 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        driver.get().findElement(By.id("tinymce")).sendKeys(UUID.randomUUID().toString());
        driver.get().switchTo().parentFrame();
    }
}
