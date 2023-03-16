package com.telliant.pageObjects;



import java.util.concurrent.TimeUnit;




import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;
import java.util.logging.Level; 
import java.util.logging.Logger;

public class EmpCreationPage extends WebDriverRoot {
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);	
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 Logger logger = Logger.getLogger(EmpCreationPage.class.getSimpleName());
	
	public void fillEmpCreationFormMandatoryFields() throws InterruptedException {
		// waitForElementClickable("firstNameTxtFld_EH");
		enterFirstName(ExcelMethods.getData("Sheet1", "firstName", 1));
		//enterLastName(ExcelMethods.getData("Sheet1", "lasttName", 1));
		 enterDateOfBirth(ExcelMethods.getData("Sheet1", "dateOfBirth", 1));
		enterEmailID(ExcelMethods.getData("Sheet1", "emailID", 1));
		enterEmpContactNum(ExcelMethods.getData("Sheet1", "empContactNum", 1));
		
		clickElement("empsection_ED");
		enterEmpCode(ExcelMethods.getData("Sheet1", "empCode", 1));
		enterDateOfJoining(ExcelMethods.getData("Sheet1", "dateofJoining", 1));
		selectPrivilegeLevel();
		selectDepartment();
     	selectReportingManager(ExcelMethods.getData("Sheet1", "reportingMgr", 1));
		selectRole();
		selectDesigination();
		clickElement("empsection_PJ");
		assignProject(ExcelMethods.getData("Sheet1", "projectName", 1));
		assignProject(ExcelMethods.getData("Sheet1", "projectName", 2));
		assignProject(ExcelMethods.getData("Sheet1", "projectName", 3));
		assignProject(ExcelMethods.getData("Sheet1", "projectName", 4));
		clickElement("empsection_TS");
		enterMaxhours(ExcelMethods.getData("Sheet1", "maxhours", 1));
		enterMinhours(ExcelMethods.getData("Sheet1", "minhours", 1));
		ScrollElement2();
		Thread.sleep(3000);
		clickSaveBtn();
		waitForElementVisible("notification");
		Assert.assertEquals( getText("notification"), "Notification" +'\n'+"Employee "+ExcelMethods.getData("Sheet1", "firstName", 1)+" Created Successfully");
	}
	
	public void enterFirstName(String firstName) {

		type("firstNameTxtFld_EH", firstName);
	}

	public void enterMiddleName(String middleName) {
		type("midNameTxtFld_EH", middleName);
	}

	public void enterLastName(String lastName) {
		clickElement("lastNameTxtFld_EH");
		type("lastNameTxtFld_EH", lastName);
	}

	public void enterDateOfBirth(String dateOfBirth) {
		 clickElement("dobTxtFld");
		type("dobSelectDate_EH", dateOfBirth);
	}

	public void enterDateOfJoining(String dateofJoining) {
		mouseOver("dojTextField");
		clickElement("DojClose_EH");
		clickElement("dojTextField");
		type("dojSelectDate_EH", dateofJoining);
	}

	public void enterEmailID(String emailID) {
	clickElement("empEmailTxtFld_EH");
	type("empEmailTxtFld_EH", emailID);
	}

	public void enterEmpContactNum(String contactNum) {
		
		type("empContactTxtFld_EH", contactNum);
	}

	public void enterEmpCode(String empCode) {
		clickElement("empCodeTxtFld");
		type("empCodeTxtFld", empCode);
	}

	public void enterMaxhours(String maxhours) {
		type("maxHours_EH", maxhours);
	}

	public void enterMinhours(String minhours) {
		type("minHours_EH", minhours);
	}

	public void selectReportingManager(String reportingMgr) {
		clickElement("reportingMgrDrpdwn");
		waitForElementVisible("reportingMgrTxtFld");
		type("reportingMgrTxtFld", reportingMgr);
		pressKeyEnter("reportingMgrTxtFld");
	}

	public void selectPrivilegeLevel() {
		waitForElementVisible("privilegeLevelDrpdwn");
		clickElement("privilegeLevelDrpdwn");
		clickElement("privilegeLevelDrpdwnValue");
	}
	
	public void selectDepartment() {
		waitForElementVisible("DeptTxtField");
		clickElement("DeptTxtField");
		clickElement("DeptDropdownvalue");
	}
	public void selectRole() {
		waitForElementVisible("roleDrpdwn");
		clickElement("roleDrpdwn");
		clickElement("roleDrpdwnValue");
	}

	public void selectDesigination() {
		waitForElementVisible("DesiginationDrpdwn");
		clickElement("DesiginationDrpdwn");
		clickElement("DesiginationDrpdwnValue");
	}

	public void assignProject(String projectName) {
		clickElement("assignPrjctDrpdwn");
		type("assignPrjctDrpdwnTxtFld", projectName);
		pressKeyEnter("assignPrjctDrpdwnTxtFld");
		clickElement("assignPrjctAddIcon");
	}

	public void clickSaveBtn() {
		clickElement("saveempbut_EH");
	}

	public void clickCancelBtn() {
		clickElement("cancelBtn");
	}
	
	public void updateEmpmailIdInExcel() { 
	        // Initializing String
	        String Str = new String(ExcelMethods.getData("Sheet1", "emailID", 1));
	        //System.out.print("The original string  is : ");
	        //System.out.println(Str);
	        logger.log(Level.INFO, Str); 
	        
	        //System.out.print("The extracted substring  is : ");
	        // System.out.println(Str.substring(10, 12));
	        String S =Str.substring(10, 12) ;
	        //System.out.println(S);
	        logger.log(Level.INFO, S); 
	        //convert string to integer
	        int i = Integer.parseInt(S); 	
	        int j=i+1; 	
	        //convert integer to String 
	        String sj=String.valueOf(j);
	        String si=String.valueOf(i);
	        String s1=new String(ExcelMethods.getData("Sheet1", "emailID", 1)); 
	        String replaceString=s1.replaceAll(si,sj);  
	        //System.out.println("Updated Employee mailid is :" +replaceString); 
	        logger.log(Level.INFO, "Updated Employee mailid is :" +replaceString); 
	        ExcelMethods.putData("Sheet1", "emailID", 1, replaceString);
	    
	}
	public void updateEmpIdInExcel() { 
        // Initializing String
        String Str = new String(ExcelMethods.getData("Sheet1", "empCode", 1));
        //System.out.println(Str);
        logger.log(Level.INFO, Str); 
        String S =Str.substring(3, 5) ;
        //System.out.println(S);
        logger.log(Level.INFO, S); 
        //convert string to integer
        int i = Integer.parseInt(S); 	
        int j=i+1; 	
        //convert integer to String 
        String sj=String.valueOf(j);
        String si=String.valueOf(i);
        String s1=new String(ExcelMethods.getData("Sheet1", "empCode", 1)); 
        String replaceString=s1.replaceAll(si,sj);  
        //System.out.println("Updated Employeeid is :" +replaceString); 
        logger.log(Level.INFO, "Updated Employeeid is :" +replaceString); 
        ExcelMethods.putData("Sheet1", "empCode", 1, replaceString);    
}
	public void updateUserNamePwdInXL() { 
		ExcelMethods.putData("Sheet1", "UserName", 6, ExcelMethods.getData("Sheet1", "emailID", 1));
		ExcelMethods.putData("Sheet1", "empCode", 2, ExcelMethods.getData("Sheet1", "empCode", 1));
	
	}
	
	 public String[] EmpHubPageVerificationGetdata(int rowno ) throws InterruptedException {
		   String EmpCode,Empname,EmpMailID;
		   String [] EmpcontNum;
		   EmpCode= ExcelMethods.getData("Sheet1", "empCode", rowno);
		   Empname= ExcelMethods.getData("Sheet1", "firstName", rowno);
		   EmpMailID= ExcelMethods.getData("Sheet1", "emailID", rowno);
		
		   return new String[] {EmpCode,Empname,EmpMailID}; 
		   
		}
	 
	 public void EmpsearchByEmpCode( String EmpCode ) throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			try{
				type("searchBx_EH",EmpCode);
			}catch(NoSuchElementException e){
				driver.navigate().refresh();
				type("searchBx_EH",EmpCode);
			}
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			Thread.sleep(1700);

		 	waitForElementClickable2("empSelfrmList_EH");

			Thread.sleep(1900);
			WebDriverWait wait=new WebDriverWait(driver, 15);
		 	wait.until(ExpectedConditions.elementToBeClickable(getElement("empSelfrmList_EH")));
			try{
				clickElement("empSelfrmList_EH");
			}catch(StaleElementReferenceException e){
				
				js.executeScript("arguments[0].click();", getElement("empSelfrmList_EH"));
			}
			
		}
	 
	 public void VerifyEmp( String EmpCode, String Empname, String EmpMailID ) throws InterruptedException {
			
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			Thread.sleep(1800);
		 		String actualEmpname =getElement("firstNameTxtFld_EH").getAttribute("value");
		 		String actualEmpCode =getElement("empCodeTxtFld").getAttribute("value");
		 		String actualEmpMailID =getElement("empEmailTxtFld_EH").getAttribute("value");
		 		
		 		
		 		
		 		
		 		Assert.assertEquals(actualEmpname, Empname);
		 		Assert.assertEquals(actualEmpCode, EmpCode);
		 		Assert.assertEquals(actualEmpMailID, EmpMailID);
		 		
		 		}
	
	public void EmpHubPageVerification() throws InterruptedException {
		
		 //fetching first row data from sheet1.If need to fetch 9 row data need to increment by one eg rowno++;
		int rowno = 8;
		String[] Empdata=EmpHubPageVerificationGetdata(rowno);
		
		//System.out.print("Empdata: "+Empdata[0]);
		logger.log(Level.INFO, "Empdata: "+Empdata[0]); 
		 
		
		/*
		Empdata[0] = EmpCode
		Empdata[1] = Empname
		Empdata[2] = emailID
		*/
		EmpsearchByEmpCode(Empdata[0]);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickElement("empInfoPgEmpDD_EH");
		VerifyEmp(Empdata[0],Empdata[1],Empdata[2]);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clickElement("empInfoPageBkBtn");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		rowno++;
		Empdata=EmpHubPageVerificationGetdata(rowno);
		EmpsearchByEmpCode(Empdata[0]);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clickElement("empInfoPgEmpDD_EH");
		VerifyEmp(Empdata[0],Empdata[1],Empdata[2]);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clickElement("empInfoPageBkBtn");
		clickElement("logout");
	}
	
