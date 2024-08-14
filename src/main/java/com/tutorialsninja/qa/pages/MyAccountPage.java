package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	
	WebDriver driver;
	
	//constructor
    public MyAccountPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    
    //locators
    
    @FindBy(linkText="Edit your account information") WebElement edityouraccountinfo;
    
    
    //Actions methods
    public boolean display_edityouraccountinfo()
    {
    	return edityouraccountinfo.isDisplayed(); 
    }
	
}
