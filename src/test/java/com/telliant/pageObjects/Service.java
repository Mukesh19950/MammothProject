package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class Service extends WebDriverRoot {
	public void navigateToService() {
		waitForElementVisible("servicetab");
		clickElement("servicetab");
	}
	public void addNewService() {
		waitForElementClickable("addservicebtn");
		clickElement("addservicebtn");
	}
	public void serviceName() {	
		Faker faker = new Faker();
		String name = faker.name().name();
		ExcelMethods.putData("Sheet1", "ServiceName", 1, name);
		type("serviceNametxtbox", name);
	}
	public void serviceType() {
		waitForElementClickable("serviceTypedrpdwn");
		clickElement("serviceTypedrpdwn");
	}
	public void selectType() throws InterruptedException {
		Thread.sleep(2000);
		pressKeyDown("serviceTypedrpdwn");
		pressKeyEnter("serviceTypedrpdwn");
	}
	public void servicePrice() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 9);
		String price=Integer.toString(number);
		type("servicePricetxtbox", price);
	}
	public void searchService() {
		type("searchtxtbox",ExcelMethods.getData("Sheet1", "ServiceName", 1));
	}

}
