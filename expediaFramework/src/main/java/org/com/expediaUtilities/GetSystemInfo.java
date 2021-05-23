package org.com.expediaUtilities;

import java.util.ArrayList;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GetSystemInfo {

	public static String getOperatingSystem() {

		String nameOS = System.getProperty("os.name");
		return nameOS;
	}

	public static String getOperatingSystemVersion() {

		String versionOS = System.getProperty("os.version");
		return versionOS;
	}

	public static String getOperatingSystemArchitecture() {

		String architectureOS = System.getProperty("os.arch");
		return architectureOS;
	}
	
	public static ArrayList<String> getBrowserDetails(WebDriver driver) {
		
		ArrayList<String> browserDetails = new ArrayList<String>();
		
		Capabilities browserCap = ((RemoteWebDriver) driver).getCapabilities();
		browserDetails.add(browserCap.getBrowserName());
		browserDetails.add(browserCap.getVersion());
		
		return browserDetails;
		
	}
}
