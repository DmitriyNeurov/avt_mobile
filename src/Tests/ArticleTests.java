package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase  {

    @Test
    public void testSwipeArticle()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstringByXPath("Appium");
        ArticlePageObject.swipeToFooter();
    }
    @Test
    public void testFindTitlePresentWithoutTimeout()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByXPath("Object-oriented programming language");
        SearchPageObject.assertElementPresent("Java (programming language)");
    }
}
