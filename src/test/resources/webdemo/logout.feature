@REQ_XT-6
Feature: As a user, I can logout the application

	Scenario: Valid Logout
		Given user is on the welcome page
		When user chooses to logout
		Then login page should be open
