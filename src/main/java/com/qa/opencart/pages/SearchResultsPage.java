package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProducts=By.cssSelector("div.product-thumb");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public int getSearchProductCount() {
		return eleUtil.waitForElementsVisible(searchProducts,20).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("searching for the product: "+productName);
		eleUtil.waitForElementVisible(By.linkText(productName), 20).click();
		return new ProductInfoPage(driver);
	}
}
