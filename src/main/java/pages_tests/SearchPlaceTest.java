package pages_tests;

import helpers.DriverSetup;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PlacebookerSite;

public class SearchPlaceTest {
    private PlacebookerSite website;

    private static final String LOGIN_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login";
    private static final String BOOKINGS_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/booking";
    private static final String SEARCHPLACE_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/search";
    private static final String BOOKINGS_TITLE_SELECTOR = "[class=\"sc-jONnTn jIvnOH\"]";
    private static final String SELECT_ROOM_BUTTON_SELECTOR = "[data-testid=\"select-room-btn\"]";

    @Before
    public void setUp() {
        DriverSetup.getInstance().getWebDriver().get(LOGIN_PAGE_URL);
        website = new PlacebookerSite(DriverSetup.getInstance().getWebDriver());
        website.loginPage().login();
        DriverSetup.getInstance().getWebDriver().get(SEARCHPLACE_PAGE_URL);
    }

    @Test
    public void bookWorkplace() {
        website.searchPlacePage().clickDatePicker();
        website.searchPlacePage().selectNextMonth();
        website.searchPlacePage().chooseRandomDate();
        website.searchPlacePage().clickDeliveryPicker();
        website.searchPlacePage().chooseRandomDelivery();
        website.searchPlacePage().clickFilterButton();
        DriverSetup.getInstance().getWebDriverWait()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECT_ROOM_BUTTON_SELECTOR)));
        website.searchPlacePage().chooseRandomRoom();
        website.searchPlacePage().chooseRandomPlace();
        website.searchPlacePage().clickBookButton();
        DriverSetup.getInstance().getWebDriverWait()
                .until(ExpectedConditions.visibilityOf(DriverSetup
                        .getInstance()
                        .getWebDriver()
                        .findElement(By.cssSelector(BOOKINGS_TITLE_SELECTOR))));
        Assert.assertEquals(BOOKINGS_PAGE_URL,DriverSetup.getInstance().getWebDriver().getCurrentUrl());
    }

    @AfterClass
    public static void tearDown() {
        if(DriverSetup.getInstance().getWebDriver() != null)
            DriverSetup.getInstance().getWebDriver().quit();
    }
}
