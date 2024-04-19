package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By firstName=By.id("input-firstname");
	By lastName=By.id("input-lastname");
	
	By email=By.name("email");
	By telephone=By.name("telephone");
	
	By pwd=By.xpath("//*[@id=\"input-password\"]");
	By confirmPwd=By.xpath("//*[@id=\"input-confirm\"]");
	
	By isSubscribeYes=By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input");
	By isSubscribeNo=By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input");
	
	By policyCheck=By.name("agree");
	By btnContinue=By.cssSelector("#content > form > div > div > input.btn.btn-primary");
	By successMsg=By.cssSelector("div#content h1");
	By logOut=By.linkText("Logout");
	By registerLink=By.linkText("Register");
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public boolean userRegister(String firstName, String lastName, String email, String telephone,
			String pwd, String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, 10).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.pwd, pwd);
		eleUtil.doSendKeys(this.confirmPwd, pwd);
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(isSubscribeYes);
		}
		else {
			eleUtil.doClick(isSubscribeNo);
		}
		eleUtil.doClick(policyCheck);
		eleUtil.doClick(btnContinue);
		
		String regSuccessMsg=eleUtil.waitForElementVisible(successMsg, 10).getText();
		if(regSuccessMsg.equals(AppConstants.USER_REG_SUCCESS_MSG)) {
			eleUtil.doClick(logOut);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
		
	}

}
