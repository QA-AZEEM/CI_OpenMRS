package mrs.ci_openmrs.util;

import com.github.javafaker.Faker;

public class TestData {

	private static Faker data;

	public static Faker getInstance() {
		if (data == null) {
			data = new Faker();
		}
		return data;
	}
	
	public static String getFirstName() {
		return getInstance().name().firstName();
	}
	
	public static String getLastName() {
		return getInstance().name().lastName();
	}
	
	public static String getEmail() {
		return getInstance().internet().emailAddress();
	}
	
	public static String getUsername() {
		return getInstance().name().username();
	}
	
	public static String getPwd() {
		return getInstance().internet().password(8, 15, true, true, true);
	}
}
