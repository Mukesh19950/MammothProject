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
import com.telliant.pageObjects.Membership;
import com.telliant.pageObjects.Product;
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;
import com.telliant.pageObjects.Vendor;
@Listeners(com.telliant.core.web.Reporting.class)

public class Membership_Functionality_TC extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	Vendor MCWVendor = PageFactory.initElements(driver, Vendor.class);
	Membership MCWMembership =  PageFactory.initElements(driver, Membership.class);
	//Admin Login
	@Test(testName = "013", description = "Verify the add membership feature via admin",priority = 1)
	public void AddMembership() throws IOException, InterruptedException {
		Thread.sleep(2000);
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		MCWMembership.navigateToMembership();
		MCWMembership.addNewMembership();
		MCWMembership.membershipName();
		MCWMembership.washes();
		MCWMembership.selectwashes();
		MCWService.servicePrice();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Membership added successfully !");
}
	@Test(testName = "014", description = "Verify the presence of added membership and editing the same",priority = 2)
	public void SearchMembership() throws IOException, InterruptedException {
		
		MCWMembership.searchMembership();
		MCWSystemsetup.editbtn();
		Thread.sleep(1000);
		MCWMembership.membershipName();
		MCWService.servicePrice();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Membership updated successfully !");
	}
	@Test(testName = "015", description = "Verify the presence of added membership and deleting the same",priority = 3)
	public void DeleteMembership() throws IOException, InterruptedException {
		
		MCWMembership.searchMembership();
		MCWSystemsetup.deletebtn();
		MCWSystemsetup.cfmpopupbtn();
		Thread.sleep(2000);
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"),"Membership deleted successfully !");
		loginPage.logout();
	}
}