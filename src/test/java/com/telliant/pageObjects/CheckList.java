package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class CheckList extends WebDriverRoot{
	public void navigateToCheckList() {
		waitForElementVisible("checkListtab");
		clickElement("checkListtab");
	}
	public void addNewItem() {
		waitForElementVisible("addNewItembtn");
		clickElement("addNewItembtn");
	}
	public void checkListName() {
		type("chechListName",ExcelMethods.getData("Sheet1", "CheckListName", 1));
	}
	public void Role() {
		waitForElementClickable("roleDrpdwn");
		clickElement("roleDrpdwn");
	}
	public void selectRole() {
		pressKeyDown("roleDrpdwn");
		pressKeyDown("roleDrpdwn");
		pressKeyEnter("roleDrpdwn");
	}
	public void timeHH() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 10);
		String HH=Integer.toString(number);
		type("checkTimeHH", HH);
	}
	public void timeMM() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 60);
		String MM=Integer.toString(number);
		type("checkTimeMM", MM);
	}
	public void selectPage() {
		clickElement("selectPagedrpdwn");
	}
	public void editCheckList() {
		scrollToElement("editCheckListbtn");
		waitForElementClickable("editCheckListbtn");
		clickElement("editCheckListbtn");
	}
	public void deleteCheckList() {
		scrollToElement("deleteCheckListbtn");
		waitForElementClickable("deleteCheckListbtn");
		clickElement("deleteCheckListbtn");
	}
	
}