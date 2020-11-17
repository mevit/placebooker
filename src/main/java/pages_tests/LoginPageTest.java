package pages_tests;

import helpers.DriverSetup;
import org.junit.*;
import pages.PlacebookerSite;

public class LoginPageTest {
    private PlacebookerSite website;

    private static final String HOME_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/home";
    private static final String LOGIN_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login";

    @Before
    public void setUp() {
        DriverSetup.getInstance().getWebDriver().get(LOGIN_PAGE_URL);
        website = new PlacebookerSite(DriverSetup.getInstance().getWebDriver());
    }

    @Test
    public void openAccountsModal() {
        website.loginPage().openLoginForm();
        Assert.assertEquals(2, DriverSetup.getInstance().getWebDriver().getWindowHandles().size()); //should be 2 windows - main and login
    }

    @Test
    public void loginWithGoogleAccount() {
        website.loginPage().login();
        Assert.assertEquals(HOME_PAGE_URL, DriverSetup.getInstance().getWebDriver().getCurrentUrl());
    }

    @AfterClass
    public static void tearDown() {
        if(DriverSetup.getInstance().getWebDriver() != null)
            DriverSetup.getInstance().getWebDriver().quit();
    }
}
