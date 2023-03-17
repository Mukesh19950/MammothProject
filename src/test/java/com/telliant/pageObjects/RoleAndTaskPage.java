package com.telliant.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;

public class RoleAndTaskPage  extends WebDriverRoot {
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	 JavascriptExecutor js = (JavascriptExecutor)driver; 
	
	
	public void srcRolAndSel(String RolName) throws InterruptedException { 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	      // invisibilityOfElementLocated condition
		/*WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(getElement("CusScrBx")));*/
		Thread.sleep(1000);
		
		clickElement("RolPgScrBx");
		type("RolPgScrBx",RolName);
		getElement("RolPgScrBx").sendKeys(Keys.ENTER );
		
		clickElement("SelRol&TaskFrmLst");
		Thread.sleep(750);
		
		try {
			clickElement("SelRol&TaskFrmLst");
			}catch(StaleElementReferenceException e ) {
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
				Thread.sleep(1500);
				clickElement("RolPgScrBx");
				type("RolPgScrBx",RolName);
				getElement("RolPgScrBx").sendKeys(Keys.ENTER );
				js.executeScript("arguments[0].click();", getElement("SelRol&TaskFrmLst"));	
			}
	        }

	public void srcTaskAndSel(String taskName) throws InterruptedException { 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	      // invisibilityOfElementLocated condition
		/*WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(getElement("CusScrBx")));*/
		Thread.sleep(750);
		clickElement("TskPgScrBx");
		type("TskPgScrBx",taskName);
		getElement("TskPgScrBx").sendKeys(Keys.ENTER );
		Thread.sleep(750);
		
		try {
			clickElement("SelTaskFrmLst",taskName);
			}catch(StaleElementReferenceException e ) {
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
				Thread.sleep(750);
				clickElement("TaskScrBx");
				type("TaskScrBx",taskName);
				getElement("TaskScrBx").sendKeys(Keys.ENTER );
				js.executeScript("arguments[0].click();", getElement("SelTaskFrmLst",taskName));
			}
		    }

	public void AddRolAndVerf( ) throws InterruptedException {
		clickElement("RolPgAddRoleBtn");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		String RolNam,RolSrtName;
		
		RolNam=randStrGen("UL",7);
		RolSrtName=randStrGen("U",3);
		 //System.out.println(RolNam+"  &1 "+RolSrtName+" &2 "+randStrGen("ULN",7)+" &3 "+randStrGen("LN",7)+" &2 "+randStrGen("UN",7)+" &2 "+randStrGen("U",7)+" &2 "+randStrGen("L",7)+" &2 "+randStrGen("N",7));
		
		// do{  
		 
		 clickElement("RolPgRolNamTxt");
		type("RolPgRolNamTxt",RolNam);
		
		ExcelMethods.putData("Sheet3", "Task/Role_Name", 1,RolNam);
		
		clickElement("RolPgRolDescTxt");
		type("RolPgRolDescTxt",ExcelMethods.getData("Sheet3", "Task/Role_Desc", 1));
		
		clickElement("RolPgShtNamTxt");
		type("RolPgShtNamTxt",RolSrtName);
		
		ExcelMethods.putData("Sheet3", "SrtName", 1,RolSrtName);
		
		clickElement("RolPgTaskFld");
		clickElement("RolAdd/EdtTaskDrpdwn",ExcelMethods.getData("Sheet3", "Tasks/Role", 1));
		
		clickElement("RolPgSavBtn");
		
		 //System.out.println(!(getElement("RolPgRolNamDuplChk").isEnabled()||getElement("RolPgRolShtNamDuplChk").isEnabled()));
		
		 //}while(!(getElement("RolPgRolNamDuplChk").isEnabled()||getElement("RolPgRolShtNamDuplChk").isEnabled())); 
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		 Thread.sleep(450);
		Assert.assertEquals(getText("notification"),"Notification"+ '\n' +"Role created successfully");
		
		//System.out.println("Task/Role_Name "+ExcelMethods.getData("Sheet3", "Task/Role_Name", 1));
		
       srcRolAndSel(ExcelMethods.getData("Sheet3", "Task/Role_Name", 1));
       
       Thread.sleep(750);
		
		Assert.assertEquals(getElement("RolPgRolNamTxt").getAttribute("value"), RolNam);
		Assert.assertEquals(getElement("RolPgShtNamTxt").getAttribute("value"), RolSrtName);
		Assert.assertEquals(getElement("RolPgUpdTaskFld").getText(), ExcelMethods.getData("Sheet3", "Tasks/Role", 1));
		Thread.sleep(500);
		
		clickElement("RolPgSavBtn");
		driver.navigate().refresh();
		clickElement("logout");

		
		
	}

	public void EdtRolAndVerf( ) throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		String EdtRolSrtName,EdtRolName;
		
		EdtRolSrtName=randStrGen("U",2);
		EdtRolName=ExcelMethods.getData("Sheet3", "Task/Role_Name", 1)+"Edited";
		
		srcRolAndSel(ExcelMethods.getData("Sheet3", "Task/Role_Name", 1));
		
