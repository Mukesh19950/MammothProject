package com.telliant.tests;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
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
import com.telliant.pageObjects.Bonus;
@Listeners(com.telliant.core.web.Reporting.class)
public class Bonus_Functionality_TC extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	Vendor MCWVendor = PageFactory.initElements(driver, Vendor.class);
	Ads Ads = PageFactory.initElements(driver, Ads.class);
	Deals Deals = PageFactory.initElements(driver, Deals.class);
	Bonus Bonus = PageFactory.initElements(driver, Bonus.class);
	
	//Admin Login
	@Test(testName = "021", description = "Verify the add Bonus feature via admin",priority = 1)
	public void AddBonus() throws IOException, InterruptedException, ParseException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		//loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		Bonus.navigateToBonus();
		Bonus.bonusMin();
		Bonus.bonusMax();
		Bonus.bonusAmount();
		Bonus.badReviews();
		Bonus.badReviewDedication();
		Bonus.collisions();
		Bonus.collisionsDedication();
		MCWSystemsetup.savebtn();
		loginPage.logout();
	}

}
