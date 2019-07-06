package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
        CLOSE_WINDOW_SYNC = "id:places auth close";
        CLEAR_SEARCH = "id:clear mini";
        EXPLORE = "id:Explore";


/*
        NEW_SEARCH = "id:Search Wikipedia";
*/

    }
    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
