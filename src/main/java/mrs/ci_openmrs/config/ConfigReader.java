package mrs.ci_openmrs.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties properties = new Properties();
	private static String fileName = "config.properties";
	
	static {
		try(InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
			if(inputStream != null) {
				properties.load(inputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		String evnVal = System.getenv(key);
		if(evnVal != null) {
			return evnVal;
		}
		return properties.getProperty(key);
	}
	
}
