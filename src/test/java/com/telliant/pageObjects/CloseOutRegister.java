package com.telliant.pageObjects;


import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;



public class CloseOutRegister extends BaseClass {
	
	
public void coinsbills() throws InterruptedException {
		
		enterCoins("Coins_Pennies", ExcelMethods.getNum("Close_Out_Register", "Coins", 1));
		enterCoins("Coins_Nickels", ExcelMethods.getNum("Close_Out_Register", "Coins", 2));
		enterCoins("Coins_Dimes", ExcelMethods.getNum("Close_Out_Register", "Coins", 3));
		enterCoins("Coins_Quarters", ExcelMethods.getNum("Close_Out_Register", "Coins", 4));
		enterCoins("Coins_HalfDollars", ExcelMethods.getNum("Close_Out_Register", "Coins", 5));
		enterBills("Bills_1s", ExcelMethods.getNum("Close_Out_Register", "Bills", 1));
		enterBills("Bills_5s", ExcelMethods.getNum("Close_Out_Register", "Bills", 2));
		enterBills("Bills_10s", ExcelMethods.getNum("Close_Out_Register", "Bills", 3));
		enterBills("Bills_20s", ExcelMethods.getNum("Close_Out_Register", "Bills", 4));
		enterBills("Bills_50s", ExcelMethods.getNum("Close_Out_Register", "Bills", 5));
		enterBills("Bills_100s", ExcelMethods.getNum("Close_Out_Register", "Bills", 6));
	
		type("Tip", ExcelMethods.getNum("Close_Out_Register", "Tips", 1));	
		scrollToElement("CloseSave");
		clickElement("CloseSave");
		
	}
	
	
	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(5000);
		waitForElementVisible("Admin");
		clickElement("Admin");
	}
	
	public void navigateToCloseOutRegister() throws InterruptedException {
		// Thread.sleep(5000);
		waitForElementVisible("CloseOutRegister");
		clickElement("CloseOutRegister");
	}

	public void enterCoins(String locator, String Coins) {

		type(locator, Coins);

	}

	public void enterBills(String locator, String Bills) {

		type(locator, Bills);
	}

}
