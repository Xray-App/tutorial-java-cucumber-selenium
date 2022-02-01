package utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.xray.tutorials.pages.*;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTests {

	private static WebDriver webDriver;
	protected static Page homePage;

	@BeforeClass
	public static void launchApplication(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, to run in Docker
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
		webDriver.get("https://robotwebdemo.herokuapp.com");
		homePage = new Page(webDriver);
	}

	@AfterClass
	public static void closeBrowser(){
		webDriver.quit();
	}

	public static WebDriver getWebDriver(){
		return webDriver;
	}

}