public void editEmpInfoAndVerify(int rowno) throws InterruptedException {
	 
		String[] td=editEmpInfoAndVerifyGetdata(rowno);
		/*
		td[0] = ExisReprMgrEmpCode   td[3] = NewReprMgrEmpCode   td[6] = EmpemailID  td[9] = EmpID
		td[1] = ExisReprMgrEmpName   td[4] = NewReprMgrEmpName   td[7] = maxhours    td[10] = EmpName
		td[2] = ExisReprMgrMailID    td[5] = NewReprMgrMailID    td[8] = minhours
		*/
		EmpsearchByEmpCode(td[9]);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		clickElement("empInfoPgEmpDD_EH");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		editEmpinfo(td[2]);
		expandEmpHub();
		editAprvinfo(td[3],td[0],td[10]);
		editMinMaxTime(td[7],td[8],td[10]);
		clickElement("logout");
		
		
	}

public String[] editEmpInfoAndVerifyGetdata(int rowno ) throws InterruptedException {
	   String ExisReprMgrEmpCode,EmpID,EmpName,ExisReprMgrEmpName,ExisReprMgrMailID,NewReprMgrEmpCode,NewReprMgrEmpName,NewReprMgrMailID,EmpemailID,maxhours,minhours;
	   
	   ExisReprMgrEmpCode= ExcelMethods.getData("Sheet1", "reportingMgrID", rowno);
	   ExisReprMgrEmpName= ExcelMethods.getData("Sheet1", "reportingMgr", rowno);
	   ExisReprMgrMailID= ExcelMethods.getData("Sheet1", "reportingMgrEmailID", rowno);
	   NewReprMgrEmpCode= ExcelMethods.getData("Sheet1", "NewreportingMgrID", rowno);
	   NewReprMgrEmpName= ExcelMethods.getData("Sheet1", "NewreportingMgr", rowno);
	   NewReprMgrMailID= ExcelMethods.getData("Sheet1", "NewreportingMgrEmailID", rowno);
	   EmpemailID= ExcelMethods.getData("Sheet1", "emailID", rowno);
	   maxhours= ExcelMethods.getNum("Sheet1", "maxhours", rowno);
	   minhours= ExcelMethods.getNum("Sheet1", "minhours", rowno);
	   EmpID= ExcelMethods.getData("Sheet1", "empCode", rowno);
	   EmpName= ExcelMethods.getData("Sheet1", "firstName", rowno);
	
	   return new String[] {ExisReprMgrEmpCode,ExisReprMgrEmpName,ExisReprMgrMailID,NewReprMgrEmpCode,NewReprMgrEmpName,NewReprMgrMailID,EmpemailID,maxhours,minhours,EmpID,EmpName}; 
	   
	}

