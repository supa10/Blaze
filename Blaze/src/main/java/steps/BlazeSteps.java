package steps;


import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import page.BlazePage;

public class BlazeSteps {

    BlazePage blazePage = new BlazePage();

//    BlazeSteps(){
//        blazePage ;
//    }

    @Given("^I am on the blaze Simple Travel Agency home page$")
    public void i_am_on_the_blaze_Simple_Travel_Agency_home_page() throws Throwable {
        this.blazePage.verifyWhetherOnHomePage();
    }

    @Then("^verify Departure City$")
    public void verify_Departure_City() throws Throwable {
        this.blazePage.verifyDepatureCity();
    }

    @Then("^verify Destination City$")
    public void verify_Destination_City() throws Throwable {
        this.blazePage.verifyDestinationCity();
    }

    @When("^I choose Departure City as \"([^\"]*)\"$")
    public void i_choose_Departure_City_as(String cityName) throws Throwable {
       this.blazePage.chooseDepartureCity(cityName);
    }

    @When("^I choose Destination City as \"([^\"]*)\"$")
    public void i_choose_Destination_City_as(String cityName) throws Throwable {
        this.blazePage.chooseDestinationCity(cityName);
    }

    @When("^I click on Find Flights$")
    public void i_click_on_Find_Flights() throws Throwable {
        this.blazePage.clickOnButton("Find flight");
    }

    @Then("^click on first choice to choose the flight$")
    public void click_on_first_choice_to_choose_the_flight() throws Throwable {
        this.blazePage.clickOnButton("first option");
    }

    @Then("^verify flight booking page is displayed$")
    public void verify_flight_booking_page_is_displayed() throws Throwable {
        this.blazePage.verifyFlightBookingPageIsDisplayed();
    }

    @Then("^select flight with the lowest price$")
    public void select_flight_with_the_lowest_price() throws Throwable {
   this.blazePage.selectLowestPriceFlight();
    }

    @When("^Fill all details to book a reservation$")
    public void fill_all_details_to_book_a_reservation(DataTable dataTable) throws Throwable {
        this.blazePage.enterReservationDetails(dataTable);
    }

    @When("^I click on Purchase Flight$")
    public void i_click_on_Purchase_Flight() throws Throwable {
        this.blazePage.clickOnButton("Purchase Flight");
        
    }

    @Then("^verify flight confirmation page is displayed$")
    public void verify_flight_confirmation_page_is_displayed() throws Throwable {
        this.blazePage.verifyFlightConfirmationPage();
        
    }
}
