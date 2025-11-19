package mrs.ci_openmrs.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtil {
	
	public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.out.println("Driver is null. Cannot take screenshot for " + testName);
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";

        // Create directory if it doesn't exist
        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                System.out.println("Failed to create screenshot directory: " + screenshotDir);
            }
        }

        String filePath = screenshotDir + testName + "_" + timestamp + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(screenshot.toPath(), new File(filePath).toPath());
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }

    // Take screenshot and return Base64 string
    public static String takeScreenshotBase64(WebDriver driver) {
        if (driver == null) {
            System.out.println("Driver is null. Cannot take screenshot in Base64.");
            return null;
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
	
	
	
}
