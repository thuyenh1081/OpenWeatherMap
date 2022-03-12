package rootpackage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class
DetailSearchPage extends CommonPage{
    public DetailSearchPage(WebDriver driver)
    {
        super(driver);
    }


    public WebElement searchTxt()
    {
        return findWithExplicitWait(By.ById.id("search_str"));
    }
    public WebElement weatherWiget()
    {
        return findWithExplicitWait(By.ById.id("weather-widget"));
    }
    public WebElement currentWeatherArea()
    {
        return weatherWiget().findElement(By.xpath(".//div[@class = 'current-container mobile-padding']"));
    }
    public String dateTimeInfo()
    {
        return currentWeatherArea().findElements(By.tagName("div")).get(0).findElement(By.tagName("span")).getText();
    }
    public String cityNameInfo()
    {
        return currentWeatherArea().findElements(By.tagName("div")).get(0).findElement(By.tagName("h2")).getText();
    }
    public String temperateInfo()
    {
        return currentWeatherArea().findElements(By.tagName("div")).get(1)
                .findElement(By.className("current-temp")).findElement(By.tagName("span")).getText();
    }
    public String weatherSummaryInfo()
    {
        return currentWeatherArea().findElements(By.tagName("div")).get(1)
                .findElements(By.tagName("div")).get(1).getText();
    }

}
