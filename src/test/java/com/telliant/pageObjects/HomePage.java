package com.telliant.pageObjects;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;
import java.util.logging.Level; 
import java.util.logging.Logger;

public class HomePage extends WebDriverRoot {
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 Logger logger = Logger.getLogger(HomePage.class.getSimpleName());
	
	public void navigateToEmpCreation() throws InterruptedException {
		//Thread.sleep(5000);
		clickElement("sideMenuBtn_HP");
		
		try {
			clickElement("employeeHubSideMenuBtn_HP");
		}catch(TimeoutException | ElementNotInteractableException e) {
			getElement("sideMenuBtn_HP").click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			clickElement("employeeHubSideMenuBtn_HP");
		}
		//Thread.sleep(5000);
		clickElement("addEmpBtn_EH");
	}
	
	public void navigateBackToHomepage() {
		
		clickElement("sideMenuBtn_HP");
		clickElement("homePageSideMenu_HP");		
	}
	
	public void navigateToDuetimecard() throws InterruptedException {
		//Thread.sleep(6000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickElement("duetab_HP");
		 // ele3 =getElement("DueWeekSep5-11").getText();	
		//System.out.println(ele3);		
	    clickElement("Due_value_HP");
	}
	
	public void navigateToCusCreation() throws InterruptedException, ElementClickInterceptedException {
		//Thread.sleep(5000);
		try {
		clickElement("sideMenuBtn_HP");
		clickElement("customerHubSideMenuBtn_HP");
		}catch(ElementNotInteractableException| TimeoutException e) {
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			Thread.sleep(1000);
			getElement("sideMenuBtn_HP").click();
			 getElement("customerHubSideMenuBtn_HP").click();
			/*js.executeScript("arguments[0].click();", getElement("sideMenuBtn_HP"));
			js.executeScript("arguments[0].click();", getElement("customerHubSideMenuBtn_HP"));*/
		}finally {
			 getElement("sideMenuBtn_HP").click();
			 getElement("customerHubSideMenuBtn_HP").click();
		}
		//Thread.sleep(5000);
		clickElement("addbtn_CH");
	}
	public void navigateChangePwd() throws InterruptedException {
		 
		waitForElementClickable("Changepwd_icon");
		try {
		clickElement("Changepwd_icon");
		}catch(ElementClickInterceptedException e) {
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			js.executeScript("arguments[0].click();", getElement("Changepwd_icon"));
		}
		}
	public void navigaterejectedtimecard() throws InterruptedException {
		waitForElementVisible("rejectedtab_HP");
		clickElement("rejectedtab_HP");
		waitForElementVisible("rejectedvalue_HP");
		clickElement("rejectedvalue_HP");
		}
	public void TimeCardSucc_Notification() throws InterruptedException {
		waitForElementVisible("notification");
		//clickElement("notification");
		Assert.assertEquals( getText("notification"), "Notification"+ '\n' +"TimeCard Submitted Successfully");
		clickElement("noti_cls");
	}
	
	public void navigateToMytimecardPg() throws InterruptedException {
		
		clickElement("sideMenuBtn_HP");
		
		clickElement("MyTimecardPage_HP");
		
	}
	public void navigateToSubmittedtimecardpage() throws InterruptedException {
		clickElement("submitTab_HP");
		clickElement("submittedvalue_HP");
		clickElement("submittedweek_HP");
		String actualSubmittedWeekrange = getText("submittedweek_HP");
		//System.out.println(actualSubmittedWeekrange);
		logger.log(Level.INFO, actualSubmittedWeekrange); 
		clickElement("submittedHours_HP");
		String actualSubmittedHours = getText("submittedHours_HP");
		//System.out.println(actualSubmittedHours);	
		logger.log(Level.INFO, actualSubmittedHours); 
	}
	
	public void EmpHubnavigation() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			//Thread.sleep(1000);
			getElement("sideMenuBtn_HP").click();
			//Thread.sleep(1000);
			
			try {
				clickElement("employeeHubSideMenuBtn_HP");
			}catch(TimeoutException | ElementNotInteractableException e) {
				getElement("sideMenuBtn_HP").click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				clickElement("employeeHubSideMenuBtn_HP");
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
		}
	
public void navigateToMytimecard(int rowNo) throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		try {
			clickElement("duetab_HP");
		}catch(NoSuchElementException e){
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", getElement("duetab_HP"));
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		ExcelMethods.putData("Sheet2", "Week", rowNo, getElement("FrstDueTimecards_HP").getText());
		System.out.println(getElement("FrstDueTimecards_HP").getText());
		
	   try { clickElement("FrstDueTimecards_HP");
	}catch(NullPointerException e){
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", getElement("FrstDueTimecards_HP"));
	}
	   
	}

public void navigateToRolesAndTask() throws InterruptedException, ElementClickInterceptedException {
	//Thread.sleep(5000);
	try {
	clickElement("sideMenuBtn_HP");
	clickElement("RolesAndTaskSideMenuBtn_HP");
	}catch(ElementNotInteractableException| TimeoutException e) {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		getElement("sideMenuBtn_HP").click();
		 getElement("RolesAndTaskSideMenuBtn_HP").click();
		/*js.executeScript("arguments[0].click();", getElement("sideMenuBtn_HP"));
		js.executeScript("arguments[0].click();", getElement("customerHubSideMenuBtn_HP"));*/
	}finally {
		 getElement("sideMenuBtn_HP").click();
		 getElement("RolesAndTaskSideMenuBtn_HP").click();
	}
	//clickElement("addbtn_CH");
}
	
}
