package rootpackage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonPage {
    public static String winHandleBefore ;
    public static String currentWindow ;
    public static WebDriver driver;
    public CommonPage(WebDriver driver1)
    {
        //get current window
        //swichi to current window
        winHandleBefore = driver1.getWindowHandle();
        // Switch to new window opened
        for(String winHandle : driver1.getWindowHandles()){
            driver1.switchTo().window(winHandle);
        }
        currentWindow = driver1.getWindowHandle();
        driver = driver1;
    }
///////////////////////Element---------------------------

///////////////////////Action----------------------

    public WebElement findWithImplicitWait(By findElementCondition)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
        return driver.findElement(findElementCondition);
    }
    public WebElement findWithExplicitWait(By waitConditionLocator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
        return wait.until(ExpectedConditions.visibilityOfElementLocated
                (waitConditionLocator));
    }

    public void inputTextFromDataTable(WebElement element, String keyword)
    {
        inputText(element, keyword);
    }
    public void closeWeb()
    {
        driver.close();
    }
    public void clickOnElement(WebElement element)
    {
        element.click();
    }
    public void inputText(WebElement element, String text)
    {
        element.sendKeys(text);
    }
    public void closeCurrentWindow()
    {
        driver.close();
    }
    public void switchToNewWindow()
    {
        winHandleBefore = driver.getWindowHandle();
        currentWindow = driver.getWindowHandles().toArray()[driver.getWindowHandles().size()-1].toString();
        driver.switchTo().window(currentWindow);

    }

}
