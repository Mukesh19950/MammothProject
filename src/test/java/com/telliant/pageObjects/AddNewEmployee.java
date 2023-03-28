package com.telliant.pageObjects;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class AddNewEmployee extends BaseClass {

	public void fillEmpCreationFormMandatoryFields() throws InterruptedException, ParseException {

		enterFirstName(ExcelMethods.getData("Employees", "FIRSTNAME", 1));
		enterLastName(ExcelMethods.getData("Employees", "LASTNAME", 1));
		selectDropdownByVisibleTxt("gender", ExcelMethods.getData("Employees", "GENDER", 1));
		enterAddress(ExcelMethods.getData("Employees", "ADDRESS", 1));

		// Select state

		clickElement("state");
		enterState(ExcelMethods.getData("Employees", "STATE", 1));
		Thread.sleep(3000);
		clickElement("selectstate");

		// Select City
		Thread.sleep(3000);
		clickElement("city");
		enterCity(ExcelMethods.getData("Employees", "CITY", 1));
		clickElement("selectcity");

		enterMobile(ExcelMethods.getData("Employees", "MOBILE", 1));

		selectDropdownByValue("immigration", ExcelMethods.getNum("Employees", "IMMIGRATION", 1));

		enterZip(ExcelMethods.getNum("Employees", "ZIP", 1));

		enterEmailid(ExcelMethods.getData("Employees", "EMAIL", 1));

		DateofHire();

		// Selecting Roles
		scrollToElement("roles");
		clickElement("roles");
		selectRoles(ExcelMethods.getData("Employees", "ROLES", 1));
		clickElement("selectroles");

		// Selecting Location
		clickElement("emplocation");
		selectEmpLocation(ExcelMethods.getData("Employees", "LOCATION", 1));
		clickElement("emplocationselect");

		selectDropdownByValue("ratelocation", ExcelMethods.getNum("Employees", "RATELOCATION", 1));

		enterRate(ExcelMethods.getNum("Employees", "RATEPEROUR", 1));

		addLocation();
		saveEmployee();

	}

	public void enterFirstName(String firstName) {

		type("fname", firstName);
	}

	public void enterLastName(String lastname) {

		type("lname", lastname);
	}

	public void enterEmailid(String emailid)

	{

		type("email", emailid);
	}

	public void enterHireDate(String hiredate) {

		type("dateofhire", hiredate);
	}

	public void enterAddress(String address) {

		type("address", address);
	}

	public void enterState(String state) {

		type("stateinput", state);

	}

	public void enterMobile(String mobile) {
		type("mobile", mobile);

	}

	public void enterZip(String zip) {

		type("zip", zip);
	}

	public void enterCity(String state) {

		type("cityinput", state);

	}

	public void selectRoles(String roles) {

		type("inputroles", roles);

	}

	public void selectEmpLocation(String emplocation) {

		type("emplocationinput", emplocation);
	}

	public void enterRate(String rate) {

		type("rateperhour", rate);
	}

	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(4000);
		waitForElementVisible("Admin");
		clickElement("Admin");
	}

	public void navigateEmployees() throws InterruptedException {
		// Thread.sleep(5000);
		waitForElementVisible("Employees");
		clickElement("Employees");
		Thread.sleep(4000);

	}

	public void addLocation() {

		clickElement("addloc");

	}

	public void saveEmployee() {

		scrollToElement("save");
		clickElement("save");

	}

	public void editEmployeeFields() throws InterruptedException {

		waitTillElementgetsvisible("empsearch", 200, 50);
		enterSearch(ExcelMethods.getData("Employees", "FIRSTNAME", 1));
		Thread.sleep(3000);
		clickElement("empedit");
		Thread.sleep(7000);
		scrollToElement("email");
		enterEmailid(ExcelMethods.getData("Employees", "EMAIL", 1));
		saveEmployee();
		getElement("empsearch").clear();

	}

	public void enterSearch(String search) {
		waitForElementClickable("empsearch");
		type("empsearch", search);
	}

	public void deleteEmployeeFields() throws InterruptedException {
		enterSearch(ExcelMethods.getData("Employees", "FIRSTNAME", 1));
		Thread.sleep(2000);
		clickElement("clientdelete");
		clickElement("deleteconfirm");

	}

	public void DateofHire() throws ParseException {
		SimpleDateFormat sdf1;
		SimpleDateFormat sdf2;
		LocalDate campaignEndDate = LocalDate.now();
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		String campaignsEndDate = sdf2.format(sdf1.parse(campaignEndDate.toString()));
		type("dateofhire", campaignsEndDate);
	}

}
