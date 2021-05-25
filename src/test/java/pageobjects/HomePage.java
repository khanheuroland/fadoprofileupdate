package pageobjects;

import common.PageObjectBase;
import io.cucumber.java.bs.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends PageObjectBase {
    By accountEl = By.xpath("//*[@class=\"user-name-col\"]");
    By profileMenuEl = By.cssSelector("div.user-dropdown-menu-inner");
    By editProfileEl = By.xpath("//div[@class=\"user-edit-profile-icon-col\"]/a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getAccountName()
    {
        waitForPageLoaded("https://fado.vn/");
        return this.driver.findElement(accountEl).getText();
    }

    public void OpenProfileUpdate()
    {
        this.driver.findElement(accountEl).click();
        waitForElementToAppear(profileMenuEl);
        this.driver.findElement(editProfileEl).click();
    }
}
