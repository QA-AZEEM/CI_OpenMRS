package mrs.ci_openmrs.testcase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mrs.ci_openmrs.BaseClass;
import mrs.ci_openmrs.config.ConfigReader;
import mrs.ci_openmrs.itestlistners.ExtentTestListners;
import mrs.ci_openmrs.page.LoginPage;

public class AdminLoginTC extends BaseClass {
	
	@DataProvider(name = "loginCredentials")
	public Object[][] loginData() {
		return new Object[][] {
			{ConfigReader.get("open_mrs_username"),ConfigReader.get("open_mrs_pwd")}
		};
	}
	
	
	@Test(dataProvider = "loginCredentials")
	public void adminValidLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Login Test Case");
		try {
			log.adminLogin(username, pwd);
			Assert.assertTrue(log.isLogoutDisplayed(),"LogoutButton is not displayed");
		} catch (Exception e) {
			Assert.fail("Admin login test case failed", e);
		}
	}
}
