Feature: Compare temperature from accuweather UI with API

Background:
Given Launch Accuweather application
  
   
   @Smoke @ValidateWeather
   Scenario: Validate weather with UI and API
      
    Given User search with City name as "Namakkal"
    When User selects proper city in suggestion
    Then Get temperature value from UI
    And Get temperature value from API
    Then Compare temperature from API with UI
    
    @Regression @ValidateWeatherForMultipleCity
    
    Scenario Outline: Validate temperature for multiple cities
    Given User search with City name as "<city>"
    When User selects proper city in suggestion
    Then Get temperature value from UI
    And Get temperature value from API
    Then Compare temperature from API with UI

    Examples:
    |city|
    |Salem|
    |Namakkal|
    |Karur|
    |Chennai|
    