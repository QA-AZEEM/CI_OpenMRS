package mrs.ci_openmrs.testcase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mrs.ci_openmrs.BaseClass;
import mrs.ci_openmrs.config.ConfigReader;
import mrs.ci_openmrs.itestlistners.ExtentTestListners;
import mrs.ci_openmrs.page.LoginPage;
import mrs.ci_openmrs.util.TestData;

public class AdminLoginTC extends BaseClass {
	
	@DataProvider(name = "loginCredentials")
	public Object[][] loginData() {
		return new Object[][] {
			{ConfigReader.get("open_mrs_username"),ConfigReader.get("open_mrs_pwd")}
		};
	}
	
	@DataProvider(name = "invalidCredential")
	public Object[][] invalidloginData() {
		return new Object[][] {
			{TestData.getUsername(),TestData.getPwd()}
		};
	}
	
	
	@Test(dataProvider = "loginCredentials")
	public void inpatientLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Login Test Case");
		try {
			log.loginAsInpatient(username, pwd);
			Assert.assertTrue(log.isLogoutDisplayed(),"LogoutButton is not displayed");
		} catch (Exception e) {
			Assert.fail("Admin login test case failed", e);
		}
	}
	
	@Test(dataProvider = "loginCredentials")
	public void isolationWardLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Login Test Case");
		try {
			log.loginAsIsolation(username, pwd);
			Assert.assertTrue(log.isLogoutDisplayed(),"LogoutButton is not displayed");
		} catch (Exception e) {
			Assert.fail("Admin login test case failed", e);
		}
	}
	
	@Test(dataProvider = "loginCredentials")
	public void laboratoryLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Login Test Case");
		try {
			log.loginAsLaboratory(username, pwd);
			Assert.assertTrue(log.isLogoutDisplayed(),"LogoutButton is not displayed");
		} catch (Exception e) {
			Assert.fail("Admin login test case failed", e);
		}
	}
	
	@Test(dataProvider = "loginCredentials")
	public void outPatietClinicLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Login Test Case");
		try {
			log.loginAsOutPatientClinic(username, pwd);
			Assert.assertTrue(log.isLogoutDisplayed(),"LogoutButton is not displayed");
		} catch (Exception e) {
			Assert.fail("Admin login test case failed", e);
		}
	}
	
	@Test(dataProvider = "loginCredentials")
	public void pharmacyLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Login Test Case");
		try {
			log.loginAsPharmacy(username, pwd);
			Assert.assertTrue(log.isLogoutDisplayed(),"LogoutButton is not displayed");
		} catch (Exception e) {
			Assert.fail("Admin login test case failed", e);
		}
	}
	
	@Test(dataProvider = "loginCredentials")
	public void registrationDeskLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Login Test Case");
		try {
			log.loginAsRegistrationDesk(username, pwd);
			Assert.assertTrue(log.isLogoutDisplayed(),"LogoutButton is not displayed");
		} catch (Exception e) {
			Assert.fail("Admin login test case failed", e);
		}
	}
	
	@Test(dataProvider = "invalidCredential")
	public void invalidLogin(String username, String pwd) {
		LoginPage log = new LoginPage(driver);
		ExtentTestListners.getTest().info("Invalid Login Test Case");
		try {
			log.loginAsInpatient(username, pwd);
			Assert.assertTrue(log.isErrorMessageDisplayed(), "Error Message is not displayed");
		} catch (Exception e) {
			Assert.fail("Unexpected invalid login test execute", e);
		}
	}
}
