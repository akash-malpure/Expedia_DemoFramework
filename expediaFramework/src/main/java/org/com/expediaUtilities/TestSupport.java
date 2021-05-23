package org.com.expediaUtilities;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestSupport {

	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void initialize(String browserLevel) {
		
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger("Log4j Logger");
		
		if(browserLevel.equalsIgnoreCase("Chrome")) {
			
			System.setProperty("webdriver.chrome.driver", ".//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}
		else if(browserLevel.equalsIgnoreCase("Firefox")) {
			
			System.setProperty("webdriver.gecko.driver", ".//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}
		else if(browserLevel.equalsIgnoreCase("ie")) {
			
			System.setProperty("webdriver.ie.driver", ".//Drivers//IEDriverServer.exe");
			
			DesiredCapabilities desiredcap = new DesiredCapabilities().internetExplorer();
			desiredcap.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(desiredcap);
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.expedia.co.in/");
	}
	
	@AfterClass
	public void cleanUp() {

		driver.quit();
	}
	
	public void captureScreenshot(WebDriver driver, String tcName) {
		
		
		
	}
}
