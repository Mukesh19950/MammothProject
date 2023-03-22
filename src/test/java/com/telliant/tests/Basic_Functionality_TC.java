package com.telliant.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.Systemsetup;

@Listeners(com.telliant.core.web.Reporting.class)

public class Basic_Functionality_TC extends BaseClass implements ITestListener{
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup=PageFactory.initElements(driver, Systemsetup.class);
	
	
	//Admin Login
	@Test(testName = "001", description = "Verify the Add Location feature via admin role",priority = 1)
	public void Add_Location_Functionality() throws IOException, InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		MCWSystemsetup.AddNewLoc();
		MCWSystemsetup.locName();
		MCWSystemsetup.address();
		MCWSystemsetup.zipcode("30005");
		MCWSystemsetup.state();
		MCWSystemsetup.stateinput("Georgia");
		MCWSystemsetup.selectstate();
		MCWSystemsetup.citydrpdwn();
		MCWSystemsetup.cityinput("Alpharetta");
		MCWSystemsetup.selectcity();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		System.out.println("52"+getText("successmsg"));
		Assert.assertEquals( getText("successmsg"),"Location added successfully !");
	}


	@Test(testName = "002", description = "Verify the presence of added location and editing the same",priority = 2)
	public void Search_Edit_Locations() throws IOException, InterruptedException {
		MCWSystemsetup.searchtxtbox();
		MCWSystemsetup.searchbtn();
		MCWSystemsetup.editbtn();
		MCWSystemsetup.locName();
		MCWSystemsetup.address();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		System.out.println("64"+getText("successmsg"));
		Assert.assertEquals( getText("successmsg"),"Location updated successfully !");
		
	}
	@Test(testName = "003", description = "Verify the presence of added location and deleting the same",priority = 3)
	public void Search_Delete_Location() throws IOException, InterruptedException {
		Thread.sleep(3000);
		MCWSystemsetup.searchtxtbox();
		MCWSystemsetup.searchbtn();
		MCWSystemsetup.deletebtn();
		MCWSystemsetup.cfmpopupbtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		System.out.println("78"+getText("successmsg"));
		Assert.assertEquals( getText("successmsg"),"Location deleted successfully !");
}
}