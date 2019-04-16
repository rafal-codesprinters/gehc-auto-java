package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WpHomePage extends GenericPage {

    public static final String WP_HOME_PAGE_URL = "http://www.automation.markowicz.pro/";
    public static final By LOC_ARTICLE_BLOCK = By.cssSelector("article.post");
    public static final By LOC_HOME_PAGE_BODY = By.cssSelector("body.home.blog");
    public static final By LOC_ARTICLE_LINK = By.cssSelector(".entry-title > a");

    public WpHomePage(WebDriver webDriver) {
        super(webDriver);
        driver.get(WP_HOME_PAGE_URL);
        waitForElementPresence(LOC_HOME_PAGE_BODY);
    }

    public WpNotePage openFirstNote() {
        WebElement articleBlock = driver.findElement(LOC_ARTICLE_BLOCK);
        articleBlock.findElement(LOC_ARTICLE_LINK).click();

        return new WpNotePage(driver);
    }

}
