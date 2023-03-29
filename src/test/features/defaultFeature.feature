@default
                                    Feature: default
    Scenario: defaultScenario
      Given I open "home"
      When I populate "email" field with "info@m-ostrov.com"
      When I populate "Password" field with "d"
      When I click on "Submit"
