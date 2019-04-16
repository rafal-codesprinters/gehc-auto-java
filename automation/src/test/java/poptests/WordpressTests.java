package poptests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.WpHomePage;
import pageobjects.WpNotePage;

import java.util.UUID;

public class WordpressTests extends BaseTest {
    @Test
    public void canAddCommentToNote() {
        // arrange
        String author = UUID.randomUUID().toString();
        String email = author + "@test.domain.com";
        String comment = UUID.randomUUID().toString();

        // act
        WpHomePage homePage = new WpHomePage(driver.get());
        WpNotePage notePage = homePage.openFirstNote();
        WpNotePage notePageWithComment = notePage.addComment(author, email, comment);

        // assert
        Assert.assertTrue(notePageWithComment.hasComment(author, comment));
    }
}
