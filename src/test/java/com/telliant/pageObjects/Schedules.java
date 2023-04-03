package com.telliant.pageObjects;

import com.telliant.core.web.BaseClass;

public class Schedules extends BaseClass {
	
	
	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(4000);
		waitForElementVisible("Admin");
		clickElement("Admin");
	}

	public void navigateSchedules() throws InterruptedException {
		waitForElementVisible("schedules");
		clickElement("schedules");
		
		
	}
	
	public void action() throws InterruptedException {
		
		waitForElementVisible("rightclick");
		clickElement("rightclick");
		dragAndDrop("schemp", "schedulebay");
		Thread.sleep(6000);
		clickElement("scheclick");
		clickElement("clickendtime");
		clickElement("EndTime");
		clickElement("schesave");
		Thread.sleep(2000);
		clickElement("forecasted");
		waitTillElementgetsvisible("closeforecast", 200, 50);
		clickElement("closeforecast");
		
	}

}
