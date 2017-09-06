Feature: Displaying result
Scenario: verify the calculator is displayed
Given: I'm on google search page
And I input the key word "Calculator"
And I navigate to calculator
And I test the calculator throgh excelsheet 
Then I verify the result displayed on the calculaor matches expected result
