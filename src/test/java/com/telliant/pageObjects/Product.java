package com.telliant.pageObjects;

import com.github.javafaker.Faker;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class Product extends WebDriverRoot {
	public void navigateToProduct() {
		waitForElementVisible("producttab");
		clickElement("producttab");
	}
	public void  addNewProduct() {
		waitForElementClickable("addNewProductbtn");
		clickElement("addNewProductbtn");
	}
	public void productName() {
		Faker faker = new Faker();
		String name = faker.name().name();
		ExcelMethods.putData("Sheet1", "ProductName", 1, name);
		type("productNametxtbox", name);
	}
	public void productType() {
		waitForElementClickable("productTypedrpdwn");
		clickElement("productTypedrpdwn");
	}
	public void selectType() {
		pressKeyDown("productTypedrpdwn");
		pressKeyEnter("productTypedrpdwn");
	}
	public void location() {
		waitForElementClickable("locationdrpdwn");
		clickElement("locationdrpdwn");
	}
	public void selectLocation() {
		waitForElementClickable("locationValue");
		clickElement("locationValue");
	}
	public void productPrice() {
		Faker faker =new Faker();
		int number = faker.number().numberBetween(1, 9);
		String price=Integer.toString(number);
		type("productPricetxtbox", price);
	}
	public void searchProduct() throws InterruptedException {
		type("searchtxtbox",ExcelMethods.getData("Sheet1", "ProductName", 1));
		Thread.sleep(3000);
	}

}
