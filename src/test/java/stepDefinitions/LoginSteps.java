package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.Profile;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSteps {
    WebDriver driver;
    public LoginPage loginPage;
    Profile user;
    public LoginSteps()
    {
        this.driver = Hooks.driver;
        user = Profile.FromResource();
    }

    @Given("^The login page is showed$")
    public void the_login_page_is_showed() {
        this.driver.get("https://fado.vn/dang-nhap?redirect=https%3A%2F%2Ffado.vn%2F");
        this.loginPage = new LoginPage(this.driver);
    }

    @When("^The user attempt to login with his account$")
    public void the_user_attempt_to_login_with_his_account() {
        loginPage.Email.sendKeys(user.Email);
        loginPage.Password.sendKeys(user.Password);
        loginPage.LoginButton.click();
    }

    @Then("^The dashboard will be showed$")
    public void the_dashboard_will_be_showed() {
        HomePage homePage = new HomePage(this.driver);
        assertThat(homePage.getAccountName(), is(user.FullName));
    }
}
