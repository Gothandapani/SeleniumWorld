package common;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest extends CommonActions {
	static ResourceBundle configObject = null;;
	protected static WebDriver driver = null;
	static ExtentReports extent = null;
	protected static ExtentTest test = null;
	static String userDir = null;
	static String testDataDir = null;
	public static Map<String, String> testNgParameters = null;

	@BeforeSuite(alwaysRun = true)
	public static void getBrowser(ITestContext context) {
		testNgParameters = context.getCurrentXmlTest().getAllParameters();
		userDir = System.getProperty("user.dir").replaceAll("\\\\", "/");
		System.out.println(userDir);
		System.out.println("Executing Before Suite");
		configObject = ResourceBundle.getBundle("config");
		testDataDir = userDir + "/TestData/";
		extent = new ExtentReports(userDir + "/Results/testReport.html");
		extent.config().reportName("Google - ");
		extent.config().reportHeadline("Test Automation Report");
	}

	@BeforeMethod(alwaysRun = true)
	public static WebDriver launchBrowser(Method  method) {
		test = extent.startTest(method.getName());
		//test.log(LogStatus.INFO, method.getName() + " script execution is started");

		System.out.println("Executing Before Method");
		try {
			if (configObject.getString("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						userDir + "/Exe/geckodriver.exe");
				driver = new FirefoxDriver();
				test.log(LogStatus.PASS, "Firefox browser launched");
			} else if (configObject.getString("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						userDir + "/Exe/chromedriver.exe");
				driver = new ChromeDriver();
				test.log(LogStatus.PASS, "Chrome browser launched");
			}
		} catch(Exception e) {
			test.log(LogStatus.FAIL, "Failed to lauch browser");
		}
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		launchApplication();
		return driver;

	}


	@AfterMethod(alwaysRun = true)
	public static void closeBrowser() {
		
		System.out.println("Executing After Method");
		driver.quit();
		test.log(LogStatus.PASS, "Test case execution completed");
		extent.endTest(test);
	}
	
	public static void launchApplication() {
		try {
			driver.get(configObject.getString("url"));
			Thread.sleep(3000);
			test.log(LogStatus.PASS, "Application is opened in browser");
			
		} catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Failed to open Application in browser");
			e.printStackTrace();
		}

	}

	
	@AfterSuite(alwaysRun = true)
	public static void generateReport() {
		extent.flush();
	}
	
	public static ExtentTest getTest() {
		return test;
	}
	
	public static WebDriver getWebDriver() {
		return driver;
	}
}
