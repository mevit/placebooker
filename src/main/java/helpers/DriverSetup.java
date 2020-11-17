package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverSetup {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private static DriverSetup instance = null;

    private static final String CHROMEDRIVER_PATH = "D:\\Programs\\chromedriver\\chromedriver.exe";

    public DriverSetup() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, 15);
    }

    public static DriverSetup getInstance() {
        if (instance == null)
            instance = new DriverSetup();
        return instance;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }
}
