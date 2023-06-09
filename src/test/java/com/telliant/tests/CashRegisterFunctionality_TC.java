package com.telliant.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;

import com.telliant.pageObjects.CashRegisterSetup;

import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.AddNewEmployee;

@Listeners(com.telliant.core.web.Reporting.class)
public class CashRegisterFunctionality_TC extends BaseClass {

	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	CashRegisterSetup cashregister = PageFactory.initElements(driver, CashRegisterSetup.class);
	AddNewEmployee addemployee = PageFactory.initElements(driver, AddNewEmployee.class);

	@Test(testName = "CASH_REG001", description = "Verify the functionality of Cash Register", priority = 1)
	public void CashRegister_Setup() throws InterruptedException {

		launchURL(config.getProperty("url"));
		String ValidateUrl = driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"), (config.getProperty("password")));
		loginPage.proceed();
		waitTillElementgetsvisible("Admin", 200, 50);
		cashregister.navigateToAdmin();
		cashregister.navigateToCashRegister();
		cashregister.coinsbills();
		cashregister.performactions();
		waitTillElementgetsvisible("success", 200, 50);
		Assert.assertEquals(getText("success"), "Cash Register updated successfully!");
		loginPage.logout();

	}

}
