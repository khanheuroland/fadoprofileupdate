package stepDefinitions;

import io.cucumber.java.en.*;
import models.Profile;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePage;
import pageobjects.ProfilePage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProfileSteps {
    WebDriver driver;
    ProfilePage profilePage;
    public ProfileSteps()
    {
        this.driver = Hooks.driver;
    }
    @Given("^The update user information form is showed$")
    public void the_update_user_information_form_is_showed() {
        HomePage homePage = new HomePage(this.driver);
        homePage.OpenProfileUpdate();
        this.profilePage = new ProfilePage(this.driver);
    }

    @When("^The user try to show the Calendar popup for new value for birthday. \"([^\"]*)\" for example$")
    public void the_user_try_to_show_the_calendar_popup_for_new_value_for_birthday_something_for_example(String inputDateValue) throws ParseException {
        /*1. Chuyển param value sang date */
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
        Date inputDate = format.parse(inputDateValue);
        /*2. Pass date value to profile page for selection*/
        this.profilePage.SelectDate(inputDate);
    }

    @When("^The user attempt to clear value of Fullname and Phone$")
    public void the_user_attempt_to_clear_value_of_fullname_and_phone() {
        this.profilePage.clearText(this.profilePage.Fullname);
        this.profilePage.Fullname.sendKeys(Keys.ENTER);
        this.profilePage.clearText(this.profilePage.Phone);
        this.profilePage.Fullname.sendKeys(Keys.ENTER);
    }

    @When("^The user attempt to update with all valid values$")
    public void the_user_attempt_to_update_with_all_valid_values() {
        this.profilePage.Save.click();
    }

    @When("^The user attempt to clear all values$")
    public void the_user_attempt_to_clear_all_values() {
        this.profilePage.Reset.click();
    }

    @Then("^The current user information is showed for default$")
    public void the_current_user_information_is_showed_for_default() {
        Profile expectedProfile = Profile.FromResource();
        assertThat(this.profilePage.Fullname.getAttribute("value"), is(expectedProfile.FullName));
        assertThat(this.profilePage.Phone.getAttribute("value"), is(expectedProfile.Phone));
        assertThat(this.profilePage.Email.getAttribute("value"), is(expectedProfile.Email));
        assertThat(this.profilePage.Birthday.getAttribute("value"), is(expectedProfile.DateOfBirth));
        assertThat(this.profilePage.Male.isSelected(), is(expectedProfile.Gender));
    }

    @Then("^The value of date of birth will be updated to \"([^\"]*)\"$")
    public void the_value_of_date_of_birth_will_be_updated_to_something(String date) {
        assertThat(this.profilePage.Birthday.getAttribute("value"), is(date));
    }

    @Then("^The message \"([^\"]*)\" will be displayed below Fullname field$")
    public void the_message_something_will_be_displayed_below_fullname_field(String msg) {
        assertThat(this.profilePage.FullNameRequired.getText(), is(msg));
    }

    @Then("^The message \"([^\"]*)\" will be showed$")
    public void the_message_notify_update_is_successful_will_be_showed(String msg) {
        assertThat(this.profilePage.getSucessMessage(), is(msg));
    }

    @Then("^The user should see blank value for all except email field$")
    public void the_user_should_see_blank_value_for_all_except_email_field() {
        assertThat(this.profilePage.Fullname.getAttribute("value"), is(""));
        assertThat(this.profilePage.Phone.getAttribute("value"), is(""));
        assertThat(this.profilePage.Email.getAttribute("value"), not(is("")));
        assertThat(this.profilePage.Birthday.getAttribute("value"), is(""));
        assertThat(this.profilePage.Male.isSelected(), is(false));
        assertThat(this.profilePage.FeMale.isSelected(), is(false));
    }

    @And("^The email information can't be updated$")
    public void the_email_information_cant_be_updated() {
        /*Check disabled attribute của input field. Nếu giá trị là true thì input field sẽ ko thể edit*/
        assertThat(this.profilePage.Email.getAttribute("disabled"), is("true"));
    }

    @And("^The date of birth is display as dd-mm-yyyy format.$")
    public void the_date_of_birth_is_display_as_ddmmyyyy_format() {
        /*Sử dụng Regex để validate format của date of birth value*/
        String dateFormat = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[1-2])-\\d{4}";
        Pattern dateFormatPattern = Pattern.compile(dateFormat);
        Matcher dateMatcher = dateFormatPattern.matcher(this.profilePage.Birthday.getAttribute("value"));
        assertThat(dateMatcher.matches(), is(true));
    }

    @And("^The message \"([^\"]*)\" will be displayed below phone number$")
    public void the_message_something_will_be_displayed_below_phone_number(String msg) {
        assertThat(this.profilePage.PhoneRequired.getText(), is(msg));
    }

    @And("^The email field value is \"([^\"]*)\"$")
    public void the_email_field_value_is_something(String emailValue) {
        assertThat(this.profilePage.Email.getAttribute("value"), is(emailValue));
    }
}
