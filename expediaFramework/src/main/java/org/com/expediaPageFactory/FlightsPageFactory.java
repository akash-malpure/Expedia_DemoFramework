package org.com.expediaPageFactory;


import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//div[@title='Leaving from']//ul[@class='uitk-typeahead-results no-bullet']/li//strong")
	List<WebElement> autoSuggestDepartureCity;
	
	@FindBy(xpath="//div[@title='Going to']//ul[@class='uitk-typeahead-results no-bullet']/li//strong")
	List<WebElement> autoSuggestArrivalCity;
	
	@FindBy(id="d1-btn")
	WebElement departureDateButton;
	
	@FindBy(xpath="//div[@class='uitk-date-picker-month'][position()=1]//button[not(contains(@class,'is-disabled'))]")
	List<WebElement> availableDates;
	
	@FindBy(xpath="//span[text()='Done']")
	WebElement datePickerDone;

	@FindBy(xpath="//button[text()='Search']")
	WebElement searchButton;

	@FindBy(xpath="//select[@id='listings-sort']")
	WebElement sortingElement;
	
	@FindBy(xpath="//ul[@data-test-id='listings']/li")
	List<WebElement> allAvaialbleFlights;

	@FindBy(xpath="//div[@id='adaptive-menu']")
	WebElement setNumberOfTravellersDiv;

	@FindBy(xpath="//div[contains(@class,'adultStepInput')]//button[position()=2]")
	WebElement addAdultTravellers;
	
	@FindBy(xpath="//div[contains(@class,'guestsDoneBtn')]//button[@data-testid='guests-done-button']")
	WebElement addTravellersDoneButton;
	
	@FindBy(xpath="//div[@class=\"uitk-flex uitk-flex-justify-content-space-between uitk-date-picker-menu-pagination-container\"]//button[2]")
	WebElement nextMonth;
	
	@FindBy(xpath="//div[@class=\"uitk-date-picker-menu-months-container\"]//div[1]//h2")
	WebElement currentMonthDisplayed;
	
	
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

		for(WebElement selectCity : autoSuggestDepartureCity) {

			String autoSuggestedCity = selectCity.getText();

			if(autoSuggestedCity.contains(" ")) {
				autoSuggestedCity = autoSuggestedCity.substring(0, autoSuggestedCity.indexOf(" "));
				System.out.println(autoSuggestedCity);
			}

			if(autoSuggestedCity.equalsIgnoreCase(cityName)) {
				selectCity.click();
				break;
			}
		}
	}
	
	public void setArrivalCity(String cityName) {
		
		goingToButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(enterArrivalCityName));
		enterArrivalCityName.sendKeys(cityName);
		
		for(WebElement selectCity : autoSuggestArrivalCity) {

			String autoSuggestedCity = selectCity.getText();

			if(autoSuggestedCity.contains(" ")) {
				autoSuggestedCity = autoSuggestedCity.substring(0, autoSuggestedCity.indexOf(" "));
				System.out.println(autoSuggestedCity);
			}

			if(autoSuggestedCity.equalsIgnoreCase(cityName)) {
				selectCity.click();
				break;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setDepartureDate(int dd, int mm, int yyyy) {

	
		
		Date date = new Date();
		int currentMonth = date.getMonth() + 1;
		int currentYear = date.getYear() + 1900;
		String dateProvided = String.valueOf(dd);
		int monthProvided = mm;
		int yearProvided = yyyy;
		
		departureDateButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(availableDates));
		
		System.out.println(dateProvided + " " +currentMonth+ " " +currentYear);

		if((currentYear==yyyy && currentMonth<=mm) || ((currentYear+1)==yyyy && (currentMonth-2)>=mm)  ) {
			
//			System.out.println("setDepartureDate passed if statement");
			setMonthAndYear(monthProvided,yearProvided);
//			System.out.println("Month and year successfully set");
			wait.until(ExpectedConditions.visibilityOfAllElements(availableDates));
		
			for(WebElement currentDate : availableDates) {

//				System.out.println(currentDate.getAttribute("data-day"));

				if(currentDate.getAttribute("data-day").equals(dateProvided)) {
					currentDate.click();
					break;
				}	
			}
			datePickerDone.click();
		}
		else {
			System.out.println("Please set correct month & year of departure. Month of departure cannot be more than 11 months of current date.");
		}
	}
	
	private void setMonthAndYear(int mm , int yyyy) {
		
		DateFormatSymbols dateFormatSymbol = new DateFormatSymbols();
		String[] allMonths =  dateFormatSymbol.getMonths();
		
		while(true) {
			
			try {
				
//				System.out.println("//h2[text()='"+allMonths[mm-1] + " " + yyyy +"']");
				driver.findElement(By.xpath("//div[@class='uitk-date-picker-month'][position()=1]//h2[text()='"+ allMonths[mm-1] + " " + yyyy +"']"));
//				System.out.println("Month set to "+ allMonths[mm-1] + " and year set to " + yyyy );
				break;
			}
			catch(Exception e) {
				
				System.out.println("Current month is : " + currentMonthDisplayed.getText());
				nextMonth.click();
			}	
		}		
	}

	public FlightDetailsPage searchForAvailableFlights() {

		searchButton.click();
		wait.until(ExpectedConditions.visibilityOf(sortingElement));
		
		return new FlightDetailsPage(driver);
	}
	
	
	public FlightsPageFactory setTravellerDetails() {
		
		setNumberOfTravellersDiv.click();
		return this;
	}
	
	public void setNumberOfAdultTravellers(int number) throws InterruptedException {
				
		for(int i=2; i<=number; i++){
			
				addAdultTravellers.click();
				java.lang.Thread.sleep(200);
				//System.out.println("Added traveller "+i);
			}
		
		addTravellersDoneButton.click();
	}

}