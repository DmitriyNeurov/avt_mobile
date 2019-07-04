package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "org.wikipedia:id/page_list_item_title",
        FOOTER_ELEMENT = "//*[@text = 'View page in browser']",
        OPTIONS_BUTTON = "//*[@class = 'android.widget.ImageView'][@content-desc = 'More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text = 'Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "//*[@text = 'Got it']",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text = 'OK']",
        CLOSE_ARTICLE_BUTTON = "//*[@class = 'android.widget.ImageButton'][@content-desc = 'Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void waitForNumberOfResultsFound(int number_of_results)
    {
        this.waitForElementSearchResultsArticleByTitle(By.id(TITLE), number_of_results,"Found less than "+number_of_results+" articles", 10);
    }


    public void waitForTitleElementToAppear()
    {
        this.waitForElementPresent(By.id(TITLE), "Cannot search article title", 10);
    }


    public void waitForTitleElementToDisappear()
    {
        this.waitForElementNotPresent(By.id(TITLE), "Search article title is still present", 10);
    }

    public void swipeToFooter()
    {
        this.swipeUpFindToElement(By.xpath(FOOTER_ELEMENT),"Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementByClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name to articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press 'Ok' button",
                5
        );
    }

    public void addArticleToExistMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find to list" + name_of_folder,
                15
        );

    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot closed article",
                5
        );

    }


}
