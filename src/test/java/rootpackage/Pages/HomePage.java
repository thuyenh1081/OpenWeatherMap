package rootpackage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends CommonPage {
    public HomePage(WebDriver driver1) {
        super(driver1);
    }

    public WebElement navigatorBar()
    {
        return findWithImplicitWait(By.ByXPath.xpath("//nav[@id='nav-website']"));
    }
    public WebElement searchNavigatorBarTxt()
    {
        return navigatorBar().findElement(By.ByXPath.xpath(".//input[@placeholder='Weather in your city']"));
    }
    public WebElement searchInBodyPageTxt()
    {
        return findWithImplicitWait(By.ByXPath.xpath(".//input[@placeholder='Search city']"));
    }
    /////
    public void searchInNavigatorBar(String keyword)
    {
        inputTextFromDataTable(searchNavigatorBarTxt(), keyword);
        searchNavigatorBarTxt().sendKeys(Keys.ENTER);

    }
    public void searchInBodyPage(String keyword)
    {
        inputTextFromDataTable(searchInBodyPageTxt(), keyword);

    }
}
