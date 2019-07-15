package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        CLOSE_WINDOW_SYNC = "id:places auth close";
        CLEAR_SEARCH = "id:clear mini";
        EXPLORE = "css:#searchIcon";


/*
        NEW_SEARCH = "id:Search Wikipedia";
*/

    }
    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}
