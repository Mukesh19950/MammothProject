package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.WebDriverRoot;

public class MCWSystemsetup extends WebDriverRoot {
	
	public void AddNewLoc() {
		waitForElementClickable("addNewLoc");
		clickElement("addNewLoc");
	}
	public void locName() {
		Faker faker = new Faker();
		String name = faker.name().fullName();

		type("LocName", name);
	}
	public void address() {
		Faker faker = new Faker();
		String Address = faker.address().fullAddress();

		type("address", Address);
	}
	public void zipcode(String zipcode) {

		type("zipcode", zipcode);
	}
	public void state() {
		waitForElementClickable("statedrpdwn");
		clickElement("statedrpdwn");
	}
	public void stateinput(String stateinput) {
		type("stateinput", stateinput);
	}
	public void citydrpdwn() {
		waitForElementClickable("citydrpdwn");
		clickElement("citydrpdwn");
	}
	public void cityinput(String cityinput) {
		type("cityinput", cityinput);
	}
	public void savebtn() {
		waitForElementClickable("savebtn");
		clickElement("savebtn");
	}

}
