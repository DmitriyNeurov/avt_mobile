package Tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testSearchArticleTitleAndCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        ArticlePageObject.waitForNumberOfResultsFound(2);
        SearchPageObject.clickCancelSearch();
        ArticlePageObject.waitForTitleElementToDisappear();
    }
    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results", amount_of_search_results > 0);
    }
    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "gsrggdg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchTitleAndDescription()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Object-oriented programming language");
    }

    @Test
    public void testSearchFirstThreeResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLine("Java");
        ArticlePageObject.waitForNumberOfResultsFound(3);
        SearchPageObject.searchResultsTitleAndDescriptionByIndex("0", "Java", "Island of Indonesia");
        SearchPageObject.searchResultsTitleAndDescriptionByIndex("1", "JavaScript", "Programming language");
        SearchPageObject.searchResultsTitleAndDescriptionByIndex("2", "Java (programming language)", "Object-oriented programming language");

    }

}
