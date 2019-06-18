package Tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchArticleTitleAndCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        ArticlePageObject.waitForTitleElementMoreTwo();
        SearchPageObject.clickCancelSearch();
        ArticlePageObject.waitForTitleElementToDisappear();
    }
}
