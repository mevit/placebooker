package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyBookingsPage {

    private WebDriver webDriver;

    private static final String BOOKING_STATUS_LOCATOR = "[class=\"sc-GTWni hbTlgJ\"]";

    public MyBookingsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void editBookingDate() {

    }

    public void deleteBooking() {

    }
}
