package poptests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.WpHomePage;
import pageobjects.WpNotePage;
import testdata.WpVisitor;

import java.util.UUID;

public class WordpressTests extends BaseTest {

    @Test
    public void canAddCommentToNote() {
        // arrange
        WpVisitor visitor = new WpVisitor();
        String comment = UUID.randomUUID().toString();

        // act
        WpNotePage notePage = openFirstNote();
        WpNotePage notePageWithComment = notePage.addComment(visitor.getNAME(), visitor.getEMAIL(), comment);

        // assert
        Assert.assertTrue(notePageWithComment.hasComment(visitor.getNAME(), comment));
    }

    @Test
    public void canAddReplyToComment() {

        WpVisitor commentAuthor = new WpVisitor();
        WpVisitor replyAuthor = new WpVisitor();
        String comment = UUID.randomUUID().toString();
        String reply = UUID.randomUUID().toString();

        WpNotePage notePage = openFirstNote();
        WpNotePage notePageWithComment = notePage
                .addComment(commentAuthor.getNAME(), commentAuthor.getEMAIL(), comment);

        WpNotePage noteWithReply = notePageWithComment
                .addReplyToComment(comment, replyAuthor.getNAME(), replyAuthor.getEMAIL(), reply);

        Assert.assertTrue((noteWithReply.commentReplyExists(comment, reply)));
    }

    private WpNotePage openFirstNote() {
        WpHomePage homePage = new WpHomePage(driver.get());
        return homePage.openFirstNote();
    }
}
