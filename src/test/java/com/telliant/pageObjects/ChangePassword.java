package com.telliant.pageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;
import java.util.logging.Level; 
import java.util.logging.Logger; 

public class ChangePassword extends WebDriverRoot {
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	Logger logger = Logger.getLogger(ChangePassword.class.getSimpleName()); 

	public void fillChangePwdFields() throws InterruptedException {
		
		enterCurrentpwd(ExcelMethods.getData("Sheet3", "Currentpwd", 1));
		enternewpwd(ExcelMethods.getData("Sheet3", "newpwd", 1));
		enterconnewpwd(ExcelMethods.getData("Sheet3", "coinfnewpwd", 1));
		clickElement("Change_pwd_btn");		
	}
	
	public void updateNewPwdinXL() throws InterruptedException{
		//to swapping old password to Upcomming Password
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//System.out.println("Old password : " +(ExcelMethods.getData("Sheet1", "Password", 6)));
		logger.log(Level.INFO,"Old password : " +(ExcelMethods.getData("Sheet1", "Password", 6)));
		ExcelMethods.putData("Sheet1", "Password", 6, ExcelMethods.getData("Sheet3", "coinfnewpwd", 1));
		ExcelMethods.putData("Sheet3", "coinfnewpwd", 1, ExcelMethods.getData("Sheet3", "Currentpwd", 1));
		ExcelMethods.putData("Sheet3", "newpwd", 1, ExcelMethods.getData("Sheet3", "Currentpwd", 1));
		ExcelMethods.putData("Sheet3", "Currentpwd", 1, ExcelMethods.getData("Sheet1", "Password", 6));
		//System.out.println("New password : " +(ExcelMethods.getData("Sheet1", "Password", 6)));
		
		logger.log(Level.INFO, "New password : " +(ExcelMethods.getData("Sheet1", "Password", 6))); 
	}
	public void enterCurrentpwd(String Currentpwd) {
		type("Current_pwd", Currentpwd);
	}
	public void enternewpwd(String newpwd) {
		type("New_pwd", newpwd);
	}
	public void enterconnewpwd(String coinfnewpwd) {
		type("CoinfirmNew_pwd", coinfnewpwd);
	}
}