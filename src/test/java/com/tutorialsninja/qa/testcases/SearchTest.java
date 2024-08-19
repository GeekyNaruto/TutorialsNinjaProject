package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base{

	public SearchTest()
	{
		super();
	}
	
	public WebDriver driver; 
	@BeforeMethod 
	public void Setup()
	{
		driver = OpenApp(prop.getProperty("browsername"));
		
		
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1) 
	public void SearchWithExistingProduct()
	{
		driver.findElement(By.name("search")).sendKeys("Apple");;
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();

		boolean img = driver.findElement(By.xpath("//div[@class='image']/a/img")).isDisplayed();
		
		Assert.assertTrue(img);
		
	}
	
	@Test(priority=2)
	public void SearchWithNonExistingProduct()
	{
		driver.findElement(By.name("search")).sendKeys("Dettol");
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@type='button']/following-sibling::p")).isDisplayed());
	}

	@Test(priority=3)
	public void SearchWithoutAnyProductName()
	{
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@type='button']/following-sibling::p")).isDisplayed());
	}
	
	@Test(priority=4)
	public void SearchingValidProductAfterLogin() throws InterruptedException
	{ 
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		driver.findElement(By.name("search")).sendKeys("Apple");;
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='image']/a/img")).isDisplayed());
	    Thread.sleep(1500); 	
	}
	
	@Test(priority=5)
	public void AimtoFailTest()
	{
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//input[@type='button']/following-sibling::p")).isDisplayed());
	}
	
	@Test(priority=6, dependsOnMethods = "AimtoFailTest")
	public void AimtoSkip()
	{
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@type='button']/following-sibling::p")).isDisplayed());
	}
}
