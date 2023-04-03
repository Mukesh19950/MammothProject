package com.telliant.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.Product;
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;
import com.telliant.pageObjects.Vendor;

@Listeners(com.telliant.core.web.Reporting.class)

public class Vendor_Functionality_TC extends BaseClass implements ITestListener {
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	Vendor MCWVendor = PageFactory.initElements(driver, Vendor.class);
	//Admin Login
	@Test(testName = "010", description = "Verify the add vendor feature via admin",priority = 1)
	public void AddVendor() throws IOException, InterruptedException {
		Thread.sleep(2000);
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		MCWVendor.navigateToVendor();
		MCWVendor.addNewVendor();
		MCWVendor.vendorName();
		MCWVendor.vendorNumber();
		MCWVendor.vendorAccountNumber();
		MCWSystemsetup.zipcode("30005");
		MCWSystemsetup.state();
		MCWVendor.stateinput("Georgia");
		MCWSystemsetup.selectstate();
		MCWSystemsetup.citydrpdwn();
		MCWVendor.cityinput("Alpharetta");
		MCWSystemsetup.selectcity();
		MCWVendor.vendorPhoneNo();
		MCWVendor.vendorEmail();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"),"Vendor added successfully !");

}
	@Test(testName = "011", description = "Verify the presence of added vendor and editing the same",priority = 2)
	public void EditVendor() throws IOException, InterruptedException {
		MCWVendor.searchVendor();
		MCWSystemsetup.editbtn();
		MCWVendor.vendorName();
		MCWVendor.vendorNumber();
		MCWVendor.vendorAccountNumber();
		MCWVendor.vendorEmail();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"),"Vendor updated successfully !");
	}
	@Test(testName = "012", description = "Verify the presence of added vendor and deleting the same",priority = 3)
	public void DeleteProduct() throws IOException, InterruptedException {
		MCWVendor.searchVendor();
		Thread.sleep(3000);
		MCWSystemsetup.deletebtn();
		MCWSystemsetup.cfmpopupbtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"),"Vendor deleted successfully !");
		loginPage.logout();
	}
}