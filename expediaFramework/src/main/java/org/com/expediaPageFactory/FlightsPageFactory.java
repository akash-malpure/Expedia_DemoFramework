package org.com.expediaPageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsPageFactory {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//div[@class='uitk-tabs-inner-container']/li")
	List<WebElement> flightTypes;
	
	@FindBy(xpath="//span[text()='Leaving from']/..")
	WebElement leavingFromButton;
	
	@FindBy(xpath="	//span[text()='Going to']/..")
	WebElement goingToButton;

	@FindBy(xpath="//input[@placeholder = 'Where are you leaving from?']")
	WebElement enterDepartureCityName;
	
	@FindBy(xpath="//input[@placeholder = 'Where are you going to?']")
	WebElement enterArrivalCityName;
	
	@FindBy(xpath="//ul[@class='uitk-typeahead-results no-bullet']/li//strong")
	List<WebElement> autoSuggestCity;
	
	public FlightsPageFactory(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		
		wait.until(ExpectedConditions.visibilityOfAllElements(flightTypes));
	}
	
	public boolean selectFlightType(String userInput) {
		
		for(WebElement flightType : flightTypes){
			
			if(userInput.equalsIgnoreCase(flightType.getText())) {
			
				flightType.click();
				return true;
			}		
		}      
		return false;
	} 
	
	public void setDepartureCity(String cityName) {

		leavingFromButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(enterDepartureCityName));
		enterDepartureCityName.sendKeys(cityName);

		for(WebElement selectCity : autoSuggestCity) {

			String autoSuggestedCity = selectCity.getText();

			if(autoSuggestedCity.contains(" ")) {
				autoSuggestedCity = autoSuggestedCity.substring(0, autoSuggestedCity.indexOf(" "));
				System.out.println(autoSuggestedCity);
			}

			if(autoSuggestedCity.equalsIgnoreCase(cityName)) {
				selectCity.click();
			}
		}
	}
}
