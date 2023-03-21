package com.telliant.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import com.telliant.core.web.BaseClass;
public class LoginPage extends BaseClass {
	
	JavascriptExecutor executor = (JavascriptExecutor)driver;

	public void login(String username, String pwd) throws InterruptedException {
		try {
			clickElement("username");
			}catch(StaleElementReferenceException | NullPointerException e){
				try {
				executor.executeScript("arguments[0].click();", getElement("username"));
				}catch( NullPointerException e1){
					clickElement("logout");
					Thread.sleep(700);
					executor.executeScript("arguments[0].click();", getElement("username"));
				}
			}
			
			try {
				type("username", username);
				}catch(StaleElementReferenceException e){
					 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
					executor.executeScript("arguments[0].value='"+ username +"';", getElement("username"));
				}
			
			try {
				type("password", pwd);
				}catch(StaleElementReferenceException e){
					
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].value='"+ pwd +"';", getElement("password"));
				}
			
			clickElement("loginBtn");
		}
	public void proceed() throws InterruptedException {
		Thread.sleep(7000);
//		selectDropdownByValue("location", "116");
		waitForElementClickable("proceed");
		clickElement("proceed");
	}


	public void logout() {
		
		waitForElementClickable("logout");
		clickElement("logout");
		clickElement("logoutconfirm");
	}
}
