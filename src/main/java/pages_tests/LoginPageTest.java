package pages_tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PlacebookerSite;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private WebDriver webDriver;
    private PlacebookerSite website;
    private WebDriverWait webDriverWait;

    private static final String HOME_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/home";
    private static final String LOGIN_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login";
    private static final String CHROMEDRIVER_PATH = "D:\\Programs\\chromedriver\\chromedriver.exe";
    private static final String BROWSE_PLACES_BUTTON_PATH = "[data-testid=\"browse-places-btn\"]";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(LOGIN_PAGE_URL);
        website = new PlacebookerSite(webDriver);
        webDriverWait = new WebDriverWait(webDriver, 15);
    }

    @Test
    public void openAccountsModal() {
        website.loginPage().openLoginForm();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertEquals(2, webDriver.getWindowHandles().size()); //should be 2 windows - main and login
    }

    @Test
    public void loginWithGoogleAccount() {
        Set<String> oldWindowHandlers = webDriver.getWindowHandles();           //get id of current window
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        website.loginPage().openLoginForm();                                    //login window is opened
        Set<String> newWindowHandlers = webDriver.getWindowHandles();           //get id's for all windows
        newWindowHandlers.removeAll(oldWindowHandlers);                         //safe id of login window
        webDriver.switchTo().window(newWindowHandlers.iterator().next());       //switch to login window
        website.loginPage().inputUserEmail();
        website.loginPage().clickNextAfterEmail();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        website.loginPage().inputUserPassword();
        website.loginPage().clickNextAfterPassword();
        webDriver.switchTo().window(oldWindowHandlers.iterator().next());       //switch to main window
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(BROWSE_PLACES_BUTTON_PATH)));
        Assert.assertEquals(HOME_PAGE_URL, webDriver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        if(webDriver != null)
            webDriver.quit();
    }
}
