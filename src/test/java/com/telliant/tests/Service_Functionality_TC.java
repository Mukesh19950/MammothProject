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
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;

@Listeners(com.telliant.core.web.Reporting.class)

public class Service_Functionality_TC extends BaseClass implements ITestListener{
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	
	//Admin Login
	@Test(testName = "004", description = "Verify the add service feature via admin",priority = 1)
	public void AddService() throws IOException, InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		//loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		MCWService.navigateToService();
		MCWService.addNewService();
		MCWService.serviceName();
		MCWService.serviceType();
		MCWService.selectType();
		MCWService.servicePrice();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		System.out.println("52"+getText("successmsg"));
		Assert.assertEquals( getText("successmsg"),"Service added successfully !");
	}
	@Test(testName = "005", description = "Verify the presence of added service and editing the same",priority = 2)
	public void SearchService() throws IOException, InterruptedException {
		
		MCWService.searchService();
		MCWSystemsetup.searchbtn();
		MCWSystemsetup.editbtn();
		MCWService.serviceName();
		MCWService.servicePrice();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		System.out.println("52"+getText("successmsg"));
		Assert.assertEquals( getText("successmsg"),"Service updated successfully !");
	}
	@Test(testName = "006", description = "Verify the presence of added location and deleting the same",priority = 3)
	public void DeleteService() throws IOException, InterruptedException {
		
		Thread.sleep(3000);
		MCWService.searchService();
		//MCWSystemsetup.searchbtn();
		MCWSystemsetup.deletebtn();
		MCWSystemsetup.cfmpopupbtn();
		Assert.assertEquals( getText("successmsg"),"Service deleted successfully !");
		loginPage.logout();
	}
}