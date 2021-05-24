package org.com.expediaTests;


import java.util.concurrent.TimeUnit;

import org.com.expediaPageFactory.FlightsPageFactory;
import org.com.expediaPageFactory.HomePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_002_SearchForFlights {

	WebDriver driver;
	
	@BeforeClass
	public void initialize() {
		System.setProperty("webdriver.chrome.driver", ".//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.expedia.co.in/");
	}
	
	
	@Test
	public void searchFlights() throws InterruptedException {
		
		HomePageFactory homepage = new HomePageFactory(driver);
		FlightsPageFactory flightspage = homepage.selectFlightsMenu();
		boolean isFlightTypeAvailable = flightspage.selectFlightType("one-way");

		if(isFlightTypeAvailable) {
			System.out.println("This flight type is available");
			//asserthere
		}
		else {
			System.out.println("This flight type is not available");
			//Screenshot & assert false here
		}
		
		flightspage.setDepartureCity("Melbourne");
		flightspage.setArrivalCity("Delhi");
		flightspage.setDepartureDate("26");
		
		flightspage.setTravellerDetails().setNumberOfAdultTravellers(1);
		java.lang.Thread.sleep(2000);	
		flightspage.searchForAvailableFlights();
		java.lang.Thread.sleep(4000);	
	}
	
	@AfterClass
	public void cleanUp() {

		driver.quit();
	}
	
}
