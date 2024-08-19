package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class AddToCartTest extends Base{
	
	public WebDriver driver;
	
	public AddToCartTest()
	{
		super();
	}
	
	@BeforeMethod 
	public void setup()
	{
		driver = OpenApp(prop.getProperty("browsername"));
	}
	
	@AfterMethod 
	public void tearup()
	{
		driver.quit();
	}
	
	@Test(priority=1) 
	public void ValidateAddToCartProductDisplayPage()
	{
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("imac");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		driver.findElement(By.xpath("//img[@title='iMac']")).click();
		driver.findElement(By.xpath("//button[@id='button-cart']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='shopping cart']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/img[1]")).isDisplayed());
	}

	@Test(priority=2)
	public void ValidateAddToCartSearchResultPage() throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("imac");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Add to Cart']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='shopping cart']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/img[1]")).isDisplayed());				
	}
	
	@Test(priority=3)
	public void ValidateAddToCartFeaturedItem() throws InterruptedException
	{
		driver.findElement(By.xpath("//body//div[@id='common-home']//div[@class='row']//div[@class='row']//div[2]//div[1]//div[3]//button[1]//span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='shopping cart']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/img[1]")).isDisplayed());
	}
}
