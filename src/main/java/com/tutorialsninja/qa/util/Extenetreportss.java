package com.tutorialsninja.qa.util;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extenetreportss implements ITestListener{
	
	ExtentSparkReporter sparkreporter;
	ExtentReports extent;
	ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		
		
		File file = new File(System.getProperty("user.dir")+"\\resultsreports\\report1.html");
		sparkreporter = new ExtentSparkReporter(file);
		
		sparkreporter.config().setDocumentTitle("Automation Testing");
		sparkreporter.config().setReportName("OpenCart");
		sparkreporter.config().setTheme(Theme.STANDARD);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkreporter);
		
		  extent.setSystemInfo("Computer Name", "Arvind");
		  extent.setSystemInfo("Environment", "QA");
          extent.setSystemInfo("Tester Name", "Arvind");
	      extent.setSystemInfo("OS", "Windows10");
   	     extent.setSystemInfo("Browser Name", "Chrome");
	}

	@Override
	public void onFinish(ITestContext context) {

     extent.flush();    
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "TEST PASS"+result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
	    WebDriver driver=null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		 
		File source_file =ts.getScreenshotAs(OutputType.FILE);
		File target_file = new File(System.getProperty("user.dir")+"\\ss\\ss1.png");
		source_file.renameTo(target_file);
		
		test.addScreenCaptureFromPath(target_file.getPath());
		
		//test=extent.createTest(result.getTestName());
		test.log(Status.FAIL, "TEST FAIL"+result.getName());
		test.log(Status.FAIL, "TEST FAIL"+result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "TEST SKIPPED"+result.getName());
	}

	
}
