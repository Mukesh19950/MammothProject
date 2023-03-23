package com.telliant.pageObjects;

import org.openqa.selenium.JavascriptExecutor;

import com.telliant.core.web.WebDriverRoot;

public class HomePage extends WebDriverRoot {
	
	
	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(3000);
		waitTillElementgetsvisible("adminBtn", 200,100);
		clickElement("adminBtn");
		}
	public void navigateToSystemSetup() throws InterruptedException {
		//Thread.sleep(5000);
		waitForElementVisible("systemSetUp");
		clickElement("systemSetUp");
		}
}
