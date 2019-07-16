package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearhPageObject extends SearchPageObject {

    static{
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_TITLE_OR_DESCRIPTION_TPL = "id:'{TITLE_OR_DESCRIPTION}'";
        CONCATINATION_SEARCH_TITLE_MW_TPL = "/a[@data-title='{TITLE}']";
        CONCATINATION_SEARCH_DESCRIPTION_MW_TPL = "//div[contains(@class,'wikidata-description')][contains(text(),'{DESCRIPTION}')]";
        SEARCH_RESULT_BY_INDEX_OF_ARTICLE_TPL = "xpath://li[@class='page-summary']['{INDEX}']";

    }

    public MWSearhPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}


