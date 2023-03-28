package com.telliant.tests;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.telliant.core.web.BaseClass;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.TimeClockMaintenance;
import com.telliant.pageObjects.AddNewEmployee;

@Listeners(com.telliant.core.web.Reporting.class)

public class EmployeeFunctionality_TC extends BaseClass {

	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	AddNewEmployee addemployee = PageFactory.initElements(driver, AddNewEmployee.class);
	TimeClockMaintenance timeclock = PageFactory.initElements(driver, TimeClockMaintenance.class);

	@Test(testName = "ADDEMP_001", description = "Verify the functionality of Add Employee", priority = 1)

	public void creationofNewEmployee() throws InterruptedException, ParseException {

		// Employee creation by filling only mandatory fields

		launchURL(config.getProperty("url"));
		String ValidateUrl = driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"), (config.getProperty("password")));
		loginPage.proceed();
		waitTillElementgetsvisible("Admin", 200, 50);
		addemployee.navigateToAdmin();
		addemployee.navigateEmployees();
		clickElement("AddNewEmp");
		addemployee.fillEmpCreationFormMandatoryFields();
		waitTillElementgetsvisible("searchtxtbox", 200, 50);

	}

	@Test(testName = "EMPCLOCKIN_002", description = "Verify the added employee able to check in", priority = 2)
	public void employeeClockIn() throws InterruptedException {

		timeclock.navigateToAdmin();
		timeclock.navigateTimeClockMaintenance();
		timeclock.employeeCheckIn();

	}

	@Test(testName = "EMPCLOCKINDELETE_002", description = "Verify the added employee able to check in", priority = 3)
	public void employeeClockinDelete() throws InterruptedException {

		timeclock.employeeDeleteinTC();

	}

	@Test(testName = "EDITEMP_004", description = "Verify the presence of added employee and editing the same", priority = 4)

	public void editEmployee() throws InterruptedException {

		// Editing the Employee

		addemployee.navigateToAdmin();
		addemployee.navigateEmployees();
		addemployee.editEmployeeFields();
		waitTillElementgetsvisible("success", 200, 50);
		Assert.assertEquals(getText("success"), "Employee updated successfully !");

	}

	@Test(testName = "DELETEEMP_005", description = "Verify the presence of added location and deleting the same", priority = 5)

	public void deleteEmployee() throws InterruptedException {

		// Deleting the Employee
		addemployee.deleteEmployeeFields();
		waitTillElementgetsvisible("success", 200, 50);
		Assert.assertEquals(getText("success"), "Employee deleted successfully !");
		loginPage.logout();

	}

}
