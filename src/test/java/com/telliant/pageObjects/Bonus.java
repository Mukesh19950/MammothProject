package com.telliant.pageObjects;

import com.telliant.core.web.WebDriverRoot;

public class Bonus extends WebDriverRoot{
	public void navigateToBonus() {
		waitForElementVisible("bonustab");
		clickElement("bonustab");
	}

}
