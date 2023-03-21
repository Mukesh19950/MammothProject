package com.telliant.pageObjects;


import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;



public class CashRegisterSetup extends BaseClass {
	
	
	public void coinsbills() throws InterruptedException {
		
		enterCoins("Coins_Pennies", ExcelMethods.getNum("Cash_Register", "Coins", 1));
		enterCoins("Coins_Nickels", ExcelMethods.getNum("Cash_Register", "Coins", 2));
		enterCoins("Coins_Dimes", ExcelMethods.getNum("Cash_Register", "Coins", 3));
		enterCoins("Coins_Quarters", ExcelMethods.getNum("Cash_Register", "Coins", 4));
		enterCoins("Coins_HalfDollars", ExcelMethods.getNum("Cash_Register", "Coins", 5));
		enterBills("Bills_1s", ExcelMethods.getNum("Cash_Register", "Bills", 1));
		enterBills("Bills_5s", ExcelMethods.getNum("Cash_Register", "Bills", 2));
		enterBills("Bills_10s", ExcelMethods.getNum("Cash_Register", "Bills", 3));
		enterBills("Bills_20s", ExcelMethods.getNum("Cash_Register", "Bills", 4));
		enterBills("Bills_50s", ExcelMethods.getNum("Cash_Register", "Bills", 5));
		enterBills("Bills_100s", ExcelMethods.getNum("Cash_Register", "Bills", 6));
		enterHrs("TimeHrs", ExcelMethods.getNum("Cash_Register", "TimeHrs", 1));
		enterHrs("TimeMin", ExcelMethods.getNum("Cash_Register", "TimeMin", 1));
		
		
	}

	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(5000);
		waitForElementVisible("Admin");
		clickElement("Admin");
	}

	public void navigateToCashRegister() throws InterruptedException {
		// Thread.sleep(5000);
		waitForElementVisible("CashRegister");
		clickElement("CashRegister");
	}
	
	public void performactions() throws InterruptedException
	{
		selectDropdownByVisibleTxt("ShopStatus", "Open");
		clickElement("ShopSave");
		Thread.sleep(3000);
		clickElement("BusinessGoal");
	    enterGoal("Goal", ExcelMethods.getNum("Cash_Register", "Goal", 1));		
	    clickElement("GoalSave");
	    
	}

	public void enterCoins(String locator, String Coins) {

		type(locator, Coins);

	}

	public void enterBills(String locator, String Bills) {

		type(locator, Bills);
	}

	public void enterHrs(String locator, String TimeHrs) {
		type(locator, TimeHrs);

	}

	public void enterMin(String locator, String TimeMin) {
		type(locator, TimeMin);

	}

	public void enterGoal(String locator, String Goal) {
		type(locator, Goal);

	}

	
	
}
