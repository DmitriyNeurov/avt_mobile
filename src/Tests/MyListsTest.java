package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

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
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        NavigationUI.clickMyLists();
        MyListPageObject.openFolder_By_Name(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveAndDeleteArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByXPath("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();
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
