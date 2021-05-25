package pageobjects;

import common.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.Date;

public class ProfilePage extends PageObjectBase {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@name=\"fullname\"]")
    public WebElement Fullname;
    @FindBy(xpath = "//input[@name=\"phone\"]")
    public WebElement Phone;
    @FindBy(xpath = "//div[@class=\"user-block__body\"]//input[@name=\"email\"]")
    public WebElement Email;
    @FindBy(xpath = "//input[@name=\"birthdate\"]")
    public WebElement Birthday;
    @FindBy(xpath = "//input[@name=\"gender\"][@value=\"1\"]")
    public WebElement Male;
    @FindBy(xpath = "//input[@name=\"gender\"][@value=\"2\"]")
    public WebElement FeMale;
    @FindBy(xpath = "//div[@class=\"user-block__body\"]//button[@type=\"submit\"]")
    public WebElement Save;
    @FindBy(xpath = "//div[@class=\"user-block__body\"]//button[@type=\"button\"]")
    public WebElement Reset;
    @FindBy(xpath = "//input[@name=\"fullname\"]//../label")
    public WebElement FullNameRequired;
    @FindBy(xpath = "//input[@name=\"phone\"]//../label")
    public WebElement PhoneRequired;

    /*
    Change password
     */
    @FindBy(xpath = "//a[@href=\"/thay-doi-mat-khau\"]")
    public WebElement ChangePasswordNav;
    @FindBy(xpath = "//input[@name=\"oldPassword\"]")
    public WebElement OldPassword;
    @FindBy(xpath = "//input[@name=\"newPassword\"]")
    public WebElement NewPassword;
    @FindBy(xpath = "//input[@name=\"reNewPassword\"]")
    public WebElement ConfirmNewPassword;
    @FindBy(css = "form.user-block__input-form button[type=\"submit\"]")
    public WebElement SavePassword;

    By successMessage = By.cssSelector("div.my-alert.-alert-success");
    By datePicker = By.cssSelector("div.flatpickr-calendar");
    By datePickerYear = By.cssSelector("input.cur-year");
    By datePickerMonth = By.cssSelector("select.flatpickr-monthDropdown-months");
    String datePickerDayLoc = "//span[@class=\"flatpickr-day \" and text()=\"#\"]";
    public void SelectDate(Date dateVal)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateVal);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        this.Birthday.click();
        this.waitForElementToAppear(datePicker);
        this.driver.findElement(datePickerYear).clear();
        this.driver.findElement(datePickerYear).sendKeys(String.valueOf(year));
        //Select month
        Select monthSelection = new Select(this.driver.findElement(datePickerMonth));
        monthSelection.selectByValue(String.valueOf(month));
        //Select date
        By dateLocator = By.xpath(datePickerDayLoc.replace("#", String.valueOf(day)));
        this.driver.findElement(dateLocator).click();

    }

    public String getSucessMessage()
    {
        WebElement successMsg = waitForElementToAppear(successMessage);
        return successMsg.getText();
    }
}
