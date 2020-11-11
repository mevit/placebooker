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

public class MainPageTest {
    private WebDriver webDriver;
    private PlacebookerSite website;
    private WebDriverWait webDriverWait;

    private static final String LOGIN_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login";
    private static final String SEARCHPLACE_PAGE_URL = "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/search";
    private static final String CHROMEDRIVER_PATH = "D:\\Programs\\chromedriver\\chromedriver.exe";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(LOGIN_PAGE_URL);
        webDriverWait = new WebDriverWait(webDriver, 15);
        website = new PlacebookerSite(webDriver);
        website.loginPage().login();
    }

    @Test
    public void checkBrowsePlacesButton() {
        website.mainPage().clickDatePicker();
        website.mainPage().selectNextMonth();
        website.mainPage().chooseRandomDate();
        website.mainPage().clickBrowsePlacesButton();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"date-picker-input\"]")));
        Assert.assertEquals(SEARCHPLACE_PAGE_URL, webDriver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        if(webDriver != null)
            webDriver.quit();
    }
}
