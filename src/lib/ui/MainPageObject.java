package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }


    public WebElement waitForElementPresent(String locator, String error_massage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_massage)
    {
        return waitForElementPresent(locator, error_massage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_massage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_massage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public void assertElementNotPresent(String locator, String error_massage) {
        By by = this.getLocatorByString(locator);
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_massage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_massage + " " + error_massage);
        }

    }

    public void waitForElementSearchArticleTitleMoreTwo(String locator, String error_massage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> tasksList = driver.findElements(by);
        int size = tasksList.size();
        Assert.assertFalse(error_massage, size < 2);
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y).release()
                .perform();

    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpFindToElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Can not find element by swipe up.\n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_massage, int max_swipes)
    {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_massage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_masage)
    {
        WebElement element = this.waitForElementPresent(locator + "/..", error_masage, 10);
        int right_x = element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action =new TouchAction(driver);
        action.tap(point_to_click_x, point_to_click_y).perform();
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 5).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public WebElement waitForElementByClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y);
        action.waitAction(300);

        if (Platform.getInstance().isAndroid()){
            action.moveTo(left_x, middle_y);
        }else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(offset_x, 0);
        }
        action.release();
        action.perform();
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean waitForElementNotPresent(String locator, String error_massage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    public String waitForElementGetAttribute(String locator, String attribute, String error_massage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_massage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(String locator, String attribute, String title, String error_massage)
    {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        String article = element.getAttribute(attribute);
        Assert.assertEquals(error_massage, article, title);

    }

    private  By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return  By.xpath(locator);
        }else if (by_type.equals("id")){
            return By.id(locator);
        }else {
            throw  new IllegalArgumentException("Cannot get typ of locator. Locator: " + locator_with_type);
        }
    }

    public void waitForElementSearchResultsArticleByTitle(String locator, int number_of_results, String error_massage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> tasksList = driver.findElements(by);
        int size = tasksList.size();
        Assert.assertFalse(error_massage, size < number_of_results);
    }


}
