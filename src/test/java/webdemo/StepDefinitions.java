package webdemo;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.BaseTests;

import java.net.MalformedURLException;

import com.xray.tutorials.pages.LoginPage;
import com.xray.tutorials.pages.Page;
import com.xray.tutorials.pages.WelcomePage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {
    private WebDriver webDriver;
    private WebDriverWait wait;

    private Page currentPage;

    @Given("user is on the welcome page")
    public void user_is_on_the_welcome_page() {
        WelcomePage welcomePage = new WelcomePage(webDriver);
        welcomePage.visit();
        this.currentPage = welcomePage;
    }

    @When("user chooses to logout")
    public void user_chooses_to_logout() {
        this.currentPage = ((WelcomePage)this.currentPage).logout();
    }

    @Then("login page should be open")
    public void login_page_should_be_open() {
        Assert.assertEquals(this.currentPage.getTitle(), "Login Page");
        Assert.assertTrue(this.currentPage.contains("Login Page"));
    }


    @Given("browser is opened to login page")
    public void browser_is_opened_to_login_page() {
        //WebDriver wd = BaseTests.getWebDriver();
        //System.out.println(wd);
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.visit();
        this.currentPage = loginPage;
    }

    @When("user {string} logs in with password {string}")
    public void user_logs_in_with_password(String username, String password) {
        this.currentPage = ((LoginPage)this.currentPage).login(username, password);
    }

    @Then("error page should be open")
    public void error_page_should_be_open() {
        Assert.assertEquals(this.currentPage.getTitle(), "Error Page");
        Assert.assertTrue(this.currentPage.contains("Login failed"));
    }

    @Then("welcome page should be open")
    public void welcome_page_should_be_open() {
        Assert.assertEquals(this.currentPage.getTitle(), "Welcome Page");
        Assert.assertTrue(this.currentPage.contains("Login succeeded"));
    }

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        //ChromeOptions caps = new ChromeOptions();

		this.webDriver = new ChromeDriver();
		//webDriver.get("https://robotwebdemo.herokuapp.com");
		//homePage = new Page(webDriver);
        //wait = new WebDriverWait(webDriver, 10);
    }

    @After
    public void tearDown(Scenario scenario){
        //System.out.println("status: "+scenario.getStatus());
        if (scenario.isFailed()) {
            byte[] data = ((RemoteWebDriver) this.webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", "failure");  
        }
        webDriver.quit();
    }
}