		 clickElement("RolPgRolNamTxt");
		type("RolPgRolNamTxt",EdtRolName);
		
		ExcelMethods.putData("Sheet3", "Task/Role_Name", 1,EdtRolName);
		
		clickElement("RolPgRolDescTxt");
		type("RolPgRolDescTxt",ExcelMethods.getData("Sheet3", "Task/Role_Desc", 1)+"Edited");
		
		clickElement("RolPgShtNamTxt");
		type("RolPgShtNamTxt",EdtRolSrtName);
		
		ExcelMethods.putData("Sheet3", "SrtName", 1,EdtRolSrtName);
		
		try{
			getElement("RolPgTaskFld").click();
		}catch(ElementNotInteractableException e){
			js.executeScript("arguments[0].click();", getElement("RolPgTaskFld"));
		}
		
		//Need to fetch data from sheet3 & row 2 
		try {
			clickElement("RolAdd/EdtTaskDrpdwn",ExcelMethods.getData("Sheet3", "Task/Role_Name", 2));
			}catch(ElementNotInteractableException e){
				
				 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
					js.executeScript("arguments[0].click();", getElement("EdtTaskDrpdwn",ExcelMethods.getData("Sheet3", "Task/Role_Name", 2)));
			}
		
		
		clickElement("RolPgSavBtn");
		
		Thread.sleep(400);
		
		Assert.assertEquals(getText("notification"),"Notification"+ '\n' +"Roles Updated Successfully");
		
		ExcelMethods.putData("Sheet3", "Task/Role", 2,EdtRolName);
		srcRolAndSel(EdtRolName);
		
		Thread.sleep(750);
		
		Assert.assertEquals(getElement("RolPgRolNamTxt").getAttribute("value"), EdtRolName);
		Assert.assertEquals(getElement("RolPgShtNamTxt").getAttribute("value"), EdtRolSrtName);
		Assert.assertEquals(getElement("RolPgUpdTaskFld").getText(), ExcelMethods.getData("Sheet3", "Tasks/Role", 1));
		
		clickElement("logout");

		}

    public void addTskAndVerf( ) throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		String tskName;
		
		
		tskName=randStrGen("UL",8);
		clickElement("TskBtn");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
		//try {
		clickElement("TskPgAddRoleBtn");
		/*}catch(NoSuchElementException | TimeoutException e){
			js.executeScript("arguments[0].click();", getElement("TskPgAddRoleBtn"));
		}*/
		
		try {
		 clickElement("TskPgTskNam");
    }catch(NoSuchElementException e){
		js.executeScript("arguments[0].click();", getElement("TskPgTskNam"));
	}
	
		 
		type("TskPgTskNam",tskName);
		
		ExcelMethods.putData("Sheet3", "Task/Role_Name", 2,tskName);
		
		clickElement("TskPgTskDesc");
		type("TskPgTskDesc",ExcelMethods.getData("Sheet3", "Task/Role_Desc", 2));
		
		
		clickElement("TskPgRolFld");
		clickElement("RolAdd/EdtTaskDrpdwn",ExcelMethods.getData("Sheet3", "Task/Role_Name", 1));
		clickElement("TskPgTskNam");
	
			clickElement("TskPgSavBtn");
		
			Thread.sleep(1000);
		Assert.assertEquals(getText("notification"),"Notification"+ '\n' +"Task "+tskName+" Created Successfully");
		
		
		srcTaskAndSel(ExcelMethods.getData("Sheet3", "Task/Role_Name", 2));
		Thread.sleep(600);
		Assert.assertEquals(getElement("TskPgTskNam").getAttribute("value"), tskName);
		Assert.assertEquals(getElement("TskPgUpdTaskFld").getText(), ExcelMethods.getData("Sheet3", "Task/Role_Name", 1));
		
		clickElement("logout");

		}

    public void edtTskAndVerf( ) throws InterruptedException {
    	clickElement("TskBtn");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		String edttskName;
		srcTaskAndSel(ExcelMethods.getData("Sheet3", "Task/Role_Name", 2));
		
		edttskName=ExcelMethods.getData("Sheet3", "Task/Role_Name", 2)+"Edited";
		
		clickElement("TskPgTskNam");
		type("TskPgTskNam",edttskName);
		
		ExcelMethods.putData("Sheet3", "Task/Role_Name", 2,edttskName);
		
		clickElement("TskPgTskDesc");
		type("TskPgTskDesc",ExcelMethods.getData("Sheet3", "Task/Role_Desc", 2)+"Edited");
		
		
		clickElement("TskPgSavBtn");
		
		Thread.sleep(700);
		Assert.assertEquals(getText("notification"),"Notification"+ '\n' +"Task "+edttskName+" Updated Successfully");
		
		srcTaskAndSel(ExcelMethods.getData("Sheet3", "Task/Role_Name", 2));
		Thread.sleep(600);
		Assert.assertEquals(getElement("TskPgTskNam").getAttribute("value"), edttskName);
		
		//updating  newly edited task name in sheet 3 row 1  
		ExcelMethods.putData("Sheet3", "Task/Role", 1,edttskName);
		
		clickElement("logout");

		}

}
