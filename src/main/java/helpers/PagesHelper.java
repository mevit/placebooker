package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PagesHelper {

    public void chooseRandomDate(WebDriver webDriver, String monthLocator, String weekLocator, String dayLocator) {
        ArrayList<WebElement> availableDaysInMonth = new ArrayList<WebElement>();
        List<WebElement> allDaysInMonth = webDriver.findElement(By.cssSelector(monthLocator))
                .findElements(By.cssSelector(weekLocator));
        for (WebElement webElement : allDaysInMonth) {
            availableDaysInMonth.addAll(webElement.findElements(By.cssSelector(dayLocator)));
        }
        availableDaysInMonth.get(new Random().nextInt(availableDaysInMonth.size())).click();
    }
}
