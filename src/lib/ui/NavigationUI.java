package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            EXPLORE,
            CLOSE_WINDOW_SYNC,
            CLEAR_SEARCH,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5 );
        }else {
            System.out.println("Method openNavigation() do nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }

    }

    public void clickExplore() {
        this.waitForElementAndClick(
                EXPLORE,
                "Cannot find navigation button to Explore",
                5
        );

    }

    public void clickCloseWindowSync() {
        this.waitForElementAndClick(
                CLOSE_WINDOW_SYNC,
                "Cannot find navigation button to CloseWindowSync",
                5
        );

    }

    public void ClearSearchLine() {
        this.waitForElementAndClick(
                CLEAR_SEARCH,
                "Cannot find navigation button to SearchLine",
                5
        );

    }




}