package tests;

import java.util.HashMap;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.BaseTest;
import common.SoftAssert;
import common.TestData;
import pages.HomePage;
import pages.SearchResultsPage;

public class ClearTripTest extends BaseTest {
	
	@Test(groups = {"Sanity", "Regression"}, dataProvider="SearchFlightsData")
	public static void SearchFlights(HashMap<String, String> data) throws InterruptedException {
		
		HomePage homePage =new  HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		waitForElementDisplayed(homePage.getRoundTrip());
		click(homePage.getRoundTrip(), "Round Trip");
		type(homePage.getFrom(), data.get("From"), "From");
		type(homePage.getTo(), data.get("To"), "To");
		type(homePage.getDepartDate(), getFutureDate(Integer.parseInt(data.get("DepartureDate"))), "Departure date");
		click(homePage.getBodyElement(), "Body element");
		type(homePage.getReturnDate(), getFutureDate(Integer.parseInt(data.get("ReturnDate"))), "Return date");
		click(homePage.getBodyElement(), "Body element");
		click(homePage.getSearchFlight(), "Search Flights");
		waitForElementDisplayed(searchResultsPage.getBook());
		SoftAssert.assertTrue(searchResultsPage.getBook().isDisplayed(), "Verify Search Flights results");	
	}
	
	@DataProvider(name = "SearchFlightsData")
	public static Iterator<Object[]> credentials() {
	        return  TestData.readTestData("ClearTrip.xml", "SearchFlights").iterator();
	 
	}

}
