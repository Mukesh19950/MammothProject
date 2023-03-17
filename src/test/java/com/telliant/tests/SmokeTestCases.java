package com.telliant.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import com.telliant.pageObjects.ChangePassword;
import com.telliant.pageObjects.CustomerCreation;
import com.telliant.pageObjects.EmpCreationPage;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.LoginPage;
import com.telliant.pageObjects.ManagerDashboardPages;
import com.telliant.pageObjects.MyTimeCardPage;
import com.telliant.pageObjects.RoleAndTaskPage;

@Listeners(com.telliant.tests.SmokeTestCases.class)

public class SmokeTestCases extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	EmpCreationPage empCreationPage = PageFactory.initElements(driver, EmpCreationPage.class);
	MyTimeCardPage myTimeCardPage = PageFactory.initElements(driver, MyTimeCardPage.class);
	ManagerDashboardPages managerDashbaordPages =PageFactory.initElements(driver, ManagerDashboardPages.class);
	CustomerCreation cusCreationPage = PageFactory.initElements(driver, CustomerCreation.class);
	RoleAndTaskPage rolAndTaskpg = PageFactory.initElements(driver, RoleAndTaskPage.class);
	ChangePassword Changepwd = PageFactory.initElements(driver, ChangePassword.class);
	int rowNo;
	
	//Admin Login
	@Test(testName = "001", description = "Employee creation with ProjectAssignment",priority = 1)
	public void TC01_EmployeeCreation() throws IOException, InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(config.getProperty("username"),(config.getProperty("password")));
		homePage.navigateToEmpCreation();
		//Employee creation by filling only mandatory fields
		empCreationPage.fillEmpCreationFormMandatoryFields();
		//To update User name and password in Excel for login Purpose
		empCreationPage.updateUserNamePwdInXL();
		//Update Email id and Emp id for next employee creation.
		empCreationPage.updateEmpmailIdInExcel();
		empCreationPage.updateEmpIdInExcel();
		loginPage.logout();
	}
	
	//User Login
	@Test(testName = "002", description = "Submit New Timecard",priority = 2)
 	public void TC02_submitNewTimecard() throws InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 6), ExcelMethods.getData("Sheet1", "Password", 6));
		homePage.navigateToMytimecardPg();
		myTimeCardPage.AddNewTimecard();
		//Enter time hours in Timecard
		myTimeCardPage.EnterTime1();
		//Enter leave hours and notes in Timecard
		myTimeCardPage.AddLeave();
		myTimeCardPage.enterLeave_1();
		myTimeCardPage.submit_TimeCard();
		//myTimeCardPage.savetimecard();
		homePage.TimeCardSucc_Notification();
		//Change Password Flow for new employee
		homePage.navigateChangePwd();
		Changepwd.fillChangePwdFields();
		//Update/Swap the old password to new password in excel
		Changepwd.updateNewPwdinXL();
		loginPage.logout();

	}
	
	//Managers Login
	@Test(testName="003",description="Reject Timecard",dependsOnMethods = {"TC02_submitNewTimecard()"},priority = 3)
	public void TC03_RejectTimeCard() throws InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 2), ExcelMethods.getData("Sheet1", "Password", 2));
		
		managerDashbaordPages.RejectTimeCard();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		managerDashbaordPages.dueTimesheetRemider();
		loginPage.logout();
		//Need to add Due Reminder Flow
	
	}
	
	//User Login
	@Test(testName = "004", description = "Submit Due ,Rejected and FutureWeek Timecard",priority = 4)
 	public void TC04_SubmitTimecard() throws InterruptedException {

		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 6), ExcelMethods.getData("Sheet1", "Password", 6));
		
	//To Submit Due Timecard
		homePage.navigateToDuetimecard();
		myTimeCardPage.AddNewTimecard();
		myTimeCardPage.entertime();
		myTimeCardPage.submit_TimeCard();
		homePage.TimeCardSucc_Notification();
	//To Submit Rejected Timecard
		homePage.navigaterejectedtimecard();
		myTimeCardPage.rejectionnotes();
		myTimeCardPage.editTimecard();
		homePage.TimeCardSucc_Notification();
	//To Submit the Future Week Time Card	
		homePage.navigateToMytimecardPg();
		myTimeCardPage.AddfutureTimecard();
		myTimeCardPage.AddNewTimecard();
		myTimeCardPage.EnterTime1();
		myTimeCardPage.AddLeave();
		myTimeCardPage.enterLeave_1();
		myTimeCardPage.submit_TimeCard();
	//myTimeCardPage.savetimecard();
		homePage.TimeCardSucc_Notification();
		loginPage.logout();

	}
	//Manager User
	@Test(testName="005",description="Approve Timecard",priority = 3)
	public void Tc05_ApproveTimeCard() throws InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 2), ExcelMethods.getData("Sheet1", "Password", 2));
		
		managerDashbaordPages.ApproveTimeCard();
		loginPage.logout();
	}
		//Role-Admin
	@Test(testName = "007", description = "Customer Creation",priority = 4)
	public void TC04_CustomerCreation() throws IOException, InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 1), ExcelMethods.getData("Sheet1", "Password", 1));
		homePage.navigateToCusCreation();
		cusCreationPage.fillCusCreationFormMandatoryFields();
		cusCreationPage.updateCustomerName();
		loginPage.logout();
	}	
	//Managers Login
	@Test(testName="009",description="Employee should able to View employee Details",priority = 1)
	public void TC09_EmpHubPage() throws InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.navigate().refresh();
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 2), ExcelMethods.getData("Sheet1", "Password", 2));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(450);
		homePage.EmpHubnavigation();
		empCreationPage.EmpHubPageVerification();
		
		}
	
	//Admin Login
	@Test(testName="12",description="Employee should able to View employee Details",priority = 2)
	public void TC12_EditEmpFunct() throws InterruptedException {
		rowNo=1;
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.navigate().refresh();
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//Fetching Admin User Login credentils  from Sheet4 & rowNo=4
	    loginPage.login(ExcelMethods.getData("Sheet1", "UserName", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage.EmpHubnavigation();
		
		rowNo=7;// Fetching test data  from Sheet1 & rowNo 9 
		empCreationPage.editEmpInfoAndVerify(rowNo);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 15);
	 	wait.until(ExpectedConditions.elementToBeClickable(getElement("username")));
	 	
	 	rowNo=7;// Fetching newly created User Credentials from Sheet2 row no 7
	 	driver.navigate().refresh();
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "emailID", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
		
		rowNo=6;//Fetching Time Sheet info from Sheet2 & rowNo  7
		homePage.navigateToMytimecard(rowNo);
		myTimeCardPage.enterTimecard();
		
		myTimeCardPage.entertime("Sheet2",rowNo);
		myTimeCardPage.MaxHrsVerf();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		rowNo++; //Fetching Time Sheet info for Notes from Sheet2 & rowNo  7
		myTimeCardPage.enterTimecard();
		myTimeCardPage.entertime("Sheet2",rowNo);
		myTimeCardPage.notesVerf();	
		rowNo=8; //Fetching Time Sheet info for Notes from Sheet2 & rowNo  8
		myTimeCardPage.enterTimecard();
		//myTimeCardPage.entertimeWthNots("Sheet2",rowNo);
		myTimeCardPage.minTimeVerf("Sheet2",rowNo);
	}
	
	
	//User Login
	@Test(testName = "013", description = "Add Duplicate task",priority = 12)
 	public void TC13_Addduplicatetask() throws InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 5), ExcelMethods.getData("Sheet1", "Password", 5));
		homePage.navigateToMytimecardPg();
		myTimeCardPage.AddNewTimecard();
		myTimeCardPage.Addduplicatetask();
		
	}
	//User Login
	@Test(testName = "014", description = "Maximum hours check",priority = 14)
 	public void TC14_Maximumhourscheck() throws InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 5), ExcelMethods.getData("Sheet1", "Password", 5));
		homePage.navigateToMytimecardPg();
		myTimeCardPage.AddfutureTimecard();
		myTimeCardPage.AddNewTimecard();
		myTimeCardPage.EnterTime1();
		myTimeCardPage.maximumhoursnoti();
		
	}
	//User Login
	@Test(testName = "015", description = "Submitted Timecard",priority = 15)
 	public void TC15_submittedtimecard() throws InterruptedException {
		
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 4), ExcelMethods.getData("Sheet1", "Password", 4));
		homePage.navigateToSubmittedtimecardpage();
		loginPage.logout();
		
		
	}
	
	//Admin Login
 	@Test(testName = "016", description = "Admin should be able to Edit Customer Functionality & Verify ",priority = 16)
 	 public void TC16_EdtCustFun() throws InterruptedException {
 		 
 		rowNo=1;
 		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.navigate().refresh();
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//Fetching Admin User Login credentils  from Sheet1 & rowNo=1
	    loginPage.login(ExcelMethods.getData("Sheet1", "UserName", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		//Thread.sleep(5000);
		homePage.navigateToCusCreation();
		//Thread.sleep(5000);
		//Fetching Customer Information  from Sheet3 & rowNo=1
		cusCreationPage.srcCusAndSel(ExcelMethods.getData("Sheet3", "custName", rowNo));
		cusCreationPage.editCusInfo(rowNo);
		cusCreationPage.srcCusAndSel(ExcelMethods.getData("Sheet3", "custName", rowNo));
		cusCreationPage.verfEditCusInfo(rowNo);
 		 
 	 	}
 	//Admin Login
 	@Test(testName = "017", description = "Admin should be able to Create Role under Role&Task hub & Verify ",priority = 17)
 	public void TC17_CretRoleHub() throws InterruptedException {
 		rowNo=1;
 		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.navigate().refresh();
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//Fetching Admin User Login credentils  from Sheet1 & rowNo=1
	    loginPage.login(ExcelMethods.getData("Sheet1", "UserName", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		homePage.navigateToRolesAndTask();;
		//rolAndTaskpg.srcRolAndSel(RolName);
		//Fetching Admin User Login credentils  from Sheet3 & rowNo=1
		rolAndTaskpg.AddRolAndVerf();
		
 	}
 	//Admin Login
 	@Test(testName = "018", description = "Admin should be able to Edit Role under Role&Task hub & Verify ",priority = 18)
	 public void TC18_EdtRoleHub() throws InterruptedException {
		rowNo=1;
	
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.navigate().refresh();
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//Fetching Admin User Login credentils  from Sheet1 & rowNo=1
	    loginPage.login(ExcelMethods.getData("Sheet1", "UserName", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		homePage.navigateToRolesAndTask();
		//rolAndTaskpg.srcRolAndSel(RolName);
		//Fetching Admin User Login credentils  from Sheet3 & rowNo=1
		rolAndTaskpg.EdtRolAndVerf();
		
	}
 	//Admin Login
 	@Test(testName = "019", description = "Admin should be able to Create Task under Role&Task hub & Verify ",priority = 19)
	 public void TC19_CretTskHub() throws InterruptedException {
		rowNo=1;
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.navigate().refresh();
		//Fetching Admin User Login credentils  from Sheet1 & rowNo=1
	    loginPage.login(ExcelMethods.getData("Sheet1", "UserName", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		homePage.navigateToRolesAndTask();;
		//rolAndTaskpg.srcRolAndSel(RolName);
		//Fetching Admin User Login credentils  from Sheet3 & rowNo=2
		rolAndTaskpg.addTskAndVerf();
		
	}
 	//Admin Login
 	@Test(testName = "020", description = "Admin should be able to Edit Task under Role&Task hub & Verify ",priority = 20)
	 public void TC20_EdtTskHub() throws InterruptedException {
		rowNo=1;
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.navigate().refresh();
		//Fetching Admin User Login credentils  from Sheet1 & rowNo=1
	    loginPage.login(ExcelMethods.getData("Sheet1", "UserName", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		homePage.navigateToRolesAndTask();;
		//rolAndTaskpg.srcRolAndSel(RolName);
		//Fetching Admin User Login credentils  from Sheet3 & rowNo=2
		rolAndTaskpg.edtTskAndVerf();
		
	}
	 
 

 	//Admin Login
	//@Test(testName = "021", description = "Admin should be able to Edit Task under Role&Task hub & Verify ",priority = 20)
       public void TC21_exptEmpVerf() throws InterruptedException {
			rowNo=1;
			launchURL(config.getProperty("url"));
			String ValidateUrl=driver.getCurrentUrl();
			ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
			driver.navigate().refresh();
			//Fetching Admin User Login credentils  from Sheet1 & rowNo=1
		    loginPage.login(ExcelMethods.getData("Sheet1", "UserName", rowNo), ExcelMethods.getData("Sheet1", "Password", rowNo));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			homePage.EmpHubnavigation();
		
			empCreationPage.ExptAndVerfEmplist();
			
			}

}