package mrs.ci_openmrs.extentmanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import mrs.ci_openmrs.config.ConfigReader;


public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent == null) {
			extent = createInstance();
		}
		return extent;
	}
	
	public static ExtentReports createInstance() {
		String reportsDir = System.getProperty("user.dir") + "/test-output/extent-reports/";
        new File(reportsDir).mkdirs();

        // unique file per suite execution (history preserved)
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFile = reportsDir + "Regression_Test_Report_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setReportName("Open MRS Automation Testing");
        spark.config().setDocumentTitle("Open MRS Regression Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Shaik Azeem Azhar");
        extent.setSystemInfo("Project Name", "Open MRS");
        extent.setSystemInfo("Framework", "Hybrid Automation Framework + TestNG");
        extent.setSystemInfo("Base URI", ConfigReader.get("baseurl"));
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("OS Version", System.getProperty("os.version"));
        extent.setSystemInfo("OS Architecture", System.getProperty("os.arch"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Execution Mode", "Regression Suite");
        return extent;
		
	}
}