public void editMinMaxTime(String maxhours,String minhours,String empName) throws InterruptedException {
	//Click on Edit Time Card Secion
	clickElement("EdtTimCrdBtn");
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  System.out.println(String.valueOf((Integer.parseInt(minhours)+5)) +" & "+(Integer.parseInt(maxhours)+5)); 
	  getElement("maxHours_EH").sendKeys(Keys.CONTROL + "a");
	  getElement("maxHours_EH").sendKeys(Keys.DELETE );
	  type("maxHours_EH", String.valueOf((Integer.parseInt(maxhours)+5)));
	  getElement("minHours_EH").sendKeys(Keys.CONTROL + "a");
	  getElement("minHours_EH").sendKeys(Keys.DELETE );
	  type("minHours_EH", String.valueOf((Integer.parseInt(minhours)+5)));
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 clickElement("Not_ar_mand_EH");
	  clickElement("TimCrdSaveBtn");
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  Thread.sleep(600);
	 
		 Assert.assertEquals(getText("notif"),  "Employee "+empName+" TimeCard Settings Updated Successfully");
		 clickElement("noti_cls");
		}

public void editAprvinfo(String NewReprMgrEmpCode,String ExisReprMgrEmpCode,String empName) throws InterruptedException {
	  //Click on Edit Approval Details section
	 clickElement("EdtAprvBtn");
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
	 //Updating Approver  from Drop Down
	  Thread.sleep(350);
	  try {
			 clickElement("AprvBtn");
	  }catch(NullPointerException | TimeoutException e ){
		js.executeScript("arguments[0].click();", getElement("AprvBtn"));
	}
	  
	  Thread.sleep(350);
	  
	 try {
		 clickElement("AprvDrpdwnLst",NewReprMgrEmpCode);
	 }catch(ElementNotInteractableException e ){
		
			js.executeScript("arguments[0].click();", getElement("AprvDrpdwnLst",NewReprMgrEmpCode));
	 }
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  clickElement("AprvSaveBtn");
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  Thread.sleep(350);
	  String actualSuccessNotification = getText("notif");
		 String ExpectedSuccessNotification= "Employee "+empName+" Approval Details Updated Successfully";
		 Assert.assertEquals(actualSuccessNotification, ExpectedSuccessNotification);
		  clickElement("noti_cls");
}

