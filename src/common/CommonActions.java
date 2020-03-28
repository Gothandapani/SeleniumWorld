package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class CommonActions {

	public static void click(WebElement element, String stepDesc) {
		try {
			waitForElementDisplayed(element);
			element.click();
			BaseTest.getTest().log(LogStatus.PASS, "Click on "+stepDesc);
		} catch(Exception ex)
		{
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) BaseTest.getWebDriver()).getScreenshotAs(OutputType.BASE64);
			BaseTest.getTest().log(LogStatus.FAIL, "Click on "+stepDesc + BaseTest.getTest().addScreenCapture(base64Screenshot));
		}
		
	}
	
	public static void type(WebElement element, String value, String stepDesc) {
		try {
			waitForElementDisplayed(element);
			element.clear();
			element.sendKeys(value);
			BaseTest.getTest().log(LogStatus.PASS, "Value '"+value+"' is entered in "+ stepDesc);
		} catch(Exception ex)
		{
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) BaseTest.getWebDriver()).getScreenshotAs(OutputType.BASE64);
			BaseTest.getTest().log(LogStatus.FAIL, "Value '"+value+"' is entered in "+ stepDesc + BaseTest.getTest().addScreenCapture(base64Screenshot));
		}
		
	}
	
	public static void waitForElementDisplayed(WebElement element) throws InterruptedException {
		BaseTest.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int timeout = Integer.parseInt(BaseTest.configObject.getString("timeout")); 
		int pollingEvery = 5;
		int i=0;
		boolean isElementFound = false;
		do {
			try {
				element.isDisplayed();
				isElementFound = true;
				break;
			} catch (NoSuchElementException ex) {
			} catch (WebDriverException ex) {
				
			}
			Thread.sleep(5000);
			i++;
		} while(i<=(timeout/pollingEvery) && !isElementFound);	
		BaseTest.getWebDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public static void waitForElementNotDisplayed(WebElement element) throws InterruptedException {
		BaseTest.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int timeout = Integer.parseInt(BaseTest.configObject.getString("timeout")); 
		int pollingEvery = 5;
		int i=0;
		boolean isElementFound = true;
		do {
			try {
				element.isDisplayed();
			} catch (NoSuchElementException ex) {
				isElementFound = false;
				break;
			} catch (WebDriverException ex) {
				
			}
			Thread.sleep(5000);
			i++;
		} while(i<=(timeout/pollingEvery) && isElementFound);	
		BaseTest.getWebDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public static void waitForElementEnabled(WebElement element) throws InterruptedException {
		BaseTest.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int timeout = Integer.parseInt(BaseTest.configObject.getString("timeout")); 
		int pollingEvery = 5;
		int i=0;
		boolean isElementFound = false;
		do {
			try {
				element.isEnabled();
				isElementFound = true;
				break;
			} catch (NoSuchElementException ex) {
				
			} catch (WebDriverException ex) {
				
			}
			Thread.sleep(5000);
			i++;
		} while(i<=(timeout/pollingEvery) && !isElementFound);	
		BaseTest.getWebDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public static void verifyElementPresent(WebElement element) throws InterruptedException {		
			try {
				element.isDisplayed();
				BaseTest.getTest().log(LogStatus.PASS, "Element is displayed");
			} catch (NoSuchElementException ex) {
				BaseTest.getTest().log(LogStatus.FAIL, "Element is not displayed");
			} 			
	}
	
	public static void verifyElementNotPresent(WebElement element) throws InterruptedException {		
		try {
			element.isDisplayed();
			BaseTest.getTest().log(LogStatus.FAIL, "Element is displayed");
		} catch (NoSuchElementException ex) {
			BaseTest.getTest().log(LogStatus.PASS, "Element is not displayed");
		} 			
}
	public static String getFutureDate(int day) {
		String pattern = "E, dd MMM, yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); 
		c.add(Calendar.DATE, day);		
		return simpleDateFormat.format(c.getTime());
	}
}
