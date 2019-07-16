package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static
    {
        TITLE = "css:#content h1";
        ITEM_TITLE = "xpath://div[@class='results']//li[@title]";
        FOOTER_ELEMENT = "css:footer";

/*
        //div[@class='results']//li[contains(@title,'Java')]
*/
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#ca-watch.mw-ui-icon-mf-watch";
        OPTIONS_REMOVE_FROME_MY_LIST_BUTTON = "css:#ca-watch.mw-ui-icon-mf-watched";

    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}