package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchPlacePage {

    private WebDriver webDriver;

    private static final String DATEPICKER_MONTH_CLASS = "[class=\"react-datepicker__month\"]";
    private static final String DATEPICKER_WEEK_CLASS = "[class=\"react-datepicker__week\"]";
    private static final String AVAILABLE_DAYS_SELECTOR = "[aria-disabled=\"false\"]";

    @FindBy(css = "[data-testid=\"date-picker-input\"]")
    private WebElement datePicker;

    @FindBy(css = "[class=\"Select__value-container Select__value-container--is-multi css-1hwfws3\"]")
    private WebElement deliveryPicker;

    @FindBy(css = "[aria-label=\"Next Month\"]")
    private WebElement nextMonthButton;

    @FindBy(css = "[data-testid=\"filter-btn\"]")
    private WebElement filterButton;

    @FindBy(css = "[class=\"sc-jJEJSO laquCT btn\"]")
    private WebElement bookButton;

    public SearchPlacePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void chooseRandomDate() {
        ArrayList<WebElement> availableDaysInMonth = new ArrayList<WebElement>();
        List<WebElement> allDaysInMonth = webDriver.findElement(By.cssSelector(DATEPICKER_MONTH_CLASS)).findElements(By.cssSelector(DATEPICKER_WEEK_CLASS));
        for (WebElement webElement : allDaysInMonth) {
            availableDaysInMonth.addAll(webElement.findElements(By.cssSelector(AVAILABLE_DAYS_SELECTOR)));
        }
        availableDaysInMonth.get(new Random().nextInt(availableDaysInMonth.size())).click();
    }

    public void chooseRandomRoom() {
        List<WebElement> roomsList = webDriver.findElements(By.cssSelector("[data-testid=\"select-room-btn\"]"));
        roomsList.get(new Random().nextInt(roomsList.size())).click();
    }

    public void chooseRandomPlace() {
        List<WebElement> placesList = webDriver.findElements(By.cssSelector("[data-testid=\"place-btn\"]"));
        ArrayList<WebElement> availablePlacesList = new ArrayList<WebElement>();
        for(WebElement webElement : placesList) {
            if (webElement.isEnabled())
                availablePlacesList.add(webElement);
        }
        availablePlacesList.get(new Random().nextInt(availablePlacesList.size())).click();
    }

    public void chooseRandomDelivery() {
        List<WebElement> deliveriesList = webDriver.findElement(By.cssSelector("[class=\"Select__menu-list Select__menu-list--is-multi css-11unzgr\""))
                .findElements(By.xpath(".//*"));
        deliveriesList.get(new Random().nextInt(deliveriesList.size())).click();
    }

    public void clickDatePicker() {
        datePicker.click();
    }

    public void clickDeliveryPicker() { deliveryPicker.click(); }

    public void selectNextMonth() {
        nextMonthButton.click();
    }

    public void clickFilterButton() { filterButton.click(); }

    public void clickBookButton() { bookButton.click(); }
}
