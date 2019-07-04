package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LISTS_LINK = "//*[@class = 'android.widget.FrameLayout']//*[@content-desc = 'My lists']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot find navigation button to My list",
                5
        );

    }

    public void clickExplore() {
        this.waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.FrameLayout'][@content-desc = 'Explore']"),
                "Cannot find navigation button to Explore",
                5
        );

    }


}