package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class TermsAndConditions extends WebDriverRoot{
	public void navigateToTermsAndConditions() {
		waitForElementVisible("termsAndConditionTab");
		clickElement("termsAndConditionTab");
	}
	public void addNewTerms() {
		waitForElementVisible("addNewTermsbtn");
		clickElement("addNewTermsbtn");
	}
	public void ConditionsName() {
		type("conditionNametxtbox",ExcelMethods.getData("Sheet1", "TermsName", 1));
	}
	public void Type() {
		waitForElementClickable("typedrpdwn");
		clickElement("typedrpdwn");
	}
	public void selectType() {
		pressKeyDown("typedrpdwn");
		pressKeyDown("typedrpdwn");
		pressKeyEnter("typedrpdwn");
	}
	public void UploadDoc() throws InterruptedException {
		getElement("uploaddoc").sendKeys(System.getProperty("user.dir")+"\\testData\\Sample_PDF.pdf");
		Thread.sleep(2000);
		
	}
	public void Delete() {
		waitForElementClickable("deleteTermsbtn");
		clickElement("deleteTermsbtn");
	}

}
