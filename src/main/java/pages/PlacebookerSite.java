package pages;

import org.openqa.selenium.WebDriver;

public class PlacebookerSite {

    WebDriver webDriver;

    public PlacebookerSite(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public MainPage mainPage() { return new MainPage(webDriver); }

    public SearchPlacePage searchPlacePage() { return new SearchPlacePage(webDriver); }
}
