package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia' )]",
        SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@class = 'android.widget.LinearLayout']//*[@resource-id = 'org.wikipedia:id/page_list_item_title']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text = 'No results found']",
        SEARCH_TITLE_ELEMENT_TPL = "//*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text = '{TITLE_NAME}']",
        SEARCH_TITLE_OR_DESCRIPTION_TPL = "//*[@class = 'android.widget.LinearLayout']//*[@text = '{TITLE_OR_DESCRIPTION}']",
        CONCATINATION_TPL = "//*[@text = '{TITLE_OR_DESCRIPTION}']",
        SEARCH_RESULT_BY_INDEX_OF_ARTICLE_TPL = "//*[@class = 'android.widget.LinearLayout'][@index = '{INDEX}']";



    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
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


    private static String getTitleXpathByName(String name_of_article_title)
    {
        return SEARCH_TITLE_ELEMENT_TPL.replace("{TITLE_NAME}", name_of_article_title);
    }

    private static String getTitleAndDescription(String title_or_description)
    {
        return SEARCH_TITLE_OR_DESCRIPTION_TPL.replace("{TITLE_OR_DESCRIPTION}", title_or_description);
    }



    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String article_title = getTitleAndDescription(title);
        this.waitForElementPresent(By.xpath(article_title), "Cannot find article title", 10);
        String article_description = getTitleAndDescription(description);
        this.waitForElementPresent(By.xpath(article_description), "Cannot find article description", 10);
    }

    public void searchResultsTitleAndDescriptionByIndex(String index, String title, String description)
    {
        String article_index = getSearchResultByNumberOfArticle(index);
        String article_title = getConcatinationTpl(title);
        this.waitForElementPresent(By.xpath(article_index + article_title),"Cannot find article title" + title, 10);
        String article_description = getConcatinationTpl(description);
        this.waitForElementPresent(By.xpath(article_index + article_description),"Cannot find article description" + description, 10);

    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search input element", 10);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
//        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring" + substring, 15);
    }

    public void clickByArticleWithSubstringById(String substring)
    {
        String search_result_id = getResultSearchElement(substring);
        this.waitForElementAndClick(By.id(search_result_id), "Cannot find and click search result with substring" + substring, 15);
    }

    public void clickByArticleWithSubstringByXPath(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring" + substring, 15);
    }

    public int getAmountOfFoundArticles()
    {

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything search by request",
                10
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));

    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);

    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "We supposed not find any results");
    }

    public void assertElementPresent(String name_of_article_title)
    {
        String name_of_title_xpath = getTitleXpathByName(name_of_article_title);
        this.assertElementPresent(
                By.xpath(name_of_title_xpath),
                "text",
                name_of_article_title,
                "Cannot find title" + name_of_article_title
        );


    }

}
