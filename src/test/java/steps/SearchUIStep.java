package steps;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import rootpackage.Pages.CommonPage;
import rootpackage.Pages.DetailSearchPage;
import rootpackage.Pages.HomePage;
import rootpackage.Pages.ResultSearchPage;

import java.util.ArrayList;
import java.util.List;

public class SearchUIStep extends CommonPage {

    public SearchUIStep(WebDriver driver)
    {
        super(driver);
    }
    ResultSearchPage searchPage = new ResultSearchPage(driver);
    DetailSearchPage searchDetailPage = new DetailSearchPage(driver);
    HomePage homePage = new HomePage(driver);
    List<String> resultInfo= null;
    List<String> resultInfoInDetail= null;

    public void searchInNavigatorBar(String keyword)
    {
        homePage.inputTextFromDataTable(homePage.searchNavigatorBarTxt(), keyword);
        homePage.searchNavigatorBarTxt().sendKeys(Keys.ENTER);

    }
    public void searchInBodyPage(String keyword)
    {
        homePage.inputTextFromDataTable(homePage.searchInBodyPageTxt(), keyword);

    }
    public void searchInMiddleOfPage(String keyword)
    {
        homePage.searchNavigatorBarTxt().sendKeys(Keys.ENTER);
        searchPage.inputTextFromDataTable(searchPage.searchTxt(), keyword);

    }
    public void searchInResultPage(String keyword)
    {
        searchDetailPage.inputTextFromDataTable(searchDetailPage.searchTxt(), keyword);

    }
    public void clickTheLink(int index)
    {
        searchPage.getLinkResult(index-1).click();
    }
    public void clickTheLink()
    {
        searchPage.getLinkResult(0).click();
    }
    public List<String> getResultInfo()
    {
        List<String> list = new ArrayList<String>();
        list.add(getCityName(0));
        list.add(getWeatherSummary(0));
        list.add(getTemperate(0));
        resultInfo = list;
        return  list;
    }
    public String getCityName(int index)
    {
        return searchPage.cityNameInfo(searchPage.getRowResult(index));
    }
    public String getTemperate(int index)
    {
        return searchPage.temperateInfo(searchPage.getRowResult(index));
    }
    public String getWeatherSummary(int index)
    {
        return searchPage.weatherSummaryInfo(searchPage.getRowResult(index));
    }
    public List<String> getResultInfoInDetail()
    {
        List<String> list = new ArrayList<String>();
        list.add(getCityNameInDetail());
        list.add(getTemperateInDetail());
        list.add(getWeatherSummaryInDetail());
        list.add(getCurrentDateInDetail());
        resultInfoInDetail = list;
        return  list;
    }
    public String getCityNameInDetail()
    {
        return searchDetailPage.cityNameInfo();
    }
    public String getTemperateInDetail()
    {
        return searchDetailPage.temperateInfo();
    }
    public String getWeatherSummaryInDetail()
    {
        return searchDetailPage.weatherSummaryInfo();
    }
    public String getCurrentDateInDetail()
    {
        return searchDetailPage.dateTimeInfo();
    }
    public void validateCurrentDate()
    {
        String expectedTime = (new java.util.Date()).toString().substring(4,10).replace("0","")
                .toLowerCase();
        String CurrentDateInDetail = resultInfoInDetail.get(3);
        String dislayedTime = CurrentDateInDetail
                .substring(0,CurrentDateInDetail.indexOf(",")).replace("0","")
                .toLowerCase();

        Assert.assertEquals("Current date is displayed: "+dislayedTime+" " +
                "but it must be "+expectedTime,expectedTime,dislayedTime);
    }
    public void validateCityName() {
        String dislayedName = resultInfoInDetail.get(0).replace(" ", "").toLowerCase();
        String expectedName = resultInfo.get(0).replace(" ", "").toLowerCase();

        Assert.assertEquals("City name is displayed: " + dislayedName + " " +
                "but it must be " + expectedName, expectedName, dislayedName);
    }
    public void validateWeatherSummary() {
        String dislayedName = resultInfoInDetail.get(2).trim().toLowerCase();
        String expectedName = resultInfo.get(1).trim().toLowerCase();

        Assert.assertTrue("Weather is say that: "+dislayedName+" " +
                "but it must be "+expectedName,dislayedName.contains(expectedName));
    }
    public void validateTemperature() {
        String dislayedName = resultInfoInDetail.get(1).trim().toLowerCase();
        int index1=dislayedName.length()-2;
        String expectedName = resultInfo.get(2).trim().toLowerCase();
        int index2=expectedName.length()-2;
        Assert.assertEquals("Temperature: "+dislayedName+" " +
                "but it must be "+expectedName,expectedName.substring(0,index1),dislayedName.substring(0,index2));

    }
    public void verifyTheExistOfMessage(String message)
    {
        String actualMsg = searchPage.alertWarning().getText();
        Assert.assertEquals("The message is: " + actualMsg + " but expect one is: " +message,
                message,actualMsg);
    }
}
