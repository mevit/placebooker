package pages_tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PlacebookerSite;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver webDriver;
    private PlacebookerSite website;
    private WebDriverWait webDriverWait;

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
    }

    @Test
    public void checkBrowsePlacesButton() {
        website.mainPage().clickDatePicker();
        website.mainPage().selectNextMonth();
        website.mainPage().chooseRandomDate();
        website.mainPage().clickBrowsePlacesButton();
    }



    @After
    public void tearDown() {
        if(webDriver != null)
            webDriver.quit();
    }
}
