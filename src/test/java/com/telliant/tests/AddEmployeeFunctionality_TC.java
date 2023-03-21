package com.telliant.tests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.AddNewEmployee;

public class AddEmployeeFunctionality_TC extends BaseClass {
	
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	AddNewEmployee addemployee = PageFactory.initElements(driver, AddNewEmployee.class);
	
	int rowNo=1;

	@Test(testName = "ADDEMP_001", description = "Verify the functionality of Add Employee", priority = 1)

	public void creationofNewEmployee() throws InterruptedException {
		
		//Employee creation by filling only mandatory fields
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		loginPage.proceed();
		addemployee.navigateToAdmin();
		addemployee.navigateEmployees();
		addemployee.fillEmpCreationFormMandatoryFields();
		waitForElementVisible("searchtxtbox");
		
	}
	
	
	@Test(testName = "EDTEMP_002", description = "Verify the presence of added employee and editing the same", priority = 2)

	public void editEmployee() throws InterruptedException {
		
		//Editing the Employee
		addemployee.editEmployeeFields();
		
	
	}
	
	@Test(testName = "DELEMP_003", description = "Verify the presence of added location and deleting the same", priority = 3)

	public void deleteEmployee() throws InterruptedException {
		
		//Deleting the Employee
		addemployee.deleteEmployeeFields();
		loginPage.logout();
		
		
	}

	

}
