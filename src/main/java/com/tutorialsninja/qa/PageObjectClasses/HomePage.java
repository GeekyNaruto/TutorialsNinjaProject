package com.tutorialsninja.qa.PageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	//Every page object class contains three things: 1. constructor(to initiate driver), 2. locators 3. Action methods for the locators
	WebDriver driver;
	//constructor
	public HomePage(WebDriver driver)
	{
		driver = this.driver;
	    PageFactory.initElements(driver, this);	
	}
	
	//locators
	
	@FindBy(xpath="//span[contains(text(),'My Account')]") 
	WebElement myaccount_tab;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement login_tab;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement register_tab;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement searchbar;
	
	@FindBy(xpath="//i[@class='fa fa-search']")
	WebElement search_click;

	// Action methods
	
	public void myaccount_tab_click()
	{
		myaccount_tab.click();
	}
	
	public void login_tab_click()
	{
		login_tab.click();
	}
	
	public void register_tab_click()
	{
		register_tab.click();
	}
	
	public void searchbar_input()
	{
		searchbar.sendKeys(null);
	}
	
	
}
