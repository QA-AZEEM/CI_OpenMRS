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
	private WebElement locationField;

	@FindBy(xpath = "//input[@id='loginButton']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	private WebElement logoutButton;

	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void enterPassword(String pwd) {
		passwordField.sendKeys(pwd);
	}

	public void selectLocation() {
		locationField.click();
	}

	public void clickLogin() {
		loginButton.click();
	}
	
	public void adminLogin(String username, String pwd) {
		enterUsername(username);
		enterPassword(pwd);
		selectLocation();
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
}
