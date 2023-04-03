package com.telliant.tests;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.Ads;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.Product;
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;
import com.telliant.pageObjects.Vendor;
import com.telliant.pageObjects.Deals;
@Listeners(com.telliant.core.web.Reporting.class)
public class Deals_Functionality_TC extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	Vendor MCWVendor = PageFactory.initElements(driver, Vendor.class);
	Ads Ads = PageFactory.initElements(driver, Ads.class);
	Deals Deals = PageFactory.initElements(driver, Deals.class);
	//Admin Login
	@Test(testName = "018", description = "Verify the add deals feature via admin",priority = 1)
	public void AddDeals() throws IOException, InterruptedException, ParseException {
		Thread.sleep(2000);
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		Deals.navigateToDeals();
		Deals.addNewDeals();
		Deals.dealsName();
		Deals.selectDealsName();
		Deals.timeperiod();
		Deals.selectTimePeriod();
		Deals.saveDeals();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Deal saved successfully !");
}
	@Test(testName = "019", description = "Verify the edit deals feature via admin",priority = 2)
	public void EditDeals() throws IOException, InterruptedException, ParseException {
		Deals.editDeals();
		Deals.timeperiod();
		Deals.selectTimePeriod();
		Deals.saveDeals();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Deal saved successfully !");
}
	@Test(testName = "020", description = "Verify the delete deals feature via admin",priority = 3)
	public void deleteDeals() throws IOException, InterruptedException, ParseException {
		Deals.deleteDeals();
		MCWSystemsetup.cfmpopupbtn();
		loginPage.logout();
	}
}