package com.xray.tutorials.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends Page {

  
    String Url = "http://robotwebdemo.herokuapp.com/welcome.html";

    private By logoutElement	= By.linkText("logout");

	public WelcomePage(WebDriver webDriver){
		super(webDriver);
	}

    public void visit(){
        webDriver.get(Url);
    }

    public LoginPage logout()
    {
        webDriver.findElement(logoutElement).click();
        return new LoginPage(webDriver);
    }
}
