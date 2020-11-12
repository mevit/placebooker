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

public class SearchPlaceTest {
    private WebDriver webDriver;
    private PlacebookerSite website;
    private WebDriverWait webDriverWait;

    private static final String LOGIN_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login";
    private static final String BOOKINGS_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/booking";
    private static final String SEARCHPLACE_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/search";
    private static final String CHROMEDRIVER_PATH = "D:\\Programs\\chromedriver\\chromedriver.exe";
    private static final String BOOKINGS_TITLE_SELECTOR = "[class=\"sc-jONnTn jIvnOH\"]";
    private static final String SELECT_ROOM_BUTTON_SELECTOR = "[data-testid=\"select-room-btn\"]";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(LOGIN_PAGE_URL);
        webDriverWait = new WebDriverWait(webDriver, 15);
        website = new PlacebookerSite(webDriver);
        website.loginPage().login();
        webDriver.get(SEARCHPLACE_PAGE_URL);
    }

    @Test
    public void bookWorkplace() {
        website.searchPlacePage().clickDatePicker();
        website.searchPlacePage().selectNextMonth();
        website.searchPlacePage().chooseRandomDate();
        website.searchPlacePage().clickDeliveryPicker();
        website.searchPlacePage().chooseRandomDelivery();
        website.searchPlacePage().clickFilterButton();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECT_ROOM_BUTTON_SELECTOR)));
        website.searchPlacePage().chooseRandomRoom();
        website.searchPlacePage().chooseRandomPlace();
        website.searchPlacePage().clickBookButton();
        webDriverWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.cssSelector(BOOKINGS_TITLE_SELECTOR))));
        Assert.assertEquals(BOOKINGS_PAGE_URL, webDriver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        if(webDriver != null)
            webDriver.quit();
    }
}
