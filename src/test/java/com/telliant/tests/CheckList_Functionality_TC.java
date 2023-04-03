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
import com.telliant.pageObjects.Deals;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.Product;
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;
import com.telliant.pageObjects.Vendor;
import com.telliant.pageObjects.CheckList;
@Listeners(com.telliant.core.web.Reporting.class)
public class CheckList_Functionality_TC extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	Vendor MCWVendor = PageFactory.initElements(driver, Vendor.class);
	Ads Ads = PageFactory.initElements(driver, Ads.class);
	Deals Deals = PageFactory.initElements(driver, Deals.class);
	CheckList CheckList = PageFactory.initElements(driver, CheckList.class);
	@Test(testName = "022", description = "Verify the add checklist feature via admin",priority = 1)
	public void AddCheckList() throws IOException, InterruptedException, ParseException {
		Thread.sleep(2000);
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		CheckList.navigateToCheckList();
		CheckList.addNewItem();
		CheckList.checkListName();
		CheckList.Role();
		CheckList.selectRole();
		CheckList.timeHH();
		CheckList.timeMM();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Check List updated successfully !");

}
	@Test(testName = "023", description = "Verify the edit checklist feature via admin",priority = 2)
	public void EditCheckList() throws IOException, InterruptedException, ParseException {
	
		CheckList.selectPage();
		CheckList.editCheckList();
		CheckList.Role();
		CheckList.selectRole();
		CheckList.timeHH();
		CheckList.timeMM();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 100);
		Assert.assertEquals( getText("successmsg"), "Check List updated successfully !");
}

	@Test(testName = "024", description = "Verify the delete checklist feature via admin",priority = 3)
	public void DeleteCheckList() throws IOException, InterruptedException, ParseException {
		
		Thread.sleep(3000);
		CheckList.deleteCheckList();
		MCWSystemsetup.cfmpopupbtn();
		waitTillElementgetsvisible("successmsg", 200, 100);
		Assert.assertEquals( getText("successmsg"), "Check List deleted successfully !");
	}
}