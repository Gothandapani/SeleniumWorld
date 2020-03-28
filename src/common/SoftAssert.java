package common;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.LogStatus;

public class SoftAssert {

	public static void assertTitle(String title) {
		if (BaseTest.getWebDriver().getTitle().contains(title)) {
			BaseTest.getTest().log(LogStatus.PASS, "'"+title + "' page is displayed");
			System.out.println("'"+title + "' page is displayed");
		} else {
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) BaseTest.getWebDriver()).getScreenshotAs(OutputType.BASE64);
			BaseTest.getTest().log(LogStatus.FAIL,
					"'"+title + " page is not displayed" + BaseTest.getTest().addScreenCapture(base64Screenshot));
			System.out.println(title + " page is not displayed");
		}
	}

	public static void assertTrue(boolean status, String desc) {
		if(status) {
			BaseTest.getTest().log(LogStatus.PASS, desc);
		} else {
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) BaseTest.getWebDriver()).getScreenshotAs(OutputType.BASE64);
			BaseTest.getTest().log(LogStatus.FAIL,
					desc + BaseTest.getTest().addScreenCapture(base64Screenshot));
		}
		
	}
	
	public static void assertFalse(boolean status, String desc) {
		if(!status) {
			BaseTest.getTest().log(LogStatus.PASS, desc);
		} else {
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) BaseTest.getWebDriver()).getScreenshotAs(OutputType.BASE64);
			BaseTest.getTest().log(LogStatus.FAIL,
					desc + BaseTest.getTest().addScreenCapture(base64Screenshot));
		}
		
	}

	public static void assertEquals(String actualText, String expectedText, String desc) {
		if(actualText.equals(expectedText)) {
			BaseTest.getTest().log(LogStatus.PASS, desc +"<br>Actual Text: "+actualText +"<br>Expected Text: "+expectedText);
		} else {
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) BaseTest.getWebDriver()).getScreenshotAs(OutputType.BASE64);
			BaseTest.getTest().log(LogStatus.FAIL,
					desc +"<br>Actual Text: "+actualText +"<br>Expected Text: "+expectedText + BaseTest.getTest().addScreenCapture(base64Screenshot));
		}
		
	}
	
	public static void assertContains(String actualText, String expectedText, String desc) {
		if(actualText.contains(expectedText)) {
			BaseTest.getTest().log(LogStatus.PASS, desc +"<br>Actual Text: "+actualText +"<br>Expected Text: "+expectedText);
		} else {
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) BaseTest.getWebDriver()).getScreenshotAs(OutputType.BASE64);
			BaseTest.getTest().log(LogStatus.FAIL,
					desc +"<br>Actual Text: "+actualText +"<br>Expected Text: "+expectedText + BaseTest.getTest().addScreenCapture(base64Screenshot));
		}
		
	}


}
