package com.telliant.tests;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.Schedules;

public class SchedulesFunctionality_TC extends BaseClass{
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	Schedules schedules = PageFactory.initElements(driver, Schedules.class);
	
	@Test(testName = "SCHEDULES_001", description = "Verify the functionality of Schedules", priority = 1)

	public void schedulesFunc() throws InterruptedException {

		launchURL(config.getProperty("url"));
		String ValidateUrl = driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"), (config.getProperty("password")));
		loginPage.proceed();
		waitTillElementgetsvisible("Admin", 200, 50);
		schedules.navigateToAdmin();
		schedules.navigateSchedules();
		schedules.action();
		loginPage.logout();
		

	}
	
	
	
	
	
	
	
	
	

}
