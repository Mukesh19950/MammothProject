package com.telliant.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import com.telliant.pageObjects.MCWCashRegisterSetup;
import com.telliant.pageObjects.LoginPage;

@Listeners(com.telliant.core.web.Reporting.class)
public class MCWCashRegister extends BaseClass {
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	MCWCashRegisterSetup cashregister = PageFactory.initElements(driver, MCWCashRegisterSetup.class);
	
	int rowNo=1;
	
	@Test(testName = "Verify Cash Register", description = "Verify Cash Register",priority=1)
	public void CashRegister_Setup() throws InterruptedException {
	
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		cashregister.navigateToAdmin();
		cashregister.navigateToCashRegister();
		cashregister.enterCoins("Coins_Pennies", ExcelMethods.getNum("Cash_Register", "Coins", 1));
		cashregister.enterCoins("Coins_Nickels", ExcelMethods.getNum("Cash_Register", "Coins", 2));
		cashregister.enterCoins("Coins_Dimes", ExcelMethods.getNum("Cash_Register", "Coins", 3));
		cashregister.enterCoins("Coins_Quarters", ExcelMethods.getNum("Cash_Register", "Coins", 4));
		cashregister.enterCoins("Coins_HalfDollars", ExcelMethods.getNum("Cash_Register", "Coins", 5));
		cashregister.enterCoins("Bills_1s", ExcelMethods.getNum("Cash_Register", "Bills", 1));
		cashregister.enterCoins("Bills_5s", ExcelMethods.getNum("Cash_Register", "Bills", 2));
		cashregister.enterCoins("Bills_10s", ExcelMethods.getNum("Cash_Register", "Bills", 3));
		cashregister.enterCoins("Bills_20s", ExcelMethods.getNum("Cash_Register", "Bills", 4));
		cashregister.enterCoins("Bills_50s", ExcelMethods.getNum("Cash_Register", "Bills", 5));
		cashregister.enterCoins("Bills_100s", ExcelMethods.getNum("Cash_Register", "Bills", 6));
		cashregister.enterHrs("TimeHrs", ExcelMethods.getNum("Cash_Register", "TimeHrs", 1));
		cashregister.enterHrs("TimeMin", ExcelMethods.getNum("Cash_Register", "TimeMin", 1));
		cashregister.actioncash();
		cashregister.enterGoal("Goal", ExcelMethods.getNum("Cash_Register", "Goal", 1));
		clickElement("GoalSave");
		
		
		
		
		
	}

	


}
