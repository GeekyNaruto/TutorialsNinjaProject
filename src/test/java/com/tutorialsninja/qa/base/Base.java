package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.util.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public Base()
	{
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\congif\\config.properties");
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.getStackTrace();
		}
		
		dataprop = new Properties();
		File datapropfile = new File(System.getProperty("user.dir")+"\\src\\\\main\\\\java\\\\com\\\\tutorialsninja\\\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream fis2 = new FileInputStream(datapropfile);
		dataprop.load(fis2);
		}catch(Throwable e) {
			e.getStackTrace();
		}
	}
	
	public WebDriver OpenApp(String browser_name)
	{
		
		if(browser_name.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browser_name.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser_name.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(browser_name.equalsIgnoreCase("safari"))
		{
			driver=new SafariDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		return driver;
				
	}
	
}
