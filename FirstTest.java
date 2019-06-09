import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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

    @Test
    public void firstTest() {
        waitForElementByXPathAndClick(
                "//*[contains(@text, 'Skip' )]",
                "Cannot find search 'Skip'",
                5
        );

        waitForElementByXPathAndClick(
                "//*[contains(@text, 'Search Wikipedia' )]",
                "Cannot find search  'Search Wikipedia'",
                5
        );

        waitForElementByIdAndSendKeys(
                "org.wikipedia:id/search_src_text",
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementSearchById(
                "org.wikipedia:id/page_list_item_title",
                "Found less than two articles on Java",
                10
        );
        waitForElementByIdAndClick(
                "org.wikipedia:id/search_close_btn",
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                "org.wikipedia:id/page_list_item_title",
                "Found less than two articles on Java",
                10
        );

}

    private WebElement waitForElementPresentById(String id, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXPath(String xpath, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementByIdAndClick(String id, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresentById(id, error_massage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementByIdAndSendKeys(String id, String value, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresentById(id, error_massage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementByXPathAndClick(String xpath, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresentByXPath(xpath, error_massage, timeoutInSeconds);
        element.click();
        return element;

    }

    private boolean waitForElementNotPresent(String id, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    private boolean waitForElementSearchById(String id, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
        List<WebElement> tasksList = driver.findElements(By.id(id));
        int size = tasksList.size();
        if (size>1){
            System.out.println(size);
        }
         if (size<2){
             System.out.println(error_massage);
         }
        return false;
    }
}
