package mrs.ci_openmrs.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@FindBy(xpath = "//input[@id='username']")
	private WebElement usernameField;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//li[@id='Inpatient Ward']")
	private WebElement inpatientField;
	
	@FindBy(xpath = "//li[@id='Isolation Ward']")
	private WebElement isolationField;
	
	@FindBy(xpath = "//li[@id='Laboratory']")
	private WebElement laboratoryField;
	
	@FindBy(xpath = "//li[@id='Outpatient Clinic']")
	private WebElement outPatientClinicField;
	
	@FindBy(xpath = "//li[@id='Pharmacy']")
	private WebElement pharmacyField;
	
	@FindBy(xpath = "//li[@id='Registration Desk']")
	private WebElement registrationDeskField;

	@FindBy(xpath = "//input[@id='loginButton']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//div[@id='error-message']")
	private WebElement errorMessage;

	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void enterPassword(String pwd) {
		passwordField.sendKeys(pwd);
	}

	public void selectInpatientLocation() {
		inpatientField.click();
	}
	
	public void selectIsolationLocation() {
		isolationField.click();
	}
	
	public void selectLaboratoryLocation() {
		laboratoryField.click();
	}
	
	public void selectOutPatientClinicLocation() {
		outPatientClinicField.click();
	}
	
	public void selectPharmacyLocation() {
		pharmacyField.click();
	}
	
	public void selectRegistrationDeskLocation() {
		registrationDeskField.click();
	}

	public void clickLogin() {
		loginButton.click();
	}
	
	public void loginAsInpatient(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		selectInpatientLocation();
		clickLogin();
	}
	
	public void loginAsIsolation(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		selectIsolationLocation();
		clickLogin();
	}
	
	public void loginAsLaboratory(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		selectLaboratoryLocation();
		clickLogin();
	}
	
	public void loginAsOutPatientClinic(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		selectOutPatientClinicLocation();
		clickLogin();
	}
	
	public void loginAsPharmacy(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		selectPharmacyLocation();
		clickLogin();
	}
	
	public void loginAsRegistrationDesk(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		selectRegistrationDeskLocation();
		clickLogin();
	}
	
	public boolean isLogoutDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(logoutButton));
			return logoutButton.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isErrorMessageDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(errorMessage));
			return errorMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
