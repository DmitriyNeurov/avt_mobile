package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static{
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia' )]";
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[contains(text(),'{SUBSTRING}')]";
            SEARCH_RESULT_ELEMENT = "xpath://*[@class = 'android.widget.LinearLayout']//*[@resource-id = 'org.wikipedia:id/page_list_item_title']";
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text = 'No results found']";
            SEARCH_TITLE_ELEMENT_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text = '{TITLE_NAME}']";
            SEARCH_TITLE_OR_DESCRIPTION_TPL = "xpath://*[@class = 'android.widget.LinearLayout']//*[@text = '{TITLE_OR_DESCRIPTION}']";
            CONCATINATION_TPL = "xpath://*[@text = '{TITLE_OR_DESCRIPTION}']";
            SEARCH_RESULT_BY_INDEX_OF_ARTICLE_TPL = "xpath://*[@class = 'android.widget.LinearLayout'][@index = '{INDEX}']";

    }


    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
