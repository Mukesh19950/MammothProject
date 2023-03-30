package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class VehicleSetup extends WebDriverRoot{
	public void navigateToVehicleSetup() {
		scrollToElement("vehicleSetuptab");
		waitForElementVisible("vehicleSetuptab");
		clickElement("vehicleSetuptab");
	}
	public void addNewVehicle() {
		waitForElementVisible("addNewVehiclebtn");
		clickElement("addNewVehiclebtn");
	}
	public void Make() {
		Faker faker = new Faker();
		String name = faker.name().name();
		ExcelMethods.putData("Sheet1", "MakeText", 1, name);
		type("maketxtbox", name);
	}
	public void Model() {
		Faker faker = new Faker();
		String name = faker.name().name();
		type("madeltxtbox", name);
	}
	public void searchVehicle() {
		type("vehicleSearchtxtbox",ExcelMethods.getData("Sheet1", "MakeText", 1));
	}
	public void Search() {
		waitForElementClickable("vehicleSearchbtn");
		clickElement("vehicleSearchbtn");
	}
	public void VehicleType() {
		waitForElementClickable("vehicletypedrpdwn");
		clickElement("vehicletypedrpdwn");
	}
	public void selectVehicleType() {
		pressKeyDown("vehicletypedrpdwn");
		pressKeyDown("vehicletypedrpdwn");
		pressKeyEnter("vehicletypedrpdwn");
	}

}
