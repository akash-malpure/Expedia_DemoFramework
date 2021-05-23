package org.com.expediaPageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageFactory {
	
	WebDriver driver;
	WebDriverWait wait;
		
	@FindBy(xpath="//span[text()='Stays']")
	WebElement staysMenu;
	
	@FindBy(xpath="//span[text()='Flights']")
	WebElement flightsMenu;
	
	@FindBy(xpath="//img[@alt='expedia logo']")
	WebElement expediaLogo;
	
	
	public HomePageFactory(WebDriver driver){
	
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	public FlightsPageFactory selectFlightsMenu() {
		
		flightsMenu.click();		
		return new FlightsPageFactory(driver);
	}

}
