package wordpress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import wordpress.pageobjects.admin.WpAdminPage;
import wordpress.pageobjects.blog.WpHomePage;
import wordpress.pageobjects.blog.WpLoginPage;
import wordpress.pageobjects.blog.WpNotePage;
import pagetests.BaseTest;
import wordpress.domain.WpVisitor;

import java.util.UUID;

public class WordpressTests extends BaseTest {
    @Test
    public void canAddCommentToNote() {
        WpVisitor visitor = new WpVisitor();
        String comment = UUID.randomUUID().toString();

        WpNotePage notePage = openFirstNote();
        WpNotePage notePageWithComment = notePage.addComment(visitor.getName(), visitor.getEmail(), comment);

        Assert.assertTrue(notePageWithComment.hasComment(visitor.getName(), comment));
    }

    @Test
    public void canAddReplyToComment() {
        WpVisitor commentAuthor = new WpVisitor();
        WpVisitor replyAuthor = new WpVisitor();
        String comment = UUID.randomUUID().toString();
        String reply = UUID.randomUUID().toString();

        WpNotePage notePage = openFirstNote();
        WpNotePage notePageWithComment = notePage
                .addComment(commentAuthor.getName(), commentAuthor.getEmail(), comment);

        WpNotePage noteWithReply = notePageWithComment
                .addReplyToComment(comment, replyAuthor.getName(), replyAuthor.getEmail(), reply);

        Assert.assertTrue((noteWithReply.commentReplyExists(comment, reply)));
    }

    @Test
    public void canLoginAndLogout() {
        String user = "jan-automatyczny";
        String password = "Cod@Sprint3rs2019";

        WpLoginPage loginPage = new WpLoginPage(getDriver());
        WpAdminPage adminPage = loginPage.login(user, password);
        Assert.assertTrue(adminPage.isUserLoggedIn());

        WpLoginPage loggedOutPage = adminPage.logout();
        Assert.assertTrue(loggedOutPage.isUserLoggedOut());
    }

    @Test
    public void canPublishNote() {
        String user = "jan-automatyczny";
        String password = "Cod@Sprint3rs2019";
        String noteTitle = UUID.randomUUID().toString();
        String noteContent = UUID.randomUUID().toString();

        WpLoginPage loginPage = new WpLoginPage(getDriver());
        WpAdminPage adminPage = loginPage.login(user, password);
        WpAdminPage newNoteEditor = adminPage.openNewNoteEditor();
        newNoteEditor.createNote(noteTitle, noteContent);
        String noteUrl = newNoteEditor.publishNote();

        WpLoginPage loggedOutPage = adminPage.logout();

        WpNotePage notePage = WpNotePage.open(noteUrl, getDriver());
        Assert.assertTrue(notePage.noteContains(noteTitle, noteContent));
    }

    private WpNotePage openFirstNote() {
        WpHomePage homePage = new WpHomePage(getDriver());
        return homePage.openFirstNote();
    }
}