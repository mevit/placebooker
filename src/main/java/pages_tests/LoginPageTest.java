package pages_tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PlacebookerSite;

public class LoginPageTest {
    private WebDriver webDriver;
    private PlacebookerSite website;

    private static final String HOME_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/home";
    private static final String LOGIN_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login";
    private static final String CHROMEDRIVER_PATH = "D:\\Programs\\chromedriver\\chromedriver.exe";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(LOGIN_PAGE_URL);
        website = new PlacebookerSite(webDriver);
    }

    @Test
    public void openAccountsModal() {
        website.loginPage().openLoginForm();
        Assert.assertEquals(2, webDriver.getWindowHandles().size()); //should be 2 windows - main and login
    }

    @Test
    public void loginWithGoogleAccount() {
        website.loginPage().login();
        Assert.assertEquals(HOME_PAGE_URL, webDriver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        if(webDriver != null)
            webDriver.quit();
    }
}
