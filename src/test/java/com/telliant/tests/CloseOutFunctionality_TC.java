package com.telliant.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.CloseOutRegister;
@Listeners(com.telliant.core.web.Reporting.class)
public class CloseOutFunctionality_TC extends BaseClass {
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	CloseOutRegister closeoutregister = PageFactory.initElements(driver, CloseOutRegister.class);

	
	int rowNo=1;
	
	@Test(testName = "CLOSEOUT_001", description = "Verify the functionality of Close Out Register",priority=1)
	public void CloseOutRegister() throws InterruptedException {
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		closeoutregister.navigateToAdmin();
		closeoutregister.navigateToCloseOutRegister();	
		closeoutregister.coinsbills();
		loginPage.logout();
	}

}
