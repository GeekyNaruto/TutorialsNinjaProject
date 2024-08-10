package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.util.Utilities;

public class LoginTest extends Base{

	public LoginTest()
	{
		super();
	}
	WebDriver driver;
	
	@BeforeMethod 
	public void setup()
	{
        driver = OpenApp(prop.getProperty("browsername")); 
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
	}
	
	@AfterMethod 
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1, dataProvider="dp") 
	public void VerifyUsingValid(String email, String password)
	{
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
	    boolean status = driver.findElement(By.linkText("Edit your account information")).isDisplayed();
		
		Assert.assertTrue(status,"Account information is not displayed");
	}
	
	//hardcoding of multiple data
	/*@DataProvider(name="dp")
	public Object[][] supplyTestdata()
	{
		Object[][] data = {
				{"amotooricap9@gmail.com","12345"},
				{"amotooricap1@gmail.com","12345"},
				{"amotooricap3@gmail.com","12345"}
		};
		return data;
    }*/

   @DataProvider(name="dp")
   public Object[][] supplyTestdata()
	{
		Object[][] data = Utilities.gettestdata("Login");
		return data;
	}

	
	@Test(priority=2)
	public void VerifyUsingInvalid()
	{
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateemailtimestamp());
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
        String warning_message = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		
		Assert.assertEquals(warning_message, dataprop.getProperty("warning"), "Account information is not displayed");
	}
	
	@Test(priority=3)
	public void VerifyUsingValidUsernameInvalidPassword()
	{
		driver.findElement(By.id("input-email")).sendKeys(dataprop.getProperty("validEmailid"));
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
        String warning_message = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		
		Assert.assertEquals(warning_message, dataprop.getProperty("warning"), "Account information is not displayed");
	}
	
	@Test(priority=4)
	public void VerifyUsignInvalidUsernameValidPassword()
	{
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateemailtimestamp());
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
        String warning_message = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		
		Assert.assertEquals(warning_message, dataprop.getProperty("warning"), "Account information is not displayed");	
	}
	
	@Test(priority=5)
	public void VerifyUsingNoCredentials()
	{
		//driver.findElement(By.id("input-email")).sendKeys("");
		//driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
        String warning_message = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		
		Assert.assertEquals(warning_message, dataprop.getProperty("warning"), "Account information is not displayed");
	}
	
	
}
