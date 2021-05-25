package stepDefinitions;

import common.BrowserNotSupportedException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void BaseTest() throws BrowserNotSupportedException {
        String headless=System.getProperty("headless", "false");
        String browser = System.getProperty("browser", "chrome");
        if(browser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
        }
        else
        {
            throw new BrowserNotSupportedException("Browser "+ browser + " is not supported");
        }
        driver.manage().window().maximize();
    }

    @After
    public void afterTest(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            byte[] buffer = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(buffer, "image/png", "error");
        }
        driver.quit();
    }

    @BeforeStep
    public void beforeStep() throws InterruptedException {
        Thread.sleep(500);
        ((JavascriptExecutor)driver).executeScript("var close1 = document.querySelector(\"button.close\"); if(close1) close1.click();");
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("var close2 = document.querySelector('div[id^=\"close-button\"] span'); if(close2) close2.click();");
    }

}
