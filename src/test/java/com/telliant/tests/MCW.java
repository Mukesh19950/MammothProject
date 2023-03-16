package com.telliant.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.MCWHomePage;
import com.telliant.pageObjects.MCWSystemsetup;

@Listeners(com.telliant.tests.SmokeTestCases.class)

public class MCW extends BaseClass implements ITestListener{
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	MCWHomePage MCWHomePage = PageFactory.initElements(driver, MCWHomePage.class);
	MCWSystemsetup MCWSystemsetup=PageFactory.initElements(driver, MCWSystemsetup.class);
	
	
	//Admin Login
	@Test(testName = "001", description = "Login as Admin",priority = 1)
	public void TC01_Login() throws IOException, InterruptedException {
		
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
		MCWSystemsetup.citydrpdwn();
		MCWSystemsetup.cityinput("Alpharetta");
		MCWSystemsetup.savebtn();
	}
}