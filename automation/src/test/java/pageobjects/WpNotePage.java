package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WpNotePage extends GenericPage {

    private static final By LOC_SINGLE_POST_BODY = By.cssSelector("body.single-post");
    private static final By LOC_COMMENT_BOX = By.id("comment");
    private static final By LOC_AUTHOR_BOX = By.id("author");
    private static final By LOC_EMAIL_BOX = By.id("email");
    private static final By LOC_SUBMIT_COMMENT_BUTTON = By.id("submit");
    private static final By LOC_TOP_LEVEL_COMMENT_BLOCK = By.cssSelector("li.comment.depth-1");
    private static final By LOC_COMMENT_AUTHOR = By.cssSelector(".comment-meta .fn");
    private static final By LOC_COMMENT_TEXT = By.cssSelector(".comment-content p");
    private static final By LOC_COMMENT_REPLY_LINK = By.cssSelector(".comment-reply-link");
    private static final By LOC_CANCEL_REPLY_LINK = By.id("cancel-comment-reply-link");
    private static final By LOC_REPLY_BLOCK = By.cssSelector("li.comment.depth-2");

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

        waitForElementNotPresent(submitCommentButton);
    }

    public boolean hasComment(String author, String comment) {
        return driver.findElements(LOC_TOP_LEVEL_COMMENT_BLOCK).stream()
        .anyMatch(el -> el.findElement(LOC_COMMENT_AUTHOR).getText().equals(author) &&
                el.findElement(LOC_COMMENT_TEXT).getText().equals(comment));

    }

    public WpNotePage addReplyToComment(String comment, String name, String email, String reply) {

        WebElement commentBlock = firstTopLevelCommentByContent(comment);
        commentBlock.findElement(LOC_COMMENT_REPLY_LINK).click();

        waitForElementPresence(LOC_CANCEL_REPLY_LINK);
        createCommentOrReply(name, email, reply);

        return new WpNotePage(driver);
    }

    private WebElement firstTopLevelCommentByContent(String comment) {
        return driver.findElements(LOC_TOP_LEVEL_COMMENT_BLOCK).stream()
                .filter(el -> el.findElement(LOC_COMMENT_TEXT).getText().equals((comment)))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unable to locate element: "
                        + LOC_COMMENT_TEXT.toString()));
    }

    public boolean commentReplyExists(String comment, String reply) {
        WebElement commentAndReplyBlock = firstTopLevelCommentByContent(comment);

        return commentAndReplyBlock.findElements(LOC_REPLY_BLOCK).stream()
                .anyMatch(rpl -> rpl.findElement(LOC_COMMENT_TEXT).getText().equals(reply));

    }
}
