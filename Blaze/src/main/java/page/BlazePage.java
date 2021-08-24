package page;

import basecode.SeleniumCode;
import cucumber.api.DataTable;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class BlazePage extends SeleniumCode {

    private By homePageText = By.xpath("/html/body/div[2]/div/h1");
    private By departureCity = By.xpath("/html/body/div[3]/form/select[1]");
    private By destinationCity = By.xpath("/html/body/div[3]/form/select[2]");
    private By findFlightBtn = By.xpath("/html/body/div[3]/form/div/input");
    private By firstOptionChooseFlight = By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input");
    private By purchaseFlight = By.xpath("/html/body/div[2]/form/div[11]/div/input");
    private By numberOfFlightOption = By.xpath("/html/body/div[2]/table/tbody/tr");
    private By inputName = By.id("inputName");
    private By address = By.id("address");
    private By city = By.id("city");
    private By state = By.id("state");
    private By zipCode = By.id("zipCode");
    private By cardType = By.id("cardType");
    private By creditCardNumber = By.id("creditCardNumber");
    private By creditCardMonth = By.id("creditCardMonth");
    private By creditCardYear = By.id("creditCardYear");
    private By nameOnCard = By.id("nameOnCard");
    private By flightConfirmationHeader = By.xpath("/html/body/div[2]/div/h1");

    public void verifyWhetherOnHomePage() {
        assertThat(GetPageTitle().trim(), equalTo("BlazeDemo"));
        assertThat(verifyText(homePageText), equalTo("Welcome to the Simple Travel Agency!"));
    }


    public void verifyDepatureCity() {

        List<String> listStr = new ArrayList<String>();
        listStr.add("Paris");
        listStr.add("Philadelphia");
        listStr.add("Boston");
        listStr.add("Portland");
        listStr.add("San Diego");
        listStr.add("Mexico City");
        listStr.add("São Paolo");

        for (int i = 0; i < getAllOptionInDropDown(departureCity).size(); i++) {
            assertThat(getAllOptionInDropDown(departureCity).get(i).getText().trim(), is(equalTo(listStr.get(i))));
        }
    }

    public void verifyDestinationCity() {

        List<String> listStr = new ArrayList<String>();
        listStr.add("Buenos Aires");
        listStr.add("Rome");
        listStr.add("London");
        listStr.add("Berlin");
        listStr.add("New York");
        listStr.add("Dublin");
        listStr.add("Cairo");

        for (int i = 0; i < getAllOptionInDropDown(destinationCity).size(); i++) {
            assertThat(getAllOptionInDropDown(destinationCity).get(i).getText().trim(), is(equalTo(listStr.get(i))));
        }
    }


    public void chooseDepartureCity(String cityName) {
        selectOptionFromDropdown(departureCity, cityName);
    }

    public void chooseDestinationCity(String cityName) {
        selectOptionFromDropdown(destinationCity, cityName);
    }

    public void clickOnButton(String buttonName) {
        switch (buttonName.toLowerCase()) {
            case "find flight":
                buttonClick(findFlightBtn);
                break;
            case "first option":
                buttonClick(firstOptionChooseFlight);
                break;
            case "purchase flight":
                buttonClick(purchaseFlight);
                break;
            default:
                throw new IllegalArgumentException("incorrect argument");
        }
    }


    public void verifyFlightBookingPageIsDisplayed() {
        assertThat(GetPageTitle().trim(), equalTo("BlazeDemo Purchase"));
        assertThat(verifyText(By.xpath("/html/body/div[2]/h2")), is(containsString("has been reserved")));
    }

    public void selectLowestPriceFlight() {

        Set<Float> setFloat = new TreeSet<>();
        int countOfFlights = getAllElementsMatching(numberOfFlightOption).size();

        for (int i = 1; i <= countOfFlights; i++) {
            setFloat.add(Float.parseFloat(verifyText(By.xpath("/html/body/div[2]/table/tbody/tr[" + i + "]/td[6]")).replace("$", "")));
        }
        Float floatValue = setFloat.iterator().next();
        for (int i = 1; i <= countOfFlights; i++) {
            if (Float.parseFloat(verifyText(By.xpath("/html/body/div[2]/table/tbody/tr[" + i + "]/td[6]")).replace("$", "")) == floatValue) {
                buttonClick(By.xpath("/html/body/div[2]/table/tbody/tr[" + i + "]/td[1]/input"));
                break;
            }
        }

    }


    public void enterReservationDetails(DataTable dataTable) {

        enterText(inputName, dataTable.raw().get(1).get(0));
        enterText(address, dataTable.raw().get(1).get(1));
        enterText(city, dataTable.raw().get(1).get(2));
        enterText(state, dataTable.raw().get(1).get(3));
        enterText(zipCode, dataTable.raw().get(1).get(4));
        selectOptionFromDropdown(cardType, dataTable.raw().get(1).get(5));
        enterText(creditCardNumber, dataTable.raw().get(1).get(6));
        enterText(creditCardMonth, dataTable.raw().get(1).get(7));
        enterText(creditCardYear, dataTable.raw().get(1).get(8));
        enterText(nameOnCard, dataTable.raw().get(1).get(9));

    }


    public void verifyFlightConfirmationPage() {
        assertThat(verifyText(flightConfirmationHeader), is(equalToIgnoringCase("Thank you for your purchase today!")));
    }
}
