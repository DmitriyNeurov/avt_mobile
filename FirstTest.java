import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
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
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test_Save_And_Delete_Article_To_MyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia' )]"),
                "Cannot find search  'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][@text = 'Object-oriented programming language']"),
                "Cannot find search 'Object-oriented programming language'",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageView'][@content-desc = 'More options']"),
                "Cannot find button to open article options",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find button 'Add to reading list'",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Got it']"),
                "Cannot find button 'Got it'",
                10
        );

        weitForElementByClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name to articles folder",
                5
        );
        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press 'Ok' button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton'][@content-desc = 'Navigate up']"),
                "Cannot closed article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.FrameLayout']//*[@content-desc = 'My lists']"),
                "Cannot find navigation button to My list",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find to list" + name_of_folder,
                15
        );

        String nameOfArticleJava = "Java (programming language)";

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = '" + nameOfArticleJava + "']"),
                "Cannot find an article" + nameOfArticleJava,
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Navigate up']"),
                "Cannot find button 'Navigate up'",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.FrameLayout'][@content-desc = 'Explore']"),
                "Cannot find button 'Explorer'",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia' )]"),
                "Cannot find search  'Search Wikipedia'",
                5
        );

        String nameOfArticleAppium = "Appium";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                nameOfArticleAppium,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Appium']"),
                "Cannot find search" + nameOfArticleAppium,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageView'][@content-desc = 'More options']"),
                "Cannot find button 'More options'",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find button 'Add to reading list'",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find to list" + name_of_folder,
                15
        );


//        waitForElementAndClick(
//                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title'][@text = '" + name_of_folder + "']"),
//                "Cannot find button to open article options",
//                10
//        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc = 'Navigate up']"),
                "Cannot find button 'Navigate up'",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.FrameLayout']//*[@content-desc = 'My lists']"),
                "Cannot find navigation button to My list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find to list" + name_of_folder,
                15
        );

//        waitForElementAndClick(
//                By.xpath("//*[@text = '" + name_of_folder + "']"),
//                "Cannot find to list" + name_of_folder,
//                10
//        );

        swipeElementToLeft(
                By.xpath("//*[@text = '" + nameOfArticleJava + "']"),
                "Cannot find saved article "
        );

        waitForElementPresent(
                By.xpath("//*[@text = '" + nameOfArticleAppium + "']"),
                "Cannot delete saved article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = '" + nameOfArticleAppium + "']"),
                "Cannot find article of 'Appium'",
                15
        );

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


    protected void swipeUp(int timeOfSwipe) {
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

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpFindToElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Can not fint element by swipe up.\n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }

    private WebElement weitForElementByClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        int left_x = element.getLocation().getX();
        int rigt_x = element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rigt_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

}