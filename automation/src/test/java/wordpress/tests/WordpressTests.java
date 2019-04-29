package wordpress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import wordpress.pageobjects.admin.WordPressAdminPage;
import wordpress.pageobjects.blog.WordPressHomePage;
import wordpress.pageobjects.blog.WordPressLoginPage;
import wordpress.pageobjects.blog.WordPressNotePage;
import webpage.testsuite.BaseTest;
import wordpress.domain.WordPressVisitor;

import java.util.UUID;

public class WordpressTests extends BaseTest {
    @Test
    public void canAddCommentToNote() {
        WordPressVisitor visitor = new WordPressVisitor();
        String comment = UUID.randomUUID().toString();

        WordPressNotePage notePage = openFirstNote();
        WordPressNotePage notePageWithComment = notePage.addComment(visitor.getName(), visitor.getEmail(), comment);

        Assert.assertTrue(notePageWithComment.hasComment(visitor.getName(), comment));
    }

    @Test
    public void canAddReplyToComment() {
        WordPressVisitor commentAuthor = new WordPressVisitor();
        WordPressVisitor replyAuthor = new WordPressVisitor();
        String comment = UUID.randomUUID().toString();
        String reply = UUID.randomUUID().toString();

        WordPressNotePage notePage = openFirstNote();
        WordPressNotePage notePageWithComment = notePage
                .addComment(commentAuthor.getName(), commentAuthor.getEmail(), comment);

        WordPressNotePage noteWithReply = notePageWithComment
                .addReplyToComment(comment, replyAuthor.getName(), replyAuthor.getEmail(), reply);

        Assert.assertTrue((noteWithReply.commentReplyExists(comment, reply)));
    }

    @Test
    public void canLoginAndLogout() {
        String user = "jan-automatyczny";
        String password = "Cod@Sprint3rs2019";

        WordPressLoginPage loginPage = new WordPressLoginPage(getDriver());
        WordPressAdminPage adminPage = loginPage.login(user, password);
        Assert.assertTrue(adminPage.isUserLoggedIn());

        WordPressLoginPage loggedOutPage = adminPage.logout();
        Assert.assertTrue(loggedOutPage.isUserLoggedOut());
    }

    @Test
    public void canPublishNote() {
        String user = "jan-automatyczny";
        String password = "Cod@Sprint3rs2019";
        String noteTitle = UUID.randomUUID().toString();
        String noteContent = UUID.randomUUID().toString();

        WordPressLoginPage loginPage = new WordPressLoginPage(getDriver());
        WordPressAdminPage adminPage = loginPage.login(user, password);
        WordPressAdminPage newNoteEditor = adminPage.openNewNoteEditor();
        newNoteEditor.createNote(noteTitle, noteContent);
        String noteUrl = newNoteEditor.publishNote();

        adminPage.logout();

        WordPressNotePage notePage = WordPressNotePage.open(noteUrl, getDriver());
        Assert.assertTrue(notePage.noteContains(noteTitle, noteContent));
    }

    private WordPressNotePage openFirstNote() {
        WordPressHomePage homePage = new WordPressHomePage(getDriver());
        return homePage.openFirstNote();
    }
}
