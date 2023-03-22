package com.telliant.pageObjects;

import com.telliant.core.web.WebDriverRoot;

public class Deals extends WebDriverRoot{
	public void navigateToDeals() {
		waitForElementVisible("dealsTab");
		clickElement("dealsTab");
	}
	public void  addNewDeals() {
		waitForElementClickable("addDealsbtn");
		clickElement("addDealsbtn");
	}
	public void dealsName() {
		waitForElementClickable("dealsNamedrpdwn");
		clickElement("dealsNamedrpdwn");
	}
	public void selectDealsName() {
		pressKeyDown("dealsNamedrpdwn");
		pressKeyEnter("dealsNamedrpdwn");
	}
	public void timeperiod() {
		waitForElementClickable("timePerioddrpdwn");
		clickElement("timePerioddrpdwn");
	}
	public void selectTimePeriod() {
		pressKeyDown("timePerioddrpdwn");
		pressKeyEnter("timePerioddrpdwn");
	}
	public void saveDeals() {
		waitForElementClickable("savebtn");
		clickElement("savebtn");
	}
	public void editDeals() {
		waitForElementClickable("editbtn");
		clickElement("editbtn");
	}
	public void deleteDeals() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementClickable("dealsDeletebtn");
		clickElement("dealsDeletebtn");
	}
	
}
