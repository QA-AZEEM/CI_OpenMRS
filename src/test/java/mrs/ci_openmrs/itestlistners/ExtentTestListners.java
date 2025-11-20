package mrs.ci_openmrs.itestlistners;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import mrs.ci_openmrs.driverfactory.DriverFactory;
import mrs.ci_openmrs.extentmanager.ExtentManager;
import mrs.ci_openmrs.util.ScreenshotUtil;

public class ExtentTestListners implements ITestListener, ISuiteListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	@Override
	public void onStart(ISuite suite) {

		System.out.println("Suite started " + suite.getName());
		// extent = ExtentManager.createInstance();
	}

	@Override
	public void onFinish(ISuite suite) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		testThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testThread.get().log(Status.PASS, "Test Passed");
		WebDriver driver = DriverFactory.getDriver();

		// Save screenshot to file
		String filePath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
		if (filePath != null) {
			testThread.get().addScreenCaptureFromPath(filePath, "Screenshot on Success");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testThread.get().log(Status.FAIL, result.getThrowable());
		WebDriver driver = DriverFactory.getDriver();

		// Save screenshot to file
		String filePath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
		if (filePath != null) {
			testThread.get().addScreenCaptureFromPath(filePath, "Screenshot on Failure");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testThread.get().log(Status.SKIP, "Test Skipped");
	}

	public static ExtentTest getTest() {
		return testThread.get();
	}

}
