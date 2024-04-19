package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	protected LoginPage lp;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage ;
	protected SoftAssert softAssert;
	protected RegistrationPage registrationPage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		
		prop=df.initprop();
	//	prop = df.initProp();
		driver=df.initDiver(prop);
		//driver = df.initDriver(prop);
		lp = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	

}


