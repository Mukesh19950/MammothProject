package com.telliant.pageObjects;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;

public class AddNewClient extends BaseClass {

	public void fillClientCreationFormMandatoryFields() throws InterruptedException {

		enterFirstname(ExcelMethods.getData("Clients", "FIRSTNAME", 1));
		enterLastName(ExcelMethods.getData("Clients", "LASTNAME", 1));
		enterAddress(ExcelMethods.getData("Clients", "ADDRESS", 1));
		enterEmailid(ExcelMethods.getData("Clients", "EMAIL", 1));
		enterMobile(ExcelMethods.getData("Clients", "PHONE", 1));
		enterZip(ExcelMethods.getNum("Clients", "ZIP", 1));
		selectDropdownByValue("clienttype", ExcelMethods.getNum("Clients", "TYPE", 1));
		selectDropdownByValue("clientlocation", ExcelMethods.getNum("Clients", "LOCATION", 1));
		scrollToElement("addvehicle");
		clickElement("addvehicle");
		enterMake(ExcelMethods.getData("Clients", "MAKE", 1));
		enterModel(ExcelMethods.getData("Clients", "MODEL", 1));
		enterColor(ExcelMethods.getData("Clients", "COLOR", 1));
		clickElement("vehiclesave");
		scrollToElement("clientsave");
		clickElement("clientsave");


	}

	public void enterFirstname(String firstname)
	{

		type("clientfname", firstname);

	}

	public void enterLastName(String lastname)
	{

		type("clientlname", lastname);
	}

	public void enterAddress(String address) {

		type("address", address);
	}

	public void enterEmailid(String email) {

		type("clientemail", email);
	}


	public void enterMobile(String mobile) {

		type("clientphone", mobile);
	}

	public void enterZip(String zip) {

		type("clientzip", zip);
	}


	public void enterMake(String make) throws InterruptedException {

		clickElement("makeinput");
		Thread.sleep(1000);
		type("makeinput", make);
		clickElement("makeselect");
	}

	public void enterModel(String model) throws InterruptedException {

		clickElement("modelinput");
		Thread.sleep(1000);
		type("modelinput", model);
		clickElement("makeselect");
	}

	public void enterColor(String color) throws InterruptedException {

		clickElement("colorinput");
		Thread.sleep(1000);
		type("colorinput", color);
		clickElement("colorselect");
	}

	public void navigateToAdmin() throws InterruptedException {
		Thread.sleep(4000);
		waitForElementVisible("Admin");
		clickElement("Admin");
	}

	public void navigateClients() throws InterruptedException {
		// Thread.sleep(5000);
		waitForElementVisible("client");
		clickElement("client");
		Thread.sleep(4000);
		clickElement("addnewclient");


	}

	public void editClientFields() throws InterruptedException {

		enterSearch(ExcelMethods.getData("Clients", "FIRSTNAME", 1));
		clickElement("clientedit");	
		Thread.sleep(3000);
		scrollToElement("clientemail");
		enterEmailid(ExcelMethods.getData("Clients", "EMAIL", 1));
		clickElement("clientsave");
		getElement("clientsearch").clear();

	}

	public void enterSearch(String search) throws InterruptedException {
		waitForElementClickable("clientsearch");
		type("clientsearch", search);
		Thread.sleep(6000);
	}
	
	public void deleteClientFields() throws InterruptedException {
		enterSearch(ExcelMethods.getData("Clients", "FIRSTNAME", 1));
		clickElement("empdelete");	
		clickElement("deleteconfirm");
		
	}
}
