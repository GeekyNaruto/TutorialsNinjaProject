package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.MyAccountPage;
import com.tutorialsninja.qa.util.Utilities;

public class LoginTest extends Base{

	public LoginTest()
	{
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod 
	public void setup()
	{
        driver = OpenApp(prop.getProperty("browsername")); 
        HomePage homepage = new HomePage(driver);
        homepage.myaccount_tab_click();
        homepage.login_tab_click();
	}
	
	@AfterMethod 
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1, dataProvider="dp") 
	public void VerifyUsingValid(String email, String password)
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.input_email_bar(email);
		loginpage.input_password_bar(password);
		loginpage.click_login_button();
		
		MyAccountPage myaccountpage = new MyAccountPage(driver);
		myaccountpage.display_edityouraccountinfo();
	
		Assert.assertTrue(myaccountpage.display_edityouraccountinfo(),"Account information is not displayed");
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
		LoginPage loginpage = new LoginPage(driver);
		loginpage.input_email_bar(Utilities.generateemailtimestamp());
		loginpage.input_password_bar(dataprop.getProperty("invalidPassword"));
		loginpage.click_login_button();
		Assert.assertEquals(loginpage.display_warning_message(), dataprop.getProperty("warning"), "Account information is not displayed");
	}
	
	@Test(priority=3)
	public void VerifyUsingValidUsernameInvalidPassword()
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.input_email_bar(dataprop.getProperty("validEmailid"));
		loginpage.input_password_bar(dataprop.getProperty("invalidPassword"));
		loginpage.click_login_button();
		Assert.assertEquals(loginpage.display_warning_message(), dataprop.getProperty("warning"), "Account information is not displayed");
	}
	
	@Test(priority=4)
	public void VerifyUsignInvalidUsernameValidPassword()
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.input_email_bar(Utilities.generateemailtimestamp());
		loginpage.input_password_bar(dataprop.getProperty("validPassword"));
		loginpage.click_login_button();
		Assert.assertEquals(loginpage.display_warning_message(), dataprop.getProperty("warning"), "Account information is not displayed");	
	}
	
	@Test(priority=5)
	public void VerifyUsingNoCredentials()
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.click_login_button();
	    Assert.assertEquals(loginpage.display_warning_message(), dataprop.getProperty("warning"), "Account information is not displayed");
	}
	
	
}
