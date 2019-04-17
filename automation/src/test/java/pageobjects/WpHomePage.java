package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WpHomePage extends GenericPage {

    private static final String WP_HOME_PAGE_URL = "http://www.automation.markowicz.pro/";
    private static final By LOC_ARTICLE_BLOCK = By.cssSelector("article.post");
    private static final By LOC_HOME_PAGE_BODY = By.cssSelector("body.home.blog");
    private static final By LOC_ARTICLE_LINK = By.cssSelector(".entry-title > a");

    // @FindBy (css = "article.post .entry-title > a")
    private WebElement article;

    public WpHomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);

        driver.get(WP_HOME_PAGE_URL);
        waitForElementPresence(LOC_HOME_PAGE_BODY);
    }

    public WpNotePage openFirstNote() {
        WebElement articleBlock = driver.findElement(LOC_ARTICLE_BLOCK);
        articleBlock.findElement(LOC_ARTICLE_LINK).click();

        // article.click();

        return new WpNotePage(driver);
    }

}
