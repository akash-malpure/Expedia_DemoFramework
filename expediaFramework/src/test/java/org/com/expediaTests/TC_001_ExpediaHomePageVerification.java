package org.com.expediaTests;

import org.com.expediaUtilities.TestSupport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_001_ExpediaHomePageVerification extends TestSupport{

	@Test
	public void startTest() {

		String title = "Expedia Travel: Vacations, Cheap Flights, Airline Tickets & Airfares";
		if(title.equals(driver.getTitle())) {
			logger.info("Homepage title verified");	
			Assert.assertTrue(true);
		}
		else {
			logger.info("Homepage title verified");	
			Assert.assertTrue(false);
		}

	}

}
