package com.telliant.pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import java.util.logging.Level; 
import java.util.logging.Logger;
public class ManagerDashboardPages extends BaseClass {
	Logger logger = Logger.getLogger(ManagerDashboardPages.class.getSimpleName());
	
	public void ApproveTimeCard() throws InterruptedException {
		Thread.sleep(5000);
		 clickElement("MyTime_HP");
		 clickElement("TOApprove_MD");
		 Thread.sleep(5000);
		// SelectCard();
		waitForElementVisible("approveicon_MD");
		clickElement("approveicon_MD");		
		waitForElementVisible("notification");
		 // clickElement("notification");
		Assert.assertEquals( getText("notification"), "Notification"+ '\n' +"Timecard Approved successfully");
	}

	public void RejectTimeCard() throws InterruptedException { 
	waitForElementVisible("MyTime_HP");
		clickElement("MyTime_HP");
		clickElement("TOApprove_MD");
		 Thread.sleep(5000);
		clickElement("Search_HP");
		clickElement("SelectTimecard");
		enterEmpid(ExcelMethods.getData("Sheet1", "empCode", 2));
		waitForElementVisible("rejecticon_MD");
		clickElement("rejecticon_MD");
		clickElement("rejection_Notes_MD");
		enterNotes(ExcelMethods.getData("Sheet1", "enternotes", 1));
		clickElement("rejection_Send_MD");
		waitForElementVisible("notification");
		Assert.assertEquals( getText("notification"), "Notification"+ '\n' +"Timecard Rejected successfully");  	
	}
	
	public void enterNotes(String enternotes) {

		type("rejection_Notes_MD", enternotes);
	}
	public void enterEmpid(String empCode) {

		type("Search_HP", empCode);
	}
	
	public void SelectCard() {
		List<WebElement> ToApproveCards =getElements("DueTimecards_HP");
		//System.out.println("no. of rows" + ToApproveCards.size());
		logger.log(Level.INFO, "no. of rows" + ToApproveCards.size()); 

		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : ToApproveCards) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				//System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				logger.log(Level.INFO,"row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				col_num++;
				if (tdElement.getText().equals("Sep 19,2021 - Sep 25,2021")) {
					//System.out.println("test");
					logger.log(Level.INFO,"test");
					tdElement.click();
				}
			}
			row_num++;
		}

	}
	
	public void dueTimesheetRemider() throws InterruptedException {
		
		
		clickElement("MyTime_HP");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		clickElement("Duebtn");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		int Toaprvcnt,mytemrejcnt,aprvdCnt;
		
		try {
			//Fetching pending Approval count from my team screen 
		Toaprvcnt = Integer.parseInt(getText("mytemAprdvcnt").substring(0, 2).trim());
		 //System.out.println("Ref1:"+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
		 logger.log(Level.INFO,"Ref1:"+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
		}catch(NumberFormatException e)
		{
			 Toaprvcnt = Integer.parseInt(getText("mytemAprdvcnt").substring(0, 1).trim());
			// System.out.println("Ref2: "+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
			 logger.log(Level.INFO,"Ref2: "+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
		}
		

		try {
			//Fetching pending Approval count from my team screen 
			aprvdCnt = Integer.parseInt(getText("mytemToaprvcnt").substring(0, 2).trim());
		// System.out.println("Ref1:"+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
		 logger.log(Level.INFO,"Ref1:"+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
		}catch(NumberFormatException e)
		{
			aprvdCnt = Integer.parseInt(getText("mytemToaprvcnt").substring(0, 1).trim());
			 //System.out.println("Ref2: "+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
			logger.log(Level.INFO,"Ref2: "+Toaprvcnt+" & "+getText("mytemToaprvcnt"));
		}
		
		try {
			//Fetching pending rejected count from my team screen
		mytemrejcnt = Integer.parseInt(getText("mytemrejcnt").substring(0, 2).trim());
		System.out.println("Ref3: "+mytemrejcnt+" & "+getText("mytemrejcnt"));
		logger.log(Level.INFO,"Ref3: "+mytemrejcnt+" & "+getText("mytemrejcnt"));
		}catch(NumberFormatException e)
		{
			 mytemrejcnt = Integer.parseInt(getText("mytemrejcnt").substring(0, 1).trim());
			 //System.out.println("Ref4: "+mytemrejcnt+" & "+getText("mytemrejcnt"));
			 logger.log(Level.INFO,"Ref4: "+mytemrejcnt+" & "+getText("mytemrejcnt"));
		}
		
		//by below step summing up we will getting due count & passing into xpath
		 int duecount = Toaprvcnt +aprvdCnt+ mytemrejcnt + 1;
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 getElement("DueChkbx",String.valueOf(duecount)).click();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 clickElement("SndRmdBtnr");
		 
		 //String actualSuccessNotification = getText("notif");
		 //String ExpectedSuccessNotification="Mail have been sent to the employees with pending timesheet.";
		 //Assert.assertEquals(actualSuccessNotification, ExpectedSuccessNotification);
		
		 Assert.assertEquals( getText("notif"), "Mail have been sent to the employees with pending timesheet.");
         duecount = duecount+ 1;
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 getElement("DueChkbx",String.valueOf(duecount)).click();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 clickElement("SndRmdBtnr");
		 
		  //actualSuccessNotification = getText("notif");
		 //ExpectedSuccessNotification="Mail have been sent to the employees with pending timesheet.";
		 //Assert.assertEquals(actualSuccessNotification, ExpectedSuccessNotification);
		 Assert.assertEquals( getText("notif"), "Mail have been sent to the employees with pending timesheet.");	
	
		 }

}