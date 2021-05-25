package pageobjects;

import common.PageObjectBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObjectBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form[@id=\"auth-block__login-form\"]//input[@type=\"email\"]")
    public WebElement Email;
    @FindBy(xpath = "//form[@id=\"auth-block__login-form\"]//input[@type=\"password\"]")
    public WebElement Password;
    @FindBy(xpath = "//form[@id=\"auth-block__login-form\"]//button[@type=\"submit\"]")
    public WebElement LoginButton;
}
