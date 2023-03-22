package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class Membership extends WebDriverRoot{
	public void navigateToMembership() {
		waitForElementVisible("membershiptab");
		clickElement("membershiptab");
	}
	public void  addNewMembership() {
		waitForElementClickable("addNewMembershipbtn");
		clickElement("addNewMembershipbtn");
	}
	public void membershipName() {
		Faker faker = new Faker();
		String name = faker.name().name();
		ExcelMethods.putData("Sheet1", "MembershipName", 1, name);
		System.out.println(name);
		type("membershipNametxtbox", name);
	}
	public void washes() {
		waitForElementClickable("washesdrpdwn");
		clickElement("washesdrpdwn");
	}
	public void selectwashes() {
		pressKeyDown("washesdrpdwn");
		pressKeyEnter("washesdrpdwn");
	}
	public void searchMembership() {
		type("membershipSearchtxtbox",ExcelMethods.getData("Sheet1", "MembershipName", 1));
	}

}
