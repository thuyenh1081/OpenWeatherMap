package rootpackage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ResultSearchPage extends  CommonPage {
    public ResultSearchPage(WebDriver driver)
    {
        super(driver);
    }


    public WebElement searchForm()
    {
        return findWithExplicitWait(By.ById.id("searchform"));
    }
    public WebElement searchTxt()
    {
        return searchForm().findElement(By.xpath(".//input[@id='search_str']"));
    }
    public WebElement resultArea()
    {
        return findWithExplicitWait(By.ById.id("forecast-list"));
    }
    public WebElement resultTlb()
    {

        return resultArea().findElement(By.xpath(".//table[*]"));
    }
    public List<WebElement> resultList()
    {

        return resultTlb().findElements(By.xpath(".//tr[*]"));
    }
    public WebElement getRowResult(String name)
    {
        return resultTlb().findElement(By.xpath(".//a[text()='"+name+"']"))
                .findElement(By.xpath("..")).findElement(By.xpath(".."));
    }
    public WebElement getRowResult(int index)
    {
        return resultList().get(index);
    }
    public WebElement getLinkResult(String name)
    {
        return resultTlb().findElement(By.xpath(".//a[text()='"+name+"']"));
    }
    public WebElement getLinkResult(int index)
    {
        return resultList().get(index).findElement(By.tagName("a"));
    }
    public String cityNameInfo(WebElement row)
    {
        return row.findElement(By.tagName("a")).getText();
    }
    public String temperateInfo(WebElement row)
    {
        return row.findElements(By.tagName("td")).get(1)
                .findElements(By.tagName("p")).get(0).findElement(By.tagName("span")).getText();
    }
    public String weatherSummaryInfo(WebElement row)
    {
        return row.findElements(By.tagName("td")).get(1)
                .findElements(By.tagName("b")).get(1).findElement(By.tagName("i")).getText();
    }

    public WebElement alertWarning()
    {
        return resultArea().findElement(By.xpath(".//div[@class='alert alert-warning']"));
    }
}
