package org.com.expediaTests;

import org.com.expediaUtilities.TestSupport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_001_ExpediaHomePageVerification extends TestSupport{

	@Test
	public void startTest() {
		
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
	}
	
}