public void expandEmpHub() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  clickElement("EmpInfoDrpdwn");
	  clickElement("ProjInfoDrpdwn");  
	  clickElement("skilInfoDrpdwn");
	  clickElement("LeavInfoDrpdwn");
	  clickElement("AprvDrpdwn");
	  clickElement("TimCrdDrpdwn");
	  clickElement("CtCDrpdwn");
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
}

public void editEmpinfo(String emailID) throws InterruptedException {
	  
	  clickElement("EdtEmpPersInfoBtn_EH");
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	  Thread.sleep(600);
	  type("empEmailTxtFld_EH", emailID);
	  clickElement("EmpInfSaveBtn");
	  }

public String[] webEmpTabDat(int row ,int col) throws InterruptedException {
	String[] rowdats = new String [col]; 
	
	/* "row" variable  we are passing from which row we need to pick data from web table 
	"col" till which coulme requried  to fetch data */
	
	for(int i=1;i<col;i++) {  
		
		//Below line forming xapth to fetch data from web application 
	    String element = "(//tr["+String.valueOf(row)+"]//child::td//child::div[@class='pointer employee-row'])["+String.valueOf(i)+"]";
	    
	    //Above created xpath saving in objectRepo 
	    WebElement TableElement = driver.findElement(By.xpath(element));
	    
	    try {
	    	//rowdats[i-1] because we need to store array value from index
	    try {
	    	js.executeScript("arguments[0].scrollIntoView(true)",TableElement);
	   // System.out.println(getElement("TablCont").getText());
	    logger.log(Level.INFO,getElement("TablCont").getText());  
	    rowdats[i-1] = TableElement.getText();
	    }catch(NoSuchElementException|NullPointerException e ) {
	    	js.executeScript("arguments[0].scrollIntoView(true)", TableElement);
	    	rowdats[i-1] = js.executeScript("return arguments[0].text", TableElement).toString();
	    }
	    // below catch exception get emp status from the list 
	    }catch(NoSuchElementException|NullPointerException e ) {
	    	if((getElement("ActionBtn",String.valueOf(row+2)).getAttribute("aria-checked")).contentEquals("false")) {
	    		
	    		// In Below active switch is visible  only after 2nd index in the Emp list  page so we are adding two
	    		
	    		rowdats[i-1] = "Inactive";
		    	//System.out.println(rowdats[i-1]+"&1"+getElement("ActionBtn",String.valueOf(row+2)).getAttribute("aria-checked"));
		    	 logger.log(Level.INFO,rowdats[i-1]+"&1"+getElement("ActionBtn",String.valueOf(row+2)).getAttribute("aria-checked"));
	    	}else if ((getElement("ActionBtn",String.valueOf(row+2)).getAttribute("aria-checked")).contentEquals("true")){
	    		
	    		rowdats[i-1] = "Active";
	    		System.out.println(rowdats[i-1] +"&"+getElement("ActionBtn",String.valueOf(row+2)).getAttribute("aria-checked"));
	    		logger.log(Level.INFO,rowdats[i-1] +"&"+getElement("ActionBtn",String.valueOf(row+2)).getAttribute("aria-checked"));
	    		}
	    }
	}
	
	return rowdats;
}

