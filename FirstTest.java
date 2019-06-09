import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/User/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

//    @Test
//    public void firstTest() {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Skip' )]"),
//                "Cannot find search 'Skip'",
//                5
//        );
//
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia' )]"),
//                "Cannot find search  'Search Wikipedia'",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//
//        waitForElementSearch(
//                By.id("org.wikipedia:id/page_list_item_title"),
//                "Found less than two articles on Java",
//                10
//        );
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "Cannot find X to cancel search",
//                5
//        );
//        waitForElementNotPresent(
//                By.id("org.wikipedia:id/page_list_item_title"),
//                "Found less than two articles on Java",
//                10
//        );
//    }
    @Test
    public void swipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip' )]"),
                "Cannot find search 'Skip'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia' )]"),
                "Cannot find search  'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find search input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.TextView'][@text = 'Appium']"),
                "Cannot find search 'Appium' input",
                15
        );
 /*       waitForElementPresent(
                By.xpath("(android.view.View[@content-desc=\"Appium\"])[1]"),
                "Can not find article title",
                15
        );

  */
        swipeUpFindToElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Cannot find the end of the article",
                24

        );

    }


    private WebElement waitForElementPresent(By by, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_massage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_massage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    private void waitForElementSearch(By by, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> tasksList = driver.findElements(by);
        int size = tasksList.size();
        Assert.assertFalse(error_massage, size<2);
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();


    }
    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpFindToElement(By by, String error_massage, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size()==0){
            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Can not fint element by swipe up.\n" + error_massage, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }




}
