package mrs.ci_openmrs;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import mrs.ci_openmrs.config.ConfigReader;
import mrs.ci_openmrs.driverfactory.DriverFactory;

public class BaseClass {
	
	protected WebDriver driver;
	
	@Parameters("browser")
	@BeforeMethod
    public void SetUp(@Optional("chrome") String browser) {
       DriverFactory.initBrowser(browser, true);
       driver = DriverFactory.getDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       driver.get(ConfigReader.get("baseurl"));
    }
	
	@AfterMethod
	public void teardown() {
		DriverFactory.quitBrowser();
	}
}
