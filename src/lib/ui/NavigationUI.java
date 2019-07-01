package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            EXPLORE;

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