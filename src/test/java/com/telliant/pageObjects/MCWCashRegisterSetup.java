package com.telliant.pageObjects;


import com.telliant.core.web.BaseClass;



public class MCWCashRegisterSetup extends BaseClass {

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

	public void actioncash() throws InterruptedException {
		selectDropdownByVisibleTxt("ShopStatus", "Open");
		clickElement("ShopSave");
		Thread.sleep(3000);
		clickElement("BusinessGoal");
	}

	
	
}
