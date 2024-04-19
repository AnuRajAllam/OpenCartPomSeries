package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink=By.linkText("Logout");
	private By myAccountLink=By.linkText("My Account");
	private By headers =By.cssSelector("div#content h2");
	private By search=By.xpath("//input[@name='search']");
	private By searchIcon=By.cssSelector("button.btn.btn-default.btn-lg");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title=eleUtil.waitForTitleContains(AppConstants.ACCOUNTS_PAGE_TITLE, 5);
		System.out.println("Account page title is: "+title);
		return title;
	}
	
	public String getAccPageUrl() {
		String url=eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, 5);
		System.out.println("Account page url is: "+url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink,5).isDisplayed();
	}
	
	public boolean myAccountLinkExist() {
		return eleUtil.waitForElementVisible(myAccountLink,5).isDisplayed();
	}
	
	public List<String> getAccountsPageHeadersList() {
		List<WebElement> headersEleList=eleUtil.getElements(headers);
		List<String> headersList =new ArrayList<String>();
		for(WebElement e:headersEleList) {
			String header=e.getText();
			headersList .add(header);
		}
			return headersList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Search key is:"+searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
		
	}
	

}
