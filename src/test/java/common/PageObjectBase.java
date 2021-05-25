package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectBase {
    public static final int TIMEOUT=15;
    private static final int POLLING = 100;
    protected WebDriver driver;
    private WebDriverWait wait;

    public PageObjectBase(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    public void reInitElement()
    {
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    public void clearText(WebElement element)
    {
        while (! element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    protected void waitForMoment()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void waitForPageLoaded(String url)
    {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    protected WebElement waitForElementToAppear(By locator)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected  void waitForElementToDisappear(By locator)
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
