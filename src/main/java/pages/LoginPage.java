package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    private static final String USER_EMAIL = "tctestuser0@gmail.com";
    private static final String USER_PASSWORD = "tctesttest";
    private static final String BROWSE_PLACES_BUTTON_PATH = "[data-testid=\"browse-places-btn\"]";

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @FindBy(css = "[data-testid=\"signin-btn\"]")
    private WebElement signInWithGoogleBtn;

    @FindBy(name = "identifier")
    private WebElement emailField;

    @FindBy(className = "VfPpkd-RLmnJb")
    private WebElement nextLoginInputButton;

    @FindBy(name = "password")
    private WebElement passowordField;

    @FindBy(className = "VfPpkd-RLmnJb")
    private WebElement nextPasswordButton;

    @FindBy(css = "[class=\"sc-kJFuea iwdYMG\"]")
    private WebElement userInfoField;

    @FindBy(css = "[data-testid=\"logout-btn\"]")
    private WebElement logoutButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 15);
        PageFactory.initElements(this.webDriver, this); //for finding elements on page
    }

    public void openLoginForm() {
        signInWithGoogleBtn.click();
    }

    private void inputUserEmail() {
        emailField.sendKeys(USER_EMAIL);
    }

    private void clickNextAfterEmail() { nextLoginInputButton.click(); }

    private void inputUserPassword() {
        passowordField.sendKeys(USER_PASSWORD);
    }

    private void clickNextAfterPassword() {
        nextPasswordButton.click();
    }

    private void clickUserInfoField() { userInfoField.click(); }

    private void clickLogoutButton() { logoutButton.click(); }

    public void logout() {
        clickUserInfoField();
        clickLogoutButton();
    }

    public void login() {
        Set<String> oldWindowHandlers = webDriver.getWindowHandles();           //get id of current window
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        openLoginForm();                                                        //login window is opened
        Set<String> newWindowHandlers = webDriver.getWindowHandles();           //get id's for all windows
        newWindowHandlers.removeAll(oldWindowHandlers);                         //safe id of login window
        webDriver.switchTo().window(newWindowHandlers.iterator().next());       //switch to login window
        inputUserEmail();
        clickNextAfterEmail();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        inputUserPassword();
        clickNextAfterPassword();
        webDriver.switchTo().window(oldWindowHandlers.iterator().next());       //switch to main window
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(BROWSE_PLACES_BUTTON_PATH)));
    }
}
