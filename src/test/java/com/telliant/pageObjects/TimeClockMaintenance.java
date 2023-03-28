package com.telliant.pageObjects;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;

public class TimeClockMaintenance extends BaseClass {
	
	
	public void employeeCheckIn() throws InterruptedException
	{
		
		clickElement("selectdropemp");
		enterFirstName(ExcelMethods.getData("Employees", "FIRSTNAME", 1));
		Thread.sleep(3000);
		clickElement("selectemp");
		waitForElementClickable("tcaddemployee");
		clickElement("tcaddemployee");
		Thread.sleep(4000);
		clickElement("tcseleemp");		
		clickElement("tcaddemp");
		enterTimeIn(ExcelMethods.getNum("Employees", "TIMEIN", 1));
		enterTimeOut(ExcelMethods.getNum("Employees", "TIMEOUT", 1));
		selectDropdownByIndex("roleselect", 1);
		scrollToElement("tcsave");
		clickElement("tcsave");
		
	}
	
	public void employeeDeleteinTC() throws InterruptedException {
		
		//waitTillElementgetsvisible("tcempdel", 200, 50);
		clickElement("tcempdel");
		clickElement("tcempdelcon");
		
		
	}
	
	
	public void enterFirstName(String firstName) {

		type("inputemp", firstName);
	}
	
	public void enterTimeIn(String timein)
	{
		
		type("timein", timein);
	}
	
	public void enterTimeOut(String timeout)
	{
		
		type("timeout", timeout);
	}
	
	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(4000);
		waitForElementVisible("Admin");
		clickElement("Admin");
	}

	public void navigateTimeClockMaintenance() throws InterruptedException {
		
		waitForElementVisible("timeclockmain");
		clickElement("timeclockmain");
		Thread.sleep(4000);
		
	}
	
	
	
	
	
	
	
	

}
