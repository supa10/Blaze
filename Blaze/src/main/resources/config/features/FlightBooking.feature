@Blaze @FlightBooking @Regression
Feature: Flight booking on Blaze application

  @verifyCityName
  Scenario: verify source and destination city
    Given I am on the blaze Simple Travel Agency home page
    Then verify Departure City
    And  verify Destination City

  @ChooseFirstOption
  Scenario: Book flight between two city with first option
    Given I am on the blaze Simple Travel Agency home page
    When I choose Departure City as "Boston"
    And I choose Destination City as "London"
    And I click on Find Flights
    Then click on first choice to choose the flight
    And verify flight booking page is displayed

  @lowestPrice
  Scenario: Book flight between two city with lowest price
    Given I am on the blaze Simple Travel Agency home page
    When I choose Departure City as "Boston"
    And I choose Destination City as "London"
    And I click on Find Flights
    Then select flight with the lowest price
    And verify flight booking page is displayed

  @bookFlightWithUserDetails
  Scenario: Book flight between two city with all user details
    Given I am on the blaze Simple Travel Agency home page
    When I choose Departure City as "Boston"
    And I choose Destination City as "London"
    And I click on Find Flights
    Then select flight with the lowest price
    And verify flight booking page is displayed
    When Fill all details to book a reservation
      | Name      | Address                  | City           | State | ZipCode | CardType | CreditCardNumber | Month | Year | NameOnCard |
      | Noah Emma | 3931 Philadelphia Avenue | Salt Lake City | Utah  | 84116   | Visa     | 123456789        | 12    | 2021 | Noah Emma  |
    And I click on Purchase Flight
    Then verify flight confirmation page is displayed