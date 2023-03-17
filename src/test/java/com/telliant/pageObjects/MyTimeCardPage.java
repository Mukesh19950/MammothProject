package com.telliant.pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import java.util.logging.Level; 
import java.util.logging.Logger; 
public class MyTimeCardPage extends BaseClass {
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 Logger logger = Logger.getLogger(MyTimeCardPage.class.getSimpleName());
	 
	public void enterTimecard() throws InterruptedException {
		/*
		 * clickElement("removetimecardrow_MT"); clickElement("confirmationPopUpYes");
		 */
		Thread.sleep(5000);
		remove_timecardrow();
		Thread.sleep(5000);
		clickElement("AddProject");
		clickElement("addProject_MT");
		try {
		clickElement("selectProjectDropdwon");
		}catch(ElementClickInterceptedException e){
			clickElement("PlsSltTaskBtn");
			clickElement("selectProjectDropdwon");
			
		}
		try {
		clickElement("selctProjectdocsnap");
		}catch(ElementNotInteractableException e){
			js.executeScript("arguments[0].click();", getElement("selctProjectdocsnap"));
		}
		clickElement("selectTaskdropdown");
		clickElement("selectTaskvalueQATESTING");		
	}
	
	public void SelectTimecard(String project,String task) throws InterruptedException {
		/*
		 * clickElement("removetimecardrow_MT"); clickElement("confirmationPopUpYes");
		 */
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		remove_timecardrow();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		clickElement("AddProject");
		clickElement("addProject_MT");
		clickElement("selectProjectDropdwon");
		clickElement("selctProjectdocsnap");
		clickElement("selectTaskdropdown");
		clickElement("selectTaskvalueQATESTING");		
	}
		public void entertime() throws InterruptedException {
		clickElement("monCell_AMT");
		enterMonTime(ExcelMethods.getData("Sheet2", "Mon", 1));
		clickElement("tueCell_AMT");
		enterTueTime(ExcelMethods.getData("Sheet2", "Tue", 1));
		clickElement("wedCell_AMT");
		enterWedTime(ExcelMethods.getData("Sheet2", "Wed", 1));
		clickElement("thrCell_AMT");
		enterThrTime(ExcelMethods.getData("Sheet2", "Thru", 1));
		clickElement("friCell_AMT");
		enterFriTime(ExcelMethods.getData("Sheet2", "Fri", 1));
		clickElement("satCell_AMT");

	}
//		
//		public void enterNotes() throws InterruptedException {
//			clickElement("monCell_MT");
//			clickElement("notes");
//			//enterMonNotes(ExcelMethods.getData("Sheet2", "Mon", 1));
//		}
//	
		public void enterleave() {
		
			clickElement("AddProject");
     		clickElement("selectProjectDropdown1");
     		clickElement("selectProjectTelliantAdmin");
			clickElement("selectTaskdropdown1");
			clickElement("selectTaskDropdownLeave");
			clickElement("thruCell_MT");
			type("thruCell_MT","8");
	
		}
		
		public void savetimecard() throws InterruptedException {
			clickElement("Save_MT");
			//clickElement("confirmationPopUpNo");
			clickElement("confirmationPopUpYes");
			//clickElement("notification");
			Assert.assertEquals( getText("notification"),"TimeCard Saved Successfully");
	
		}

		public void submit_TimeCard() throws InterruptedException {
			System.out.println("entered timecard");
			clickElement("Submit_MT");
			driver.getWindowHandle();
			// clickElement("confirmationPopUpNo");
			clickElement("confirmationPopUpYes");
			
		}

		public void Submittab() {
			clickElement("submitTab_HP");
			String ele4 = getText("SubmittedWeek");
			System.out.println(ele4);

			if (ele4.equalsIgnoreCase(ele3)) {

				//System.out.println("Timecard Submitted successfully");
				logger.log(Level.INFO, "Timecard Submitted successfully");

			} else {
				//System.out.println("Timecard not submitted");
				logger.log(Level.INFO, "Timecard not submitted");
			}

		}
		