public void ExptAndVerfEmplist() throws InterruptedException {
	
	 /*Thread.sleep(5000); 
	 clickElement("EmpExportBtn");
	 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	 Thread.sleep(2800);*/
	 //System.out.println("Refence "+ExcelMethods.getExportData("Employee Details","Employee Details", "Emp. Id", 4)+"&"+ExcelMethods.getExptRowCount("Employee Details"));
	 logger.log(Level.INFO, "Refence "+ExcelMethods.getExportData("Employee Details","Employee Details", "Emp. Id", 4)+"&"+ExcelMethods.getExptRowCount("Employee Details")); 
	 
	  SoftAssert softAssertion= new SoftAssert();
	  
	  //Below "rowColNam" array we are declaring required  column name to fetch data from XL .
	  //Passing this array in getExptRowdat(String FileNamewithoutextention,String sheetName, String[] ColNamelist Array , Int Rowno )
	  String [] rowColNam = {"Emp. Id","Emp Name","Type","Role","Reporting Manager","Privilege Level","Date of joining","Status"};
	  
	  
	  
	  //System.out.println("Refence web data "+webEmpTabDat(4,9)+"  Colmrow count"+rowColNam.length);
	  //System.out.println("Refence "+ExcelMethods.getExptRowdat("Employee Details","Employee Details",rowColNam,1));
	  
	  int rowCount = ExcelMethods.getExptRowCount("Employee Details");
	  //System.out.println("Refence "+rowCount);
	  logger.log(Level.INFO, "Refence "+rowCount); 
	  
	  //String [] webData = webEmpTabDat(1,9);
	  for(int i=1;i<rowCount;i++) {
		  String [] webDatas = webEmpTabDat(i,rowColNam.length);
		  for(int l=0;l<8;l++) {
	 //System.out.println("Refence web data "+i+webDatas[l]);
	  logger.log(Level.INFO,"Refence web data "+i+webDatas[l]);
		  }
	  }
	 /*for(int i=1;i<rowCount;i++) {
		  String [] xlData = ExcelMethods.getExptRowdat("Employee Details","Employee Details", rowColNam, i);
		  EventFiringWebDriver efw = new EventFiringWebDriver(driver);
		  efw.executeScript("document.querySelector('div.ant-table-body').scrollDown=3000");
		  //in below method to fetch web application data row wise in an array webEmpTabDat(row+1,col) .
		   // row & Col  value should starts from 1 Since we are fetch element using index for loop will be staring from 1. 
		   String [] webData = webEmpTabDat(i,rowColNam.length);
		  for(int l=0;l<8;l++) {
			  System.out.println(xlData[l]+" Ref1 "+ webData[l]);
			  softAssertion.assertEquals(xlData[l], webData[l]);
			  
		  }
		  
	  }*/
	 
	  }

}
