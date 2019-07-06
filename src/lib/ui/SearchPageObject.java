package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_TITLE_ELEMENT_TPL,
        SEARCH_TITLE_OR_DESCRIPTION_TPL,
        CONCATINATION_TPL,
        SEARCH_RESULT_BY_INDEX_OF_ARTICLE_TPL;


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /* TEMPLATES METHODS */

    private static String getSearchResultByNumberOfArticle(String index)
    {
        return SEARCH_RESULT_BY_INDEX_OF_ARTICLE_TPL.replace("{INDEX}", index);
    }

    private static String getConcatinationTpl(String title_or_description)
    {
        return CONCATINATION_TPL.replace("{TITLE_OR_DESCRIPTION}", title_or_description);
    }

    private static String getTitleOrDescription(String title_or_description)
    {
        return SEARCH_TITLE_OR_DESCRIPTION_TPL.replace("{TITLE_OR_DESCRIPTION}", title_or_description);
    }

    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    private static String getTitleXpathByName(String name_of_article_title)
    {
        return SEARCH_TITLE_ELEMENT_TPL.replace("{TITLE_NAME}", name_of_article_title);
    }


    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search input element", 10);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
//        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + substring, 15);
    }

    public void clickByArticleWithSubstringById(String substring)
    {
        String search_result_id = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_id, "Cannot find and click search result with substring" + substring, 15);
    }

    public void clickByArticleWithSubstringByXPath(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 15);
    }

    public int getAmountOfFoundArticles()
    {

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything search by request",
                10
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);

    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT, "We supposed not find any results");
    }

    public void assertElementPresent(String name_of_article_title)
    {
        String name_of_title_xpath = getTitleXpathByName(name_of_article_title);
        this.assertElementPresent(
                name_of_title_xpath,
                "text",
                name_of_article_title,
                "Cannot find title" + name_of_article_title
        );
    }

    public void waitTitle(String title) {
        String article_title = getTitleOrDescription(title);
        this.waitForElementPresent((article_title), "Cannot find article title", 10);
    }
    public void waitDescription(String description){

    String article_description = getTitleOrDescription(description);
        this.waitForElementPresent((article_description), "Cannot find article description", 10);
    }

    public void searchResultsTitleAndDescriptionByIndex(String index, String title, String description)
    {
        String article_index = getSearchResultByNumberOfArticle(index);

        if (Platform.getInstance().isAndroid()){
            String article_title = getConcatinationTpl(title);
            this.waitForElementPresent((article_index + article_title),"Cannot find article title" + title, 10);
        }

        this.waitForElementPresent((article_index),"Cannot find article title" + title, 10);
        if (Platform.getInstance().isAndroid()) {
            String article_description = getConcatinationTpl(description);
            this.waitForElementPresent((article_index + article_description), "Cannot find article description" + description, 10);
        }
        this.waitForElementPresent((article_index), "Cannot find article description" + description, 10);
    }

    public void assertRemainingArticle(String description)
    {
        String article_description = getTitleOrDescription(description);
        this.waitForElementPresent(article_description, "Cannot find remaining article " + description, 15);
    }





}
