package pageobjects.wordpress.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.wordpress.blog.WpLoginPage;
import pageobjects.page.GenericPage;

public class WpAdminPage extends GenericPage {

    private static final By LOC_ADMIN_PAGE_BODY = By.cssSelector("body.wp-admin");
    private static final By LOC_SUBMENU_ITEM = By.cssSelector("li a");

    // Elements created by Page Factory for new post menu and submenus
    @FindBy (css = ".post-new-php") private WebElement newPostEditor;
    @FindBy (id = "wp-admin-bar-new-content") private WebElement menuNotes;
    @FindBy (css = "wp-admin-bar-new-content-default") private WebElement submenu;

    // Elements created by Page Factory for note editor
    @FindBy (id = "title") private WebElement newNoteTitle;
    @FindBy (id = "content-html") private WebElement htmlEditorTab;
    @FindBy (id = "content") private WebElement newNoteContent;
    @FindBy (css = ".edit-slug") private WebElement permalinkEditButton;
    @FindBy (id = "publish") private WebElement publishButton;
    @FindBy (id = "sample-permalink") private WebElement permalink;

    // Elements created by Page Factory for user admin menu
    @FindBy(id = "wp-admin-bar-my-account") private WebElement userAdminLink;
    @FindBy (id = "wp-admin-bar-user-actions") private WebElement userActionsMenu;
    @FindBy (id = "wp-admin-bar-logout") private WebElement logoutLink;
    @FindBy (id = "wp-admin-bar-new-post") private  WebElement addNewPostLink;

    public WpAdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElementPresence(LOC_ADMIN_PAGE_BODY);
    }

    public WpAdminPage openNewNoteEditor() {
        hoverOverElement(menuNotes);
        waitForElementVisible(addNewPostLink);

        addNewPostLink.click();

        waitForElementVisible(newPostEditor);
        return new WpAdminPage(driver);
    }

    public WpAdminPage createNote(String title, String content) {
        newNoteTitle.click();
        newNoteTitle.sendKeys(title);

        htmlEditorTab.click();

        newNoteContent.click();
        newNoteContent.sendKeys(content);

        waitForElementClickable(permalinkEditButton);
        return new WpAdminPage(driver);
    }

    public String publishNote() {
        publishButton.click();
        waitForElementClickable(publishButton);

        return permalink.findElement(By.tagName("a")).getAttribute("href");
    }

    public WpLoginPage logout() {
        hoverOverElement(userAdminLink);
        waitForElementVisible(userActionsMenu);
        logoutLink.click();
        return new WpLoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        return driver.findElements(LOC_ADMIN_PAGE_BODY).size() == 1;
    }
}
