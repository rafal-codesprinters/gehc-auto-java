package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WpNotePage extends GenericPage {

    public static final By LOC_SINGLE_POST_BODY = By.cssSelector("body.single-post");
    public static final By LOC_COMMENT_BOX = By.id("comment");
    public static final By LOC_AUTHOR_BOX = By.id("author");
    public static final By LOC_EMAIL_BOX = By.id("email");
    public static final By LOC_SUBMIT_COMMENT_BUTTON = By.id("submit");
    public static final By LOC_TOP_LEVEL_COMMENT_BLOCK = By.cssSelector("li.comment.depth-1");
    public static final By LOC_COMMENT_AUTHOR = By.cssSelector(".comment-meta .fn");

    public WpNotePage(WebDriver webDriver) {
        super(webDriver);
        waitForElementPresence(LOC_SINGLE_POST_BODY);
    }

    public WpNotePage addComment(String author, String email, String comment) {
        WebElement commentBox = driver.findElement(LOC_COMMENT_BOX);
        commentBox.click();
        commentBox.sendKeys(comment);

        WebElement authorBox = driver.findElement(LOC_AUTHOR_BOX);
        authorBox.click();
        authorBox.sendKeys(author);

        WebElement emailBox = driver.findElement(LOC_EMAIL_BOX);
        emailBox.click();
        emailBox.sendKeys(email);

        WebElement submitCommentButton = driver.findElement(LOC_SUBMIT_COMMENT_BUTTON);
        submitCommentButton.click();

        return new WpNotePage(driver);
    }

    public boolean hasComment(String author, String comment) {
        return driver.findElements(LOC_TOP_LEVEL_COMMENT_BLOCK).stream()
        .anyMatch(el -> el.findElement(LOC_COMMENT_AUTHOR).getText().equals(author) &&
                el.findElement(By.cssSelector(".comment-content p")).getText().equals(comment));

    }
}
