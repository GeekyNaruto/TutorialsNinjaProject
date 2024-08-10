package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.util.Utilities;

public class RegisterTest extends Base{

	public RegisterTest()
	{
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod 
	public void setup()
	{
		driver = OpenApp(prop.getProperty("browsername"));
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
	}
	
	@AfterMethod 
	public void teardown()
	{
		driver.quit();
	}  
	

	@Test(priority=1)
	public void RegisterByOnlyMandatoryField() throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateemailtimestamp());
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("auto12345");
		driver.findElement(By.xpath("//input[@type='password'][@name='confirm']")).sendKeys("auto12345");
	    driver.findElement(By.xpath("//input[@name='agree']")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	    
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(), "Your Account Has Been Created!","Account not created");
	}
	
	@Test(priority=2)
	public void RegisterByAllFields() throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateemailtimestamp());
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("auto12345");
		driver.findElement(By.xpath("//input[@type='password'][@name='confirm']")).sendKeys("auto12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	    driver.findElement(By.xpath("//input[@name='agree']")).click();
	    driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	    Thread.sleep(10000);
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(), "Your Account Has Been Created!","Account not created");

	}
	
	@Test(priority=3)
	public void RegisterWithSameDetails()
	{
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("a");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("a");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("aa11bb@gmail.com");
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@type='password'][@name='confirm']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	    driver.findElement(By.xpath("//input[@name='agree']")).click();
	    driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	    
	    String actual_warningtext = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	    Assert.assertEquals(actual_warningtext,"Warning: E-Mail Address is already registered!","Warning message not displayed");
	}
	
	@Test(priority=4)
	public void RegisterWithNoDetails()
	{
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("");
		driver.findElement(By.xpath("//input[@type='password'][@name='confirm']")).sendKeys("");
	    driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();

	    String privacypolicy_text = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	    Assert.assertEquals(privacypolicy_text, "Warning: You must agree to the Privacy Policy!","Privacy Policy waning not displayed");
	    
	    String FirstNameWarning_text = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
	    Assert.assertEquals(FirstNameWarning_text, "First Name must be between 1 and 32 characters!","First name warning text not displayed");
	    
	    String LastNameWarning_text = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
	    Assert.assertEquals(LastNameWarning_text, "Last Name must be between 1 and 32 characters!","Last name warning text not displayed");
	    
	    String EmailWarning_text = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
	    Assert.assertEquals(EmailWarning_text, "E-Mail Address does not appear to be valid!","Email warning text not displayed");
	    
	    String TelephoneWarning_text = driver.findElement(By.xpath("//input[@name='telephone']/following-sibling::div")).getText();
	    Assert.assertEquals(TelephoneWarning_text, "Telephone must be between 3 and 32 characters!","Telephone warning text not displayed");
	    
	    String PasswordWarning_text = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
	    Assert.assertEquals(PasswordWarning_text, "Password must be between 4 and 20 characters!","Password warning text not displayed");
	       
	}
	
}
