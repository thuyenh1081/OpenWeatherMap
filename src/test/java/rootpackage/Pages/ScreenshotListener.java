package rootpackage.Pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.*;//.TakesScreenshot;
import org.testng.*;


import java.text.SimpleDateFormat;
//import java.util.Calendar;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.text.*;

public class ScreenshotListener implements ITestListener {
    public WebDriver driver;
    String methodName;

    public void takeACapture(String methodName, WebDriver driver)
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
            File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
            FileUtils.copyFile(scrFile, destFile);
            Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void takeACapture(WebDriver driver)
    {
        takeACapture(methodName, driver);
    }


    @Override
    public void onTestFailure(ITestResult result) {
//        if(!result.isSuccess()) {
//            takeACapture(result);
//        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        if(result.isSuccess()){
//            takeACapture(result);
//        }
    }

    @Override
    public void onFinish(ITestContext Result)
    {

    }

    @Override
    public void onStart(ITestContext Result)
    {
        methodName = Result.getName();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result)
    {
        System.out.println("The name of the testcase Skipped is :"+Result.getName());
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result)
    {
        System.out.println(Result.getName()+" test case started");
    }

}
