package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest  extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registrationPage=lp.navigateToRegisterPage();
		
	}
	@DataProvider
	public Object[][] getUserRegData() {
	return new	Object[][] {
		{"anu","raj","123567890","anu@123","yes"},
		{"raj","alla","123567690","raj@123","yes"},
		{"arnika","raj","123467890","arni@123","yes"},
	};
	}
	
	@DataProvider
	public Object[][] getUserRegDataFromExcel() {
	return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_Name);


	}
	@Test(dataProvider="getUserRegDataFromExcel")
	public void userRegTest(String firstName, String lastName,String telephone,String pwd, String subscribe) {
		Assert.assertTrue(registrationPage.userRegister(firstName, lastName, StringUtil.getRandomEmailId(),
				telephone ,pwd, subscribe));
		
	}
}
