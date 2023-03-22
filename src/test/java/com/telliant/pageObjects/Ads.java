package com.telliant.pageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class Ads extends WebDriverRoot{
	public void navigateToAds() {
		waitForElementVisible("adstab");
		clickElement("adstab");
	}
	public void  addNewAds() {
		waitForElementClickable("addNewAdsbtn");
		clickElement("addNewAdsbtn");
	}
	public void AdSetupName() {
		Faker faker = new Faker();
		String name = faker.name().name();
		ExcelMethods.putData("Sheet1", "AdsName", 1, name);
		System.out.println(name);
		type("adSetupNametxtbox", name);
	}
	public void LaunchDate() throws ParseException {
		SimpleDateFormat sdf1;
		SimpleDateFormat sdf2;
		LocalDate campaignEndDate = LocalDate.now().plusDays(1);
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		String campaignsEndDate = sdf2.format(sdf1.parse(campaignEndDate.toString()));
		type("launchDate",campaignsEndDate);
	}
	public void ExpiryDate() throws ParseException {
		SimpleDateFormat sdf1;
		SimpleDateFormat sdf2;
		LocalDate campaignEndDate = LocalDate.now().plusDays(5);
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		String campaignsEndDate = sdf2.format(sdf1.parse(campaignEndDate.toString()));
		type("expiryDate",campaignsEndDate);
	}
	public void AdType() {
		waitForElementClickable("adTypedrpdwn");
		clickElement("adTypedrpdwn");
	}
	public void selectAdType() {
		pressKeyDown("adTypedrpdwn");
		pressKeyEnter("adTypedrpdwn");
	}
	public void Description() {
		waitForElementClickable("descriptiontxtbox");
		Faker faker = new Faker();
		String name = faker.random().hex(8);
		type("descriptiontxtbox", name);
	}
	public void UploadFile() {
		//waitForElementClickable("browsefilebox");
		getElement("browsefilebox").sendKeys(System.getProperty("user.dir")+"\\testData\\img1.jpg");
		
	}
	public void searchAds() throws InterruptedException {
		type("searchAdstxtbox",ExcelMethods.getData("Sheet1", "AdsName", 1));
		Thread.sleep(3000);
	}


}
