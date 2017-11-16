@Gmail
Feature: Upload file
Scenario: Verify user information
Given I'm on gmail home page
Scenario Outline:
And I enter valid "<email>" email
Then I enter valid "<password>" password
When I click on the login button
Then the user name should be "<name>" name
Examples:
|name               |email                      |password           |
|Darya Vlassova     |vdvgreat@gmail.com         |january17011930    |

Scenario: Verify file uploaded successfully
Then I click on the napisat button
And I enter addressee "orlovskiy.diman@gmail.com"
And I click on upload file
And I upload file
And i click on send button

