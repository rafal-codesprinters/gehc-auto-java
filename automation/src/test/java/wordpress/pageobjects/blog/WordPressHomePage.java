package wordpress.pageobjects.blog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.page.GenericPage;

public class WordPressHomePage extends GenericPage {

    private static final String WP_HOME_PAGE_URL = "http://www.automation.markowicz.pro/";
    private static final By LOC_ARTICLE_BLOCK = By.cssSelector("article.post");
    private static final By LOC_HOME_PAGE_BODY = By.cssSelector("body.home.blog");
    private static final By LOC_ARTICLE_LINK = By.cssSelector(".entry-title > a");

    public WordPressHomePage(WebDriver webDriver) {
        super(webDriver);

        driver.get(WP_HOME_PAGE_URL);
        waitForElementPresence(LOC_HOME_PAGE_BODY);
    }

    public WordPressNotePage openFirstNote() {
        WebElement articleBlock = driver.findElement(LOC_ARTICLE_BLOCK);
        articleBlock.findElement(LOC_ARTICLE_LINK).click();

        return new WordPressNotePage(driver);
    }

}
