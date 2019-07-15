package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AutorizationPageObject extends MainPageObject{
    private static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name=wpName]",
            PASSWORD_INPUT = "css:input[name=wpPassword]",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AutorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton()
    {
        this.waitForElementPresent(LOGIN_BUTTON,"Cannot find auth button", 15);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 15);
    }

    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot find and put a login to the login input.", 15);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find and put a password to the login input.", 15);
    }

    public  void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click auth button", 10);
    }
}
