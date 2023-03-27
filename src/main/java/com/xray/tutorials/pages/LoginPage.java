package com.xray.tutorials.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

  
    String homepageUrl = "http://robotwebdemo.onrender.com/";

 
    private By usernameElement	= By.id("username_field");
    private By passwordElement	= By.id("password_field");
    private By submitButtonElement	= By.id("login_button");

	public LoginPage(WebDriver webDriver){
		super(webDriver);
	}

    public void visit(){
        webDriver.get(homepageUrl);
    }

    public WelcomePage login(String username, String password)
    {
        /*
        usernameElement.SendKeys(username);
        passwordElement.SendKeys(password);
        submitButtonElement.Submit();
        */
        webDriver.findElement(usernameElement).sendKeys(username);
        webDriver.findElement(passwordElement).sendKeys(password);
        webDriver.findElement(submitButtonElement).submit();
        return new WelcomePage(webDriver);
    }
}
