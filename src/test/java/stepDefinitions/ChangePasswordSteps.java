package stepDefinitions;

import common.PasswordHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Profile;
import org.openqa.selenium.WebDriver;
import pageobjects.ProfilePage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChangePasswordSteps {
    WebDriver driver;
    ProfilePage profilePage;
    Profile user;
    public ChangePasswordSteps()
    {
        this.driver = Hooks.driver;
        user = Profile.FromResource();
    }

    @Given("^The change password screen is showed$")
    public void the_change_password_screen_is_showed() {
        this.profilePage = new ProfilePage(this.driver);
        this.profilePage.ChangePasswordNav.click();
        this.profilePage.reInitElement();
    }

    @When("^The user attempt to change current password to another valid password$")
    public void the_user_attempt_to_change_current_password_to_another_valid_password() {
        PasswordHelper pwdHelper = new PasswordHelper();
        String newPass = pwdHelper.getPassword(10);
        this.profilePage.OldPassword.sendKeys(user.Password);
        this.profilePage.NewPassword.sendKeys(newPass);
        this.profilePage.ConfirmNewPassword.sendKeys(newPass);
        this.profilePage.SavePassword.click();

        System.out.println(newPass);
        user.Password = newPass;
        user.Save();
    }

    /*
    @Then("^The message \"([^\"]*)\" will be showed$")
    public void the_message_notify_update_is_successful_will_be_showed(String msg) {
        assertThat(this.profilePage.getSucessMessage(), is(msg));
    }*/
}
