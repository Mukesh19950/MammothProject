package com.telliant.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.Product;
import com.telliant.pageObjects.Service;
import com.telliant.pageObjects.Systemsetup;

@Listeners(com.telliant.core.web.Reporting.class)

public class Product_Functionality_TC extends BaseClass implements ITestListener{
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	HomePage MCWHomePage = PageFactory.initElements(driver, HomePage.class);
	Systemsetup MCWSystemsetup= PageFactory.initElements(driver, Systemsetup.class);
	Service MCWService = PageFactory.initElements(driver, Service.class);
	Product MCWProduct = PageFactory.initElements(driver, Product.class);
	//Admin Login
	@Test(testName = "007", description = "Verify the add product feature via admin",priority = 1)
	public void AddProduct() throws IOException, InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		MCWHomePage.navigateToAdmin();
		MCWHomePage.navigateToSystemSetup();
		MCWProduct.navigateToProduct();
		MCWProduct.addNewProduct();
		MCWProduct.productName();
		MCWProduct.productType();
		MCWProduct.selectType();
		MCWProduct.location();
		MCWProduct.selectLocation();
		MCWProduct.productPrice();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"),"Product added successfully !");

	}
	@Test(testName = "008", description = "Verify the presence of added product and editing the same",priority = 2)
	public void SearchProduct() throws IOException, InterruptedException {
		MCWProduct.searchProduct();
		MCWSystemsetup.editbtn();
		MCWProduct.productName();
		MCWProduct.productType();
		pressKeyDown("productTypedrpdwn");
		MCWProduct.selectType();
		MCWProduct.productPrice();
		MCWSystemsetup.savebtn();
		waitTillElementgetsvisible("successmsg", 200, 50);
		Assert.assertEquals( getText("successmsg"),"Product updated successfully !");

	}
	@Test(testName = "009", description = "Verify the presence of added product and deleting the same",priority = 3)
	public void DeleteProduct() throws IOException, InterruptedException {
		MCWProduct.searchProduct();
		MCWSystemsetup.deletebtn();
		MCWSystemsetup.cfmpopupbtn();
		Assert.assertEquals( getText("successmsg"),"Product deleted successfully !");
		loginPage.logout();
	}
}