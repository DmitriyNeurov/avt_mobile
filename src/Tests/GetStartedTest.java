package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{
    @Test
    public void testPathThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())){
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearMoreLink();
        WelcomePage.clickNextButton();
        WelcomePage.waitForNewWaysToExploreText();
        WelcomePage.clickNextButton();
        WelcomePage.waitForAddOrEditPreferredLanguagesText();
        WelcomePage.clickNextButton();
        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();

    }
}
