package pages_tests;

import helpers.DriverSetup;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PlacebookerSite;

public class MainPageTest {
    private PlacebookerSite website;
    private boolean isUserLoggedIn = false;

    private static final String LOGIN_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login";
    private static final String SEARCHPLACE_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/search";

    @Before
    public void setUp() {
        DriverSetup.getInstance().getWebDriver().get(LOGIN_PAGE_URL);
        website = new PlacebookerSite(DriverSetup.getInstance().getWebDriver());
        if(isUserLoggedIn) {
          website.loginPage().openLoginForm();
          DriverSetup.getInstance().getWebDriverWait()
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"date-picker-input\"]")));
        }else {
            website.loginPage().login();
        }
    }

    @Test
    public void checkBrowsePlacesButton() {
        website.mainPage().clickDatePicker();
        website.mainPage().selectNextMonth();
        website.mainPage().chooseRandomDate();
        website.mainPage().clickBrowsePlacesButton();
        DriverSetup.getInstance().getWebDriverWait()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"date-picker-input\"]")));
        Assert.assertEquals(SEARCHPLACE_PAGE_URL, DriverSetup.getInstance().getWebDriver().getCurrentUrl());
    }

    @Test
    public void checkDeliveryWidget() {
        website.mainPage().clickUserDeliveryButton();
    }

    @Test
    public void checkRoomWidget() {
        website.mainPage().clickUserRoomButton();
    }

    @After
    public void endUp() {
        website.loginPage().logout();
    }

    @AfterClass
    public static void tearDown() {
        if(DriverSetup.getInstance().getWebDriver() != null)
            DriverSetup.getInstance().getWebDriver().quit();
    }
}
