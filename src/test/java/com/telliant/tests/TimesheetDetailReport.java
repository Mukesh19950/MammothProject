package com.telliant.tests;

import java.text.ParseException;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.TimesheetDetailReportPage;

public class TimesheetDetailReport extends BaseClass{
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	TimesheetDetailReportPage TimesheetDR = PageFactory.initElements(driver, TimesheetDetailReportPage.class);
	
	@Test(testName = "Timecard Detail Report_001", description = "Timecards within lastweek range for all employees",priority = 1)
	public void NavigateToTDR() throws InterruptedException, ParseException {
		launchBrowser();
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));		
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 1), ExcelMethods.getData("Sheet1", "Password", 1));
		String validateHomePage=driver.getTitle();
		validateHomePage.contentEquals("Dashboard | QwikTime");		
		Thread.sleep(3000);
		System.out.println(driver.getCurrentUrl());
		System.out.println(	driver.getTitle());
		
		TimesheetDR.NavigateToTimesheetDetailReportPage();
	    TimesheetDR.filterByDropdwon_LastWeek();
	    driver.quit();

		
		
	}
}
