package com.telliant.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.AddNewClient;
import com.telliant.pageObjects.AddNewEmployee;
import com.telliant.pageObjects.LoginPage;

public class AddClientFunctionality_TC extends BaseClass {
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	AddNewClient addclient = PageFactory.initElements(driver, AddNewClient.class);
	
	int rowNo=1;

	@Test(testName = "ADDCLIENT_001", description = "Verify the functionality of Add Client", priority = 1)

	public void creationofNewClient() throws InterruptedException {
		
		
		//Client creation by filling only mandatory fields
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		addclient.navigateToAdmin();
		addclient.navigateClients();
		addclient.fillClientCreationFormMandatoryFields();
		
	}
	
	@Test(testName = "EDTCLIENT_002", description = "Verify the presence of added client and editing the same", priority = 2)

	public void editClients() throws InterruptedException {
		
		//Editing Client Information
		addclient.editClientFields();
		
		
	
	}
	
	@Test(testName = "EDTDELETE_003", description = "Verify the presence of added client and editing the same", priority = 3)

	public void deleteClients() throws InterruptedException {
		
		//Editing Client Information
		addclient.deleteClientFields();
		
		
	
	}
	
	
	
}