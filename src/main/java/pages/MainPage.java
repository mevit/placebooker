package pages;

import helpers.PagesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver webDriver;
    private PagesHelper pagesHelper;

    private static final String DATEPICKER_MONTH_CLASS = "[class=\"react-datepicker__month\"]";
    private static final String DATEPICKER_WEEK_CLASS = "[class=\"react-datepicker__week\"]";
    private static final String AVAILABLE_DAYS_SELECTOR = "[aria-disabled=\"false\"]";

    @FindBy(css = "[data-testid=\"browse-places-btn\"]")
    private WebElement browsePlacesButton;

    @FindBy(css = "[data-testid=\"date-picker-input\"]")
    private WebElement datePicker;

    @FindBy(css = "[aria-label=\"Next Month\"]")
    private WebElement nextMonthButton;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        pagesHelper = new PagesHelper();
    }

    public void chooseRandomDate() {
        pagesHelper.chooseRandomDate(webDriver, DATEPICKER_MONTH_CLASS, DATEPICKER_WEEK_CLASS, AVAILABLE_DAYS_SELECTOR);
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
