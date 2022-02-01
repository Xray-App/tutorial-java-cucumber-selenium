package utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.xray.tutorials.pages.*;

public class BaseTests {

	private static WebDriver webDriver;
	protected static Page homePage;

	@BeforeClass
	public static void launchApplication(){
		//setChromeDriverProperty();
		System.out.println("bla bla");
		webDriver = new ChromeDriver();
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