package wordpress.pageobjects.blog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webpage.pageobjects.GenericPage;
import wordpress.pageobjects.admin.WordPressAdminPage;

public class WordPressLoginPage extends GenericPage {

    private static final String WP_ADMIN_URL = "http://automation.markowicz.pro/wp-admin/";
    private static final By LOC_LOGIN_PAGE_BODY = By.cssSelector("body.login");
    public static final By LOC_ADMIN_FORM = By.id("loginform");

    @FindBy(id = "user_login") private WebElement userBox;
    @FindBy (id = "user_pass") private WebElement passwordBox;
    @FindBy (id = "wp-submit") private WebElement submitButton;

    public WordPressLoginPage(WebDriver driver) {
        super(driver);
        driver.get(WP_ADMIN_URL);
        PageFactory.initElements(driver, this);
        waitForElementPresence(LOC_ADMIN_FORM);
    }

    public WordPressAdminPage login(String user, String password) {
        userBox.click();
        userBox.sendKeys(user);

        passwordBox.click();
        passwordBox.sendKeys(password);

        submitButton.click();

        return new WordPressAdminPage(driver);
    }

    public boolean isUserLoggedOut() {
        driver.get(WP_ADMIN_URL);
        waitForElementPresence(LOC_LOGIN_PAGE_BODY);
        return driver.findElements(LOC_ADMIN_FORM).size() > 0;
    }
}