		public static void remove_timecardrow() {
			List<WebElement>deleteTimecardRow = driver.findElements(By.xpath("//span[@class='mdi mdi-close-circle align-left font-size-1-2 theme-primary-icon-color']"));
			for(int i=0;i<deleteTimecardRow.size();i++){		
				
			clickElement("removetimecardrow_MT");
			
			clickElement("confirmationPopUpYes");
			}
		}
		
public void enterMonTime(String Mon) throws InterruptedException {
	//Thread.sleep(5000);
		type("monCell_AMT", Mon);
	}
public void enterTueTime(String Tue) throws InterruptedException {
	//Thread.sleep(5000);
		type("tueCell_AMT", Tue);
}
public void enterWedTime(String Wed) throws InterruptedException {
	//Thread.sleep(5000);
		type("wedCell_AMT", Wed);
}
public void enterThrTime(String Thru) throws InterruptedException {
	//Thread.sleep(5000);
		type("thrCell_AMT", Thru);
}
public void enterFriTime(String Fri) throws InterruptedException {
	//Thread.sleep(5000);
		type("friCell_AMT", Fri);
}

public void editTimecard() throws InterruptedException {
	//Thread.sleep(5000);
	clickElement("thrCell_MT");
	enterThrTime(ExcelMethods.getData("Sheet2", "Thru", 2));
	clickElement("satCell_MT");
	clickElement("Submit_MT");
	driver.getWindowHandle();
	clickElement("confirmationPopUpYes");
	}

public void AddNewTimecard() throws InterruptedException {
	Thread.sleep(5000);
	remove_timecardrow();
	//Thread.sleep(5000);
	//remove_timecardrow();
	clickElement("PjtQwiktime_AMT");
	clickElement("selecttask_AMT");
	clickElement("selectTaskvalueQATESTING_AMT");
	
	}

public void AddLeave() throws InterruptedException {
	//Thread.sleep(5000);
	clickElement("PjtLeave_AMT");
	clickElement("selecttask_AMT");
	clickElement("selectTaskvalueSick/casual_AMT");
	//clickElement("Submitbtn_AMT");	
	}

public void EnterTime1() throws InterruptedException {
	clickElement("monCell_AMT");
	enterMonTime1(ExcelMethods.getData("Sheet2", "Mon", 1));
	clickElement("tueCell_AMT");
	enterTueTime1(ExcelMethods.getData("Sheet2", "Tue", 1));
	clickElement("wedCell_AMT");
	enterWedTime1(ExcelMethods.getData("Sheet2", "Wed", 1));
	//clickElement("thrCell_AMT");
	//enterThrTime(ExcelMethods.getData("Sheet2", "Thru", 1));
	clickElement("friCell_AMT");
	enterFriTime1(ExcelMethods.getData("Sheet2", "Fri", 1));
	clickElement("satCell_AMT");

}
public void enterLeave_1() throws InterruptedException {
	clickElement("thursleave_AMT");
	enterThrTime2(ExcelMethods.getData("Sheet2", "Thru", 1));
	clickElement("thursleave_AMT");
	clickElement("Notes_AMT");
	enterthursnotes(ExcelMethods.getData("Sheet2", "Thru", 5));
	clickElement("satCell_AMT");

}
public void enterMonTime1(String Mon) throws InterruptedException {
	//Thread.sleep(5000);
		type("monCell_AMT", Mon);
	}
public void enterTueTime1(String Tue) throws InterruptedException {
	//Thread.sleep(5000);
		type("tueCell_AMT", Tue);
}
public void enterWedTime1(String Wed) throws InterruptedException {
	//Thread.sleep(5000);
		type("wedCell_AMT", Wed);
}
public void enterThrTime1(String Thru) throws InterruptedException {
	//Thread.sleep(5000);
		type("thrCell_AMT", Thru);
}
public void enterFriTime1(String Fri) throws InterruptedException {
	//Thread.sleep(5000);
		type("friCell_AMT", Fri);
}
//public void enterMonNotes(String Mon) throws InterruptedException {
//	Thread.sleep(5000);
//		type("notes", Mon);
//	}
public void enterThrTime2(String Thru) throws InterruptedException {
	//Thread.sleep(5000);
		type("thursleave_AMT", Thru);
}
public void enterthursnotes(String Thru) throws InterruptedException {
	//Thread.sleep(5000);
		type("Notes_AMT", Thru);
}
public void rejectionnotes() throws InterruptedException {
	Thread.sleep(5000);
	clickElement("Rejection_notesicon_MT");
	clickElement("Rejection_notes_MT");
	String actualSuccessNotification = getText("Rejection_notes_MT");
	//System.out.println("Rejection Notes :  "+actualSuccessNotification);
	logger.log(Level.INFO, "Rejection Notes :  "+actualSuccessNotification);
	}
public void AddfutureTimecard() throws InterruptedException {
	//Thread.sleep(5000);
	
	clickElement("NavigationBtn_MT");
}
public void Addduplicatetask() throws InterruptedException {
	//Thread.sleep(5000);
	clickElement("PjtQwiktime_AMT");
	clickElement("selecttask_AMT");
	clickElement("selectTaskvalueQATESTING2_AMT");
	clickElement("notification");
	Assert.assertEquals( getText("notification"), "Warning"+ '\n' +"Task already exists for the project Telliant_QwikTime in current week");

	}
public void maximumhoursnoti() throws InterruptedException {
	clickElement("notification");
	Assert.assertEquals( getText("notification"), "Warning"+'\n'+"You have reached the maximum hours for the week");

	}
public void futureTimecardpjt() throws InterruptedException {
	
}

public void entertime(String Sheetnam,int rowNo) throws InterruptedException {
	clickElement("monCell_MT");
	enterMonTime(ExcelMethods.getData(Sheetnam, "Mon", rowNo));
	clickElement("tueCell_MT");
	enterTueTime(ExcelMethods.getData(Sheetnam, "Tue", rowNo));
	clickElement("wedCell_MT");
	enterWedTime(ExcelMethods.getData(Sheetnam, "Wed", rowNo));
	clickElement("thrCell_MT");
	enterThrTime(ExcelMethods.getData(Sheetnam, "Thru", rowNo));
	clickElement("friCell_MT");
	enterFriTime(ExcelMethods.getData(Sheetnam, "Fri", rowNo));
	clickElement("satCell_MT");

}

public void entertimeWthNots(String Sheetnam,int rowNo) throws InterruptedException {
	clickElement("monCell_MT");
	enterMonTime(ExcelMethods.getData(Sheetnam, "Mon", rowNo));
	try {
	type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Mon", rowNo)+"Hrs");
	}catch(NoSuchElementException | NullPointerException e){
		getElement("Notes_AMT").sendKeys("For Mon Worked For"+ExcelMethods.getData("Sheet2", "Mon", rowNo)+"Hrs");
	}
	clickElement("SavNots_Btn");
	
	clickElement("tueCell_MT");
	enterTueTime(ExcelMethods.getData(Sheetnam, "Tue", rowNo));
	//getElement("Notes_AMT").sendKeys("Notes_AMT","For Tue Worked For"+ExcelMethods.getData("Sheet2", "Tue", rowNo)+"Hrs");
	try {
		type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Tue", rowNo)+"Hrs");
		}catch(NoSuchElementException | NullPointerException e){
			getElement("Notes_AMT").sendKeys("For Mon Worked For"+ExcelMethods.getData("Sheet2", "Tue", rowNo)+"Hrs");
		}
	clickElement("SavNots_Btn");
	
	clickElement("wedCell_MT");
	enterWedTime(ExcelMethods.getData(Sheetnam, "Wed", rowNo));
	//getElement("Notes_AMT").sendKeys("Notes_AMT","For Wed Worked For"+ExcelMethods.getData("Sheet2", "Wed", rowNo)+"Hrs");
	try {
		type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Wed", rowNo)+"Hrs");
		}catch(NoSuchElementException | NullPointerException e){
			getElement("Notes_AMT").sendKeys("For Mon Worked For"+ExcelMethods.getData("Sheet2", "Wed", rowNo)+"Hrs");
		}
	clickElement("SavNots_Btn");
	
	clickElement("thrCell_MT");
	enterThrTime(ExcelMethods.getData(Sheetnam, "Thru", rowNo));
	//getElement("Notes_AMT").sendKeys("Notes_AMT","For Thru Worked For"+ExcelMethods.getData("Sheet2", "Thru", rowNo)+"Hrs");
	try {
		type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Thru", rowNo)+"Hrs");
		}catch(NoSuchElementException | NullPointerException e){
			getElement("Notes_AMT").sendKeys("For Mon Worked For"+ExcelMethods.getData("Sheet2", "Thru", rowNo)+"Hrs");
		}
	clickElement("SavNots_Btn");
	
	clickElement("friCell_MT");
	enterFriTime(ExcelMethods.getData(Sheetnam, "Fri", rowNo));
	//getElement("Notes_AMT").sendKeys("Notes_AMT","For Fri Worked For"+ExcelMethods.getData("Sheet2", "Fri", rowNo)+"Hrs");
	try {
		type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Fri", rowNo)+"Hrs");
		}catch(NoSuchElementException | NullPointerException e){
			getElement("Notes_AMT").sendKeys("For Mon Worked For"+ExcelMethods.getData("Sheet2", "Fri", rowNo)+"Hrs");
		}
	clickElement("SavNots_Btn");
	
	clickElement("satCell_MT");

}

public void MaxHrsVerf() throws InterruptedException {
	
	String actualSuccessNotification = getText("notification");
	//System.out.println(actualSuccessNotification);
	logger.log(Level.INFO, actualSuccessNotification);
	String ExpectedSuccessNotification = "Warning"+'\n'+"You have reached the maximum hours for the week";
	Assert.assertEquals(actualSuccessNotification, ExpectedSuccessNotification);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}

public void MinHrsVerf() throws InterruptedException {
	
	String actualSuccessNotification = getText("notification");
	System.out.println(actualSuccessNotification);
	String ExpectedSuccessNotification = "Warning"+'\n'+"You have reached the maximum hours for the week";
	Assert.assertEquals(actualSuccessNotification, ExpectedSuccessNotification);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}

public void notesVerf() throws InterruptedException {
	
	clickElement("Submit_MT");
	driver.getWindowHandle();
	try{clickElement("confirmationPopUpYes1");
}catch(ElementClickInterceptedException e){
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", getElement("confirmationPopUpYes1"));
}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	/*String actualSuccessNotification = getText("notMandWarni");
	System.out.println(actualSuccessNotification);
	String ExpectedSuccessNotification = "Notes are mandatory for each log";*/
	getText("notification").trim().contentEquals("Warning\r\n" + 
			"Notes are mandatory for each log");
	Assert.assertEquals(getText("notification").trim(), "Warning"+'\n'+"Notes are mandatory for each log");
}

public void minTimeVerf(String Sheetnam,int rowNo) throws InterruptedException {
	
	clickElement("monCell_MT");
	enterMonTime(ExcelMethods.getData(Sheetnam, "Mon", rowNo));
	
	try {
		clickElement("Notes_AMT");
		}catch(NoSuchElementException | NullPointerException  e){
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
					clickElement("Notes_AMT1");
				}
	
	try {
		type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Mon", rowNo)+"Hrs");
		}catch(NoSuchElementException | NullPointerException e){
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
			js.executeScript("arguments[0].value='"+ "For Mon Worked For"+ExcelMethods.getData("Sheet2", "Mon", rowNo)+"Hrs" +"';", getElement("Notes_AMT"));
		}
	

	clickElement("SavNots_Btn");
	
	clickElement("tueCell_MT");
	enterTueTime(ExcelMethods.getData(Sheetnam, "Tue", rowNo));
	
	try {
		clickElement("Notes_AMT");
		}catch(NoSuchElementException | NullPointerException  e){
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
					clickElement("Notes_AMT1");
				}
	
	
	try {
		type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Tue", rowNo)+"Hrs");
		}catch(NoSuchElementException | NullPointerException e){
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
			js.executeScript("arguments[0].value='"+ "For Mon Worked For"+ExcelMethods.getData("Sheet2", "Tue", rowNo)+"Hrs" +"';", getElement("Notes_AMT"));
		}
	
	clickElement("SavNots_Btn");
	
	
	//Below assertion we are checking submit btn should be disabled due to less time hrs 
	Assert.assertEquals(getElement("Submit_MT").isEnabled(), false);
	
	clickElement("wedCell_MT");
	enterWedTime(ExcelMethods.getData(Sheetnam, "Wed", rowNo));
	try {
		clickElement("Notes_AMT");
		}catch(NoSuchElementException | NullPointerException  e){
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
					clickElement("Notes_AMT1");
				}
		
	try {
		type("Notes_AMT","For Mon Worked For"+ExcelMethods.getData("Sheet2", "Wed", rowNo)+"Hrs");
		}catch(NoSuchElementException | NullPointerException e){
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
			js.executeScript("arguments[0].value='"+ "For Mon Worked For"+ExcelMethods.getData("Sheet2", "Wed", rowNo)+"Hrs" +"';", getElement("Notes_AMT"));
		}
	
	
	clickElement("SavNots_Btn");
	
	//Below assertion we are checking submit btn should be enabled  due to entered  time hrs is more then minium time hrs
	Assert.assertEquals(getElement("Submit_MT").isEnabled(), true);	
	
	clickElement("SavNots_Btn");
	clickElement("Submit_MT");
	driver.getWindowHandle();
	
	try{clickElement("confirmationPopUpYes1");
}catch(ElementClickInterceptedException e){
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", getElement("confirmationPopUpYes1"));
}
	clickElement("logout");
}

}


