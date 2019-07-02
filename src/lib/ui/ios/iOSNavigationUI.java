package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {

        MY_LISTS_LINK = "id:Saved";
/*
        NEW_SEARCH = "id:Search Wikipedia";
        EXPLORE = "id:Explore";
*/

    }
    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
