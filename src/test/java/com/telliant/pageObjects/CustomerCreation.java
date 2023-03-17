package com.telliant.pageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;
import java.util.logging.Level; 
import java.util.logging.Logger; 

public class CustomerCreation extends WebDriverRoot {
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 Logger logger = Logger.getLogger(CustomerCreation.class.getSimpleName());
	
	public void fillCusCreationFormMandatoryFields() throws InterruptedException {
		//Thread.sleep(5000);
		enterCustomerName(ExcelMethods.getData("Sheet3", "custName", 1));	
		selectCustomerType();
		selectIndustry();
		//clickElement("cusCollapsi_PC");
		clickElement("CusSavebtn");
		waitForElementVisible("notification");
		clickElement("notification");
		Assert.assertEquals( getText("notification"), "Notification" +'\n'+"Customer "+ExcelMethods.getData("Sheet3", "custName", 1)+" Created Successfully");
	}
	
	public void enterCustomerName(String custName) {
		type("customername_CH", custName);
	}
	
	public void selectCustomerType() {
		waitForElementVisible("CustomerTypeDrpdwn");
		clickElement("CustomerTypeDrpdwn");
		clickElement("CustomerTypeDrpdwnValue_pot");
	}
	
	public void selectIndustry() {
		waitForElementVisible("industryTypeDrpdwn");
		clickElement("industryTypeDrpdwn");
		clickElement("industryTypeDrpdwnValue_log");
	}
	
	public void updateCustomerName() { 
        // Initializing String
        String Str = new String(ExcelMethods.getData("Sheet3", "custName", 1));
        logger.log(Level.INFO, Str);
        //System.out.println(Str);
        String S =Str.substring(18, 20) ;
        logger.log(Level.INFO, S);
        //System.out.println(S);
        //convert string to integer
        int i = Integer.parseInt(S); 	
        int j=i+1; 	
        //convert integer to String 
        String sj=String.valueOf(j);
        String si=String.valueOf(i);
        String s1=new String(ExcelMethods.getData("Sheet3", "custName", 1)); 
        String replaceString=s1.replaceAll(si,sj);  
        //System.out.println("Updated Employeeid is :" +replaceString);
        logger.log(Level.INFO, "Updated Employeeid is :" +replaceString);
        ExcelMethods.putData("Sheet3", "custName", 1, replaceString);      
    
}
	
	public void srcCusAndSel(String custName) throws InterruptedException { 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	      // invisibilityOfElementLocated condition
		/*WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(getElement("CusScrBx")));*/
		Thread.sleep(750);
		
		type("CusScrBx",custName);
		getElement("CusScrBx").sendKeys(Keys.ENTER );
		
		try {
			clickElement("SelCusFrmLst",custName);
			}catch(StaleElementReferenceException e ) {
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
				Thread.sleep(1500);
				clickElement("CusScrBx");
				type("CusScrBx",custName);
				getElement("CusScrBx").sendKeys(Keys.ENTER );
				js.executeScript("arguments[0].click();", getElement("SelCusFrmLst",custName));
				
			}
		
	}
	
	public void editCusInfo(int rowNo ) { 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		
		clickElement("CustomerType");
		clickElement("CustomerTypeDrpdwn",ExcelMethods.getData("Sheet3", "CustHubType", rowNo));
		
		clickElement("industryType");
		clickElement("industryTypeDrpd",ExcelMethods.getData("Sheet3", "CustHubIndus", rowNo));
		
		clickElement("customername_CH");
		type("customername_CH",ExcelMethods.getData("Sheet3", "custName", rowNo)+"Edited");
		ExcelMethods.putData("Sheet3", "custName", rowNo, ExcelMethods.getData("Sheet3", "custName", rowNo)+"Edited");
		
		clickElement("CusPrimContDrpDwn");
		
		clickElement("cusName_PC");
		type("cusName_PC",ExcelMethods.getData("Sheet3", "PrimContNam", rowNo));
		
		clickElement("cusEmail_Pc");
		type("cusEmail_Pc",ExcelMethods.getData("Sheet3", "PrimContEmail", rowNo));
		
		clickElement("cusphonenum_pc");
		type("cusphonenum_pc",ExcelMethods.getNum("Sheet3", "PrimContPhnNum", rowNo));
		
		clickElement("CusSavebtn");
		
		Assert.assertEquals(getText("notification"),"Notification"+ '\n' +"Customer "+ExcelMethods.getData("Sheet3", "custName", rowNo)+" Updated Successfully");
	
	}

	public void verfEditCusInfo(int rowNo ) {
		
		Assert.assertEquals(getElement("customername_CH").getAttribute("value"), ExcelMethods.getData("Sheet3", "custName", rowNo));
		Assert.assertEquals(getElement("CustomerType_CH",ExcelMethods.getData("Sheet3", "CustHubType", rowNo)).getText(), ExcelMethods.getData("Sheet3", "CustHubType", rowNo));
		Assert.assertEquals(getElement("CustomerType_CH",ExcelMethods.getData("Sheet3", "CustHubIndus", rowNo)).getText(), ExcelMethods.getData("Sheet3", "CustHubIndus", rowNo));
		
		clickElement("CusPrimContDrpDwn");
		 
		Assert.assertEquals(getElement("cusName_PC").getAttribute("value"), ExcelMethods.getData("Sheet3", "PrimContNam", rowNo));
		Assert.assertEquals(getElement("cusEmail_Pc").getAttribute("value"), ExcelMethods.getData("Sheet3", "PrimContEmail", rowNo));
		Assert.assertEquals(getElement("cusphonenum_pc").getAttribute("value"), ExcelMethods.getNum("Sheet3", "PrimContPhnNum", rowNo));
		clickElement("logout");
	}
}
