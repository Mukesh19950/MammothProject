package com.telliant.pageObjects;

import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class MCWSystemsetup extends WebDriverRoot {
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	
	public void AddNewLoc() {
		waitForElementClickable("addNewLoc");
		clickElement("addNewLoc");
	}
	public void locName() {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		ExcelMethods.putData("Sheet1", "Name", 1, name);
		type("LocName", name);
		
	}
	public void address() {
		Faker faker = new Faker();
		String StreetAddress = faker.address().streetAddress();
		type("address", StreetAddress);
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
	public void selectstate() {
		clickElement("selectstate");
	}
	public void citydrpdwn() throws InterruptedException{
		Thread.sleep(3000);
		waitForElementClickable("citydrpdwn");
		clickElement("citydrpdwn");
	}
	public void cityinput(String cityinput) throws InterruptedException {
		Thread.sleep(3000);
		type("cityinput", cityinput);
	}
	public void selectcity() {
		clickElement("selectcity");
	}
	public void savebtn() throws InterruptedException {
		waitForElementClickable("savebtn");
		clickElement("savebtn");
	}

	
	  public void spinner() throws InterruptedException {
	  waitTillElementgetsDisappear("spinner",100,100); }
	 
	public void searchtxtbox() throws InterruptedException{
		type("searchtxtbox",ExcelMethods.getData("Sheet1", "Name", 1));	
	}
	public void searchbtn() throws InterruptedException{
		waitForElementClickable("searchbtn");
		clickElement("searchbtn");
	}
	public void editbtn() {
		waitForElementClickable("editbtn");
		clickElement("editbtn");
	}
	public void deletebtn() {
		waitForElementClickable("deletebtn");
		clickElement("deletebtn");
	}
	public void cfmpopupbtn() {
		waitForElementClickable("cfmpopupbtn");
		clickElement("cfmpopupbtn");
	}
	
}
