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
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.Product;
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;
import com.telliant.pageObjects.Vendor;
import com.telliant.pageObjects.Ads;

@Listeners(com.telliant.core.web.Reporting.class)
public class Ads_Functionality_TC extends BaseClass implements ITestListener {
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	Vendor MCWVendor = PageFactory.initElements(driver, Vendor.class);
	Ads Ads = PageFactory.initElements(driver, Ads.class);
	//Admin Login
	@Test(testName = "016", description = "Verify the add ads feature via admin",priority = 1)
	public void AddAds() throws IOException, InterruptedException, ParseException {
		Thread.sleep(2000);
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		Ads.navigateToAds();
		Ads.addNewAds();
		Ads.AdSetupName();
		Ads.LaunchDate();
		Ads.ExpiryDate();
		Ads.AdType();
		Ads.selectAdType();
		Ads.Description();
		Ads.UploadFile();
		Thread.sleep(3000);
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Ad Setup added successfully !");
}
	@Test(testName = "017", description = "Verify the presence of added Ads and editing the same",priority = 2)
	public void EditAds() throws IOException, InterruptedException, ParseException {
		Ads.searchAds();
		MCWSystemsetup.editbtn();
		Ads.AdSetupName();
		Ads.ExpiryDate();
		Ads.Description();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Ad Setup updated successfully !");
		Thread.sleep(2000);
	}
	@Test(testName = "017", description = "Verify the presence of added Ads and editing the same",priority = 3)
	public void DeleteAds() throws IOException, InterruptedException, ParseException {
		Ads.searchAds();
		MCWSystemsetup.deletebtn();
		MCWSystemsetup.cfmpopupbtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Ad Setup Deleted Successfully !");
		loginPage.logout();
	}
}