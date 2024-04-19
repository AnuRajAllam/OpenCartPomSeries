package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		//LoginPage lp=new 
		String actPgeTitle=lp.getPageTitle();
		Assert.assertEquals(actPgeTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(lp.getPageUrl().contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test(priority = 3)
	public void frgtPwdLnkExistTest() {
		Assert.assertTrue(lp.isForgotPwdExist());
	}
	
	@Test(priority = 4)
	public void LoginTest() {
		//String actPageTitle=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//Assert.assertEquals(actPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
		accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	
	}

}
