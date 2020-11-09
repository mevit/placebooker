package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static final String USER_EMAIL = "tctestuser0@gmail.com";
    private static final String USER_PASSWORD = "tctesttest";

    private WebDriver webDriver;

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

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this); //for finding elements on page
    }

    public void openLoginForm() {
        signInWithGoogleBtn.click();
    }

    public void inputUserEmail() {
        emailField.sendKeys(USER_EMAIL);
    }

    public void clickNextAfterEmail() {
        nextLoginInputButton.click();
    }

    public void inputUserPassword() {
        passowordField.sendKeys(USER_PASSWORD);
    }

    public void clickNextAfterPassword() {
        nextPasswordButton.click();
    }
}
