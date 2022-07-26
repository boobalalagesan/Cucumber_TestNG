Feature: Verify UI scenarios

Background:
Given Launch Accuweather application

   @Sanity @ValidatePageTitle
   Scenario: Validate weather with UI and API
   When Verify Page title
   
   @Sanity @Smoke @FetchweatherFronUI
   Scenario: Fetch UI temperature value
   
   Given User search with City name as "Salem"
    When User selects proper city in suggestion
    Then Get temperature value from UI