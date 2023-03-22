package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class Vendor  extends WebDriverRoot {
		public void navigateToVendor() {
			waitForElementVisible("vendorTab");
			clickElement("vendorTab");
		}
		public void addNewVendor() {
			waitForElementClickable("addNewVendorbtn");
			clickElement("addNewVendorbtn");
		}
		public void vendorName() {
			Faker faker = new Faker();
			String name = faker.name().name();
			type("vendorNametxtbox", name);
		}
		public void vendorNumber() {
			Faker faker = new Faker();
			String phonenumber = faker.phoneNumber().cellPhone();
			type("vendorNumbertxtbox", phonenumber);
		}
		public void vendorAccountNumber() {
			Faker faker = new Faker();
			String accountNumber = faker.number().digits(9);
			type("vendorAccNOtxtbox", accountNumber);
		}
		public void vendorEmail() {
			Faker faker = new Faker();
			String emailId = faker.internet().emailAddress();
			ExcelMethods.putData("Sheet1", "VendorEmail", 1, emailId);
			type ("vendorEmailtxtbox", emailId);
		}
		public void cityinput(String cityinput) throws InterruptedException {
			Thread.sleep(3000);
			type("vendorCityInputtxt", cityinput);
		}
		public void stateinput(String stateinput) {
			type("vendorStateInputtxt", stateinput);

		}
		public void vendorPhoneNo() {
			Faker faker = new Faker();
			String phonenumber = faker.phoneNumber().cellPhone();
			type("vendorPhoneNotxt", phonenumber);
		}
		public void searchVendor() {
			type("vendorSearchbox",ExcelMethods.getData("Sheet1", "VendorEmail", 1));
		}

}
