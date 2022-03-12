package Test;

import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import rootpackage.Pages.DataProviderClass;
import rootpackage.Pages.Screenshot;
import rootpackage.Pages.UIMap;
import steps.SearchUIStep;


public class SearchFunction {
    public static WebDriver driver;
    SearchUIStep searchStep;
    public UIMap dataFile;
    String chosenBrowser;

    @Before
    public void pre()
    {
        //se thực hiện trc khi thực hiện từng @test
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","src/test/resources/driver/geckodriver.exe");
    }
    public void setUp() throws Exception {
        // Thực hiện sau khi chạy từng @test
        dataFile = new UIMap("src/test/resources/data/data.properties");
        driver.navigate().to(dataFile.getData("url"));
        driver.manage().window().maximize();

        searchStep = new SearchUIStep(driver);
    }

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{
        chosenBrowser = browser;
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
             driver = new ChromeDriver();
        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        setUp();
    }

    @Test
    public void check_the_display_incase_result_is_not_empty() throws Exception {

        searchStep.searchInNavigatorBar("ha noi,vn");
        // read result info
        searchStep.getResultInfo();
        // lick on result link
        searchStep.clickTheLink();
        // read result info in detail
        searchStep.getResultInfoInDetail();
        //check weather info is display in detail
        searchStep.validateCurrentDate();
        searchStep.validateCityName();
        searchStep.validateWeatherSummary();
        searchStep.validateTemperature();
    }

    @Test(dataProvider="SearchProvider", dataProviderClass = DataProviderClass.class)
    public void check_the_display_incase_there_is_no_result(String searchKey,String message) throws InterruptedException{
        {
            searchStep.searchInNavigatorBar(searchKey);
            //Verify "Not found" message is shown
            searchStep.verifyTheExistOfMessage(message);
        }
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        String methodName = chosenBrowser + "_" + result.getMethod().getMethodName();
        Screenshot screenShort = new Screenshot();
        screenShort.takeACapture(methodName, driver);
    }

    @AfterTest
    public void DoSomeThingAfterEachTest()
    {
    }

    @AfterSuite
    public void clean()
    {
        try{
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
            Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
            Runtime.getRuntime().exec("taskkill /F /IM Console Window Host /T");
        }
        catch (Exception e){}
    }
}
