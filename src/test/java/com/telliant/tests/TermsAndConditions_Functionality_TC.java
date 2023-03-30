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
import com.telliant.pageObjects.CheckList;
import com.telliant.pageObjects.Deals;
import com.telliant.pageObjects.EmployeeHandBook;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.Product;
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;
import com.telliant.pageObjects.Vendor;
import com.telliant.pageObjects.TermsAndConditions;

@Listeners(com.telliant.core.web.Reporting.class)
public class TermsAndConditions_Functionality_TC extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	Vendor MCWVendor = PageFactory.initElements(driver, Vendor.class);
	Ads Ads = PageFactory.initElements(driver, Ads.class);
	Deals Deals = PageFactory.initElements(driver, Deals.class);
	CheckList CheckList = PageFactory.initElements(driver, CheckList.class);
	EmployeeHandBook EmployeeHandBook = PageFactory.initElements(driver, EmployeeHandBook.class);
	TermsAndConditions TermsAndConditions = PageFactory.initElements(driver, TermsAndConditions.class);
	
	@Test(testName = "028", description = "Verify the add terms and conditions feature via admin",priority = 1)
	public void AddTerms() throws IOException, InterruptedException, ParseException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		TermsAndConditions.navigateToTermsAndConditions();
		TermsAndConditions.addNewTerms();
		TermsAndConditions.ConditionsName();
		TermsAndConditions.Type();
		TermsAndConditions.selectType();
		TermsAndConditions.UploadDoc();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 100);
		Assert.assertEquals( getText("successmsg"), "Terms & Conditions document added successfully !");
	}
	@Test(testName = "029", description = "Verify the delete terms and conditions feature via admin",priority = 2)
	public void DeleteTerms() throws IOException, InterruptedException, ParseException {
		Thread.sleep(2000);
		TermsAndConditions.Delete();
		MCWSystemsetup.cfmpopupbtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"), "Terms & Conditions document deleted successfully !");
	}

}
