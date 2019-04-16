package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Collector;

public class WpNotePage extends GenericPage {

    private static final By LOC_SINGLE_POST_BODY = By.cssSelector("body.single-post");
    private static final By LOC_COMMENT_BOX = By.id("comment");
    private static final By LOC_AUTHOR_BOX = By.id("author");
    private static final By LOC_EMAIL_BOX = By.id("email");
    private static final By LOC_SUBMIT_COMMENT_BUTTON = By.id("submit");
    private static final By LOC_TOP_LEVEL_COMMENT_BLOCK = By.cssSelector("li.comment.depth-1");
    private static final By LOC_COMMENT_AUTHOR = By.cssSelector(".comment-meta .fn");
    private static final By LOC_COMMENT_TEXT = By.cssSelector(".comment-content p");
    public static final By LOC_COMMENT_REPLY_LINK = By.cssSelector(".comment-reply-link");

    WpNotePage(WebDriver webDriver) {
        super(webDriver);
        waitForElementPresence(LOC_SINGLE_POST_BODY);
    }

    public WpNotePage addComment(String author, String email, String comment) {
        createCommentOrReply(author, email, comment);
        return new WpNotePage(driver);
    }

    private void createCommentOrReply(String author, String email, String commentText) {
        WebElement commentBox = driver.findElement(LOC_COMMENT_BOX);
        commentBox.click();
        commentBox.sendKeys(commentText);

        WebElement authorBox = driver.findElement(LOC_AUTHOR_BOX);
        authorBox.click();
        authorBox.sendKeys(author);

        WebElement emailBox = driver.findElement(LOC_EMAIL_BOX);
        emailBox.click();
        emailBox.sendKeys(email);

        WebElement submitCommentButton = driver.findElement(LOC_SUBMIT_COMMENT_BUTTON);
        submitCommentButton.click();
    }

    public boolean hasComment(String author, String comment) {
        return driver.findElements(LOC_TOP_LEVEL_COMMENT_BLOCK).stream()
        .anyMatch(el -> el.findElement(LOC_COMMENT_AUTHOR).getText().equals(author) &&
                el.findElement(LOC_COMMENT_TEXT).getText().equals(comment));

    }

    public WpNotePage addReplyToComment(String comment, String name, String email, String reply) {

        WebElement commentBlock = driver.findElements(LOC_TOP_LEVEL_COMMENT_BLOCK).stream()
                .filter(el -> el.findElement(LOC_COMMENT_TEXT).getText().equals((comment)))
                .findFirst()
                .get();

        waitForElementPresence(LOC_COMMENT_REPLY_LINK);

        commentBlock.findElement(LOC_COMMENT_REPLY_LINK).click();
        createCommentOrReply(name, email, reply);

        //waitForElementNotPresent(driver.findElement(By.id("cancel-comment-reply-link")));

        return new WpNotePage(driver);
    }

    public boolean commentReplyExists(String comment, String reply) {
        WebElement replyBlock = driver.findElements(LOC_TOP_LEVEL_COMMENT_BLOCK).stream()
                .filter(cmt -> cmt.findElement(LOC_COMMENT_TEXT).getText().equals(comment))
                .findFirst().get();

        return replyBlock.findElements(By.cssSelector("li.comment.depth-2")).stream()
                .anyMatch(rpl -> rpl.findElement(LOC_COMMENT_TEXT).getText().equals(reply));

    }
}
