package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

By email=By.id("input-email");
By pwd=By.id("input-password");
By login=By.xpath("//input[@value='Login']");
By frgtPwd=By.linkText("Forgotten Password");
By regiterLink=By.linkText("Register");

public LoginPage(WebDriver driver) {
	this.driver=driver;
	eleUtil=new ElementUtil(driver);
}

public String getPageUrl() {
	String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION,5);
	//String url=driver.getCurrentUrl();
	System.out.println("login page url is : "+url);
	return url;
}

public String getPageTitle() {
	String title=eleUtil.waitForTitleContains(AppConstants.LOGIN_PAGE_TITLE, 5);
	//String title=driver.getTitle();
	System.out.println("login page title is : "+title);
	return title;
}

public boolean isForgotPwdExist() {
	return eleUtil.isElementDisplayed(frgtPwd);
	//return driver.findElement(frgtPwd).isDisplayed();
}

public AccountsPage doLogin(String userName, String passwd) {
	eleUtil.waitForElementVisible(email, 10).sendKeys(userName);
	eleUtil.doSendKeys(pwd, passwd);
	eleUtil.doClick(login,10);
	return new AccountsPage(driver);
}

public RegistrationPage navigateToRegisterPage() {
	eleUtil.waitForElementVisible(regiterLink, 10).click();
	return new RegistrationPage(driver);
}

}
