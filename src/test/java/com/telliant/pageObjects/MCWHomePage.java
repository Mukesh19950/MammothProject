package com.telliant.pageObjects;

import com.telliant.core.web.WebDriverRoot;

public class MCWHomePage extends WebDriverRoot {

	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(3000);
		waitForElementVisible("adminBtn");
		clickElement("adminBtn");
	}

	public void navigateToSystemSetup() throws InterruptedException {
		// Thread.sleep(5000);
		waitForElementVisible("systemSetUp");
		clickElement("systemSetUp");

	}
}
