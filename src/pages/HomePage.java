package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(id = "RoundTrip")
	WebElement roundTrip;
	@FindBy(id = "OneWay")
	WebElement oneWay;
	@FindBy(id = "FromTag")
	WebElement from;
	@FindBy(id = "ToTag")
	WebElement to;
	@FindBy(id = "DepartDate")
	WebElement departDate;
	@FindBy(id = "ReturnDate")
	WebElement returnDate;
	@FindBy(id = "SearchBtn")
	WebElement searchFlight;
	@FindBy(xpath = "//body[contains(@class, 'optimisedCSS')]")
	WebElement bodyElement;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public WebElement getBodyElement() {
		return bodyElement;
	}

	public WebElement getRoundTrip() {
		return roundTrip;
	}

	public WebElement getOneWay() {
		return oneWay;
	}

	public WebElement getFrom() {
		return from;
	}

	public WebElement getTo() {
		return to;
	}

	public WebElement getDepartDate() {
		return departDate;
	}

	public WebElement getReturnDate() {
		return returnDate;
	}

	public WebElement getSearchFlight() {
		return searchFlight;
	}
}
