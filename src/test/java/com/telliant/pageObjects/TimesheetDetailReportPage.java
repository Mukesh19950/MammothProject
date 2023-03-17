package com.telliant.pageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telliant.core.web.WebDriverRoot;

public class TimesheetDetailReportPage extends WebDriverRoot {

	public void NavigateToTimesheetDetailReportPage() {
		clickElement("sideMenuBtn_HP");
		scrollToElement("timesheetDeatilReportSideNavigationMenu_TDR");
		clickElement("timesheetDeatilReportSideNavigationMenu_TDR");
	}

	public void filterByDropdwon_LastWeek() throws InterruptedException, ParseException {

		Thread.sleep(3000);
			
		WebElement To_getRows = driver.findElement(By.xpath("//*[@class='table table-striped table-bordered table-hover']/tbody"));
		
		Thread.sleep(3000);
		List<WebElement> rows = To_getRows.findElements(By.tagName("tr"));
		int rws_cnt = rows.size();
		System.out.println(rws_cnt);

		for (int i = 0; i < rws_cnt; i++) {

			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			String C = cols.get(3).getText();
			System.out.println("The cell value is: " + C);
			
			
//Convert Date string to Date format
			Date dateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(C);
			System.out.println(dateFormat);
			
			
//Code to get last week date range
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int i1 = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
			c.add(Calendar.DATE, -i1 - 7);
			Date start = c.getTime();
			c.add(Calendar.DATE, 6);
			Date end = c.getTime();
			System.out.println(start + " - " + end);

//Code to validate data in grid is with the LastWeek range.
			
			if (dateFormat.compareTo(start) >= 0 && dateFormat.compareTo(end) <= 0) {
				System.out.println("Timecard's are with in the last week range");
			} else {
				System.out.println("TDR for last week filter fails");
			}

		}
	}
	
	//Code to get last month range( need to write logic to remove jan 01)
	public void lastMonth() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.add(Calendar.MONTH,-1);
		Date start = c.getTime();
		System.out.println(start);	
		
		c.add(Calendar.MONTH, 1);
		Date end = c.getTime();
		System.out.println(start + " - " + end);
		
	}
	//code for current week ( need to implement logic for current week (sat))
	public void CurrentWeek() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int i1 = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();	
		c.add(Calendar.DATE,-i1 -1);
		System.out.println(c);
		Date start = c.getTime();
		System.out.println(start);	
		c.add(Calendar.DATE, 7);
		Date end = c.getTime();
		System.out.println(start + " - " + end);
		
	}
	
	public void FilterByDropdown() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//i[@Class='anticon anticon-down ant-select-arrow-icon']//*")).click();
		WebElement Filteroptions = driver.findElement(By.xpath("//ul[@role='listbox']"));
		List<WebElement> Filteroptionlist =Filteroptions.findElements(By.tagName("li"));
		{	
			for(int J = 0; J<=Filteroptionlist.size()-1; J++) {
		         if(Filteroptionlist.get(J).getText().contains("Current Month")) {
               
				 Filteroptionlist.get(J).click();
              break;
          }
          else {
        	  
          	System.out.println("Filter By fails");
          }
	}
}
}}

