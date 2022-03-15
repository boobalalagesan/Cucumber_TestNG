Feature: Compare temperature from accuweather UI with API
  
   @ValidateWeather
   Scenario: Validate weather with UI and API
      
    Given User launch Accuweather appication and search with City
    When User selects proper city in suggestion
    Then Compare temperature from API with UI