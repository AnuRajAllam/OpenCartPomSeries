package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {
	@BeforeClass
	public void infoPageSetup() {
		accPage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	@Test(dataProvider="getProductSearchData")
	public void productHeaderTest(String searchKey,String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"imac","iMac",3},
			{"macbook", "MacBook Pro",4},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
			
			};
				}
	
	@Test(dataProvider="getProductImagesData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchResultsPage = accPage.doSearch(searchKey);
	productInfoPage = searchResultsPage.selectProduct(productName);
	Assert.assertEquals(productInfoPage.getProductImagesCount(),imagesCount);
	}
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productActDetailsMap =productInfoPage.getProductDetailsMap();
		System.out.println(productActDetailsMap.get("Brand"));
		softAssert.assertEquals(productActDetailsMap.get("Brand"),"Apple");
		softAssert.assertEquals(productActDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productActDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productActDetailsMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productActDetailsMap.get("extaxprice"), "$2,000.00");
		softAssert.assertAll();
	}

}
