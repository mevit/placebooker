package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPage {

    private WebDriver webDriver;

    private static final String DATEPICKER_MONTH_CLASS = "[class=\"react-datepicker__month\"]";
    private static final String DATEPICKER_WEEK_CLASS = "[class=\"react-datepicker__week\"]";
    private static final String AVAILABLE_DAYS_SELECTOR = "[aria-disabled=\"false\"]";

    @FindBy(css = "[data-testid=\"browse-places-btn\"]")
    WebElement browsePlacesButton;

    @FindBy(css = "[data-testid=\"date-picker-input\"]")
    WebElement datePicker;

    @FindBy(css = "[aria-label=\"Next Month\"]")
    WebElement nextMonthButton;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void chooseRandomDate() {
        ArrayList<WebElement> availableDaysInMonth = new ArrayList<WebElement>();                                       //list for available dates
        List<WebElement> allDaysInMonth = webDriver.findElement(By.cssSelector(DATEPICKER_MONTH_CLASS))
                .findElements(By.cssSelector(DATEPICKER_WEEK_CLASS));                                                   //get all weeks
        for (WebElement webElement : allDaysInMonth) {
            availableDaysInMonth.addAll(webElement.findElements(By.cssSelector(AVAILABLE_DAYS_SELECTOR)));
        }
        availableDaysInMonth.get(new Random().nextInt(availableDaysInMonth.size())).click();                            //click on random date in datepicker
    }

    public void clickBrowsePlacesButton() {
        browsePlacesButton.click();
    }

    public void clickDatePicker() {
        datePicker.click();
    }

    public void selectNextMonth() {
        nextMonthButton.click();
    }


}
