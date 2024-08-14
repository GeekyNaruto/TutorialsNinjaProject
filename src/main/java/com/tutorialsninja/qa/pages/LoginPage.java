package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	// constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	
	@FindBy(id="input-email") WebElement email_bar;
	@FindBy(id="input-password") WebElement password_bar;
	@FindBy(xpath="//input[@value='Login']") WebElement login_button;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']") WebElement warning_message;
	
	//Action methods
	
	public void input_email_bar(String email)
	{
		email_bar.sendKeys(email);
	}
	
	public void input_password_bar(String password)
	{
		password_bar.sendKeys(password);
	}
	
	public void click_login_button()
	{
		login_button.click();
	}
	
	public String display_warning_message()
	{
		return warning_message.getText();
	}
	
}
