package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

	
	@FindBy(xpath = "(//button[text()='Book'])[2]")
	WebElement book;
	
	public SearchResultsPage(WebDriver driver){
        PageFactory.initElements(driver, this);

    }
	
	public WebElement getBook()
	{
		return book;
	}
}
	
