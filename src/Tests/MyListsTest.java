package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByXPath("Object-oriented programming language");

//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][@text = 'Object-oriented programming language']"),
//                "Cannot find search 'Object-oriented programming language'",
//                5
//        );
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyLists();
        MyListPageObject.openFolder_By_Name(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveAndDeleteArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByXPath("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyLists();
        MyListPageObject.openFolder_By_Name(name_of_folder);
        String nameOfArticleJava = "Java (programming language)";
        MyListPageObject.waitForArticleToAppearByTitle(nameOfArticleJava);
        ArticlePageObject.closeArticle();
        NavigationUI.clickExplore();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstringByXPath("Appium");
        String nameOfArticleAppium = "Appium";
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExistMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyLists();
        MyListPageObject.openFolder_By_Name(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(nameOfArticleJava);
        MyListPageObject.waitForArticleToAppearByTitle(nameOfArticleAppium);
        SearchPageObject.clickByArticleWithSubstringByXPath("Appium");
        ArticlePageObject.swipeToFooter();
    }

}
