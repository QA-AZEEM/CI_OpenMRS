package mrs.ci_openmrs.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	 private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    /**
	     * Initializes WebDriver for the given browser with headless support.
	     * 
	     * @param browser  - chrome / firefox
	     * @param headless - true = run in headless mode, false = normal mode
	     */
	    public static void initBrowser(String browser, boolean headless) {
	        if (getDriver() != null) {
	            return; // Prevent reinitialization
	        }

	        WebDriver webDriver;

	        switch (browser.toLowerCase()) {

	            case "chrome":
	                WebDriverManager.chromedriver().setup();

	                ChromeOptions chromeOptions = new ChromeOptions();

	                if (headless) {
	                    chromeOptions.addArguments("--headless=new"); 
	                }

	                // Robust standard headless options
	                chromeOptions.addArguments("--no-sandbox");
	                chromeOptions.addArguments("--disable-dev-shm-usage");
	                chromeOptions.addArguments("--disable-gpu");
	                chromeOptions.addArguments("--disable-notifications");
	                chromeOptions.addArguments("--disable-popup-blocking");
	                chromeOptions.addArguments("--remote-allow-origins=*");
	                chromeOptions.addArguments("--window-size=1920,1080");

	                webDriver = new ChromeDriver(chromeOptions);
	                break;

	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();

	                FirefoxOptions firefoxOptions = new FirefoxOptions();

	                if (headless) {
	                    firefoxOptions.addArguments("-headless");
	                }

	                // Recommended settings for stability
	                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
	                firefoxOptions.addPreference("dom.push.enabled", false);
	                firefoxOptions.addArguments("--width=1920");
	                firefoxOptions.addArguments("--height=1080");

	                webDriver = new FirefoxDriver(firefoxOptions);
	                break;

	            default:
	                throw new IllegalArgumentException("Unexpected browser: " + browser);
	        }

	        driver.set(webDriver);
	    }

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static void quitBrowser() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }
}
