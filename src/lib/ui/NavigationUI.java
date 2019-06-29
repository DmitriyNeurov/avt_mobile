package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LISTS_LINK = "xpath://*[@class = 'android.widget.FrameLayout']//*[@content-desc = 'My lists']",
            EXPLORE = "xpath://*[@class = 'android.widget.FrameLayout'][@content-desc = 'Explore']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My list",
                5
        );

    }

    public void clickExplore() {
        this.waitForElementAndClick(
                EXPLORE,
                "Cannot find navigation button to Explore",
                5
        );

    }


}