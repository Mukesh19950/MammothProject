package com.telliant.pageObjects;

import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class EmployeeHandBook extends WebDriverRoot{
	public void navigateToEmpHandBook() {
		waitForElementVisible("empHandBookTab");
		clickElement("empHandBookTab");
	}
	public void addNewItem() {
		waitForElementVisible("addNewbtn");
		clickElement("addNewbtn");
	}
	public void HandBookName() {
		type("handBookNametxtbox",ExcelMethods.getData("Sheet1", "HandBookName", 1));
	}
	public void Role() {
		waitForElementClickable("empRoledrpdwn");
		clickElement("empRoledrpdwn");
	}
	public void selectRole() {
		pressKeyDown("empRoledrpdwn");
		pressKeyDown("empRoledrpdwn");
		pressKeyEnter("empRoledrpdwn");
	}
	public void UploadDoc() throws InterruptedException {
		getElement("empdocumentfield").sendKeys(System.getProperty("user.dir")+"\\testData\\Sample_PDF.pdf");
		Thread.sleep(2000);
		
	}
	public void editEmpHandbook() {
		waitForElementClickable("empHandbookeditbtn");
		clickElement("empHandbookeditbtn");
	}
	public void deleteEmpHandbook() {
		waitForElementClickable("empHandbookdeletebtn");
		clickElement("empHandbookdeletebtn");
	}

}
