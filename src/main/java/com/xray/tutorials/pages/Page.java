package com.xray.tutorials.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Page {

	protected WebDriver webDriver;

	public Page(WebDriver webDriver){
		  this.webDriver = webDriver;
	}


	By account				= By.id("account");
	By cart					= By.id("header_cart");
	String menuItem_Format	= ".//li[contains(@class, 'menu-item') and text() = '%s']";
	By searchField			= By.name("s");



	public Page goTo(String postUrl) {
		webDriver.get(postUrl);
		return new Page(webDriver);
	}

    public boolean contains(String text){
		return webDriver.getPageSource().contains(text);
	}

    public String getTitle(){
		return webDriver.getTitle(); // (title).getText();
	}
}