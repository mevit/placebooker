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
    WebDriver webDriver;
    PlacebookerSite website;
    WebDriverWait webDriverWait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Programs\\chromedriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/login");
        website = new PlacebookerSite(webDriver);
        webDriverWait = new WebDriverWait(webDriver, 15);
    }

//    @Test
//    public void openAccountsModal() {
//        website.loginPage().openLoginForm();
//    }

    @Test
    public void loginWithGoogleAccount() {
        Set<String> oldWindowHandlers = webDriver.getWindowHandles();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        website.loginPage().openLoginForm();
        Set<String> newWindowHandlers = webDriver.getWindowHandles();
        newWindowHandlers.removeAll(oldWindowHandlers);
        webDriver.switchTo().window(newWindowHandlers.iterator().next());
        website.loginPage().inputUserEmail();
        website.loginPage().clickNextAfterEmail();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        website.loginPage().inputUserPassword();
        website.loginPage().clickNextAfterPassword();
        webDriver.switchTo().window(oldWindowHandlers.iterator().next());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"browse-places-btn\"]")));
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://tc-workplace-booking-fe.s3-website.eu-central-1.amazonaws.com/home");
    }

    @After
    public void tearDown() {
        if(webDriver != null)
            webDriver.quit();
    }
}
