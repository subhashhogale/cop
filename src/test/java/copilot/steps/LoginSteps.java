package copilot.steps;

import copilot.base;
import copilot.pages.NaukriLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private WebDriver driver = base.initDriver();
    private NaukriLoginPage loginPage = new NaukriLoginPage(driver);

    @Given("user opens the Naukri login page")
    public void openLoginPage() {
        // navigation is handled in Hooks but keep a fallback
        String url = base.getConfig().getProperty("base.url");
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        }
    }

    @When("user logs in with valid credentials")
    public void loginWithValid() {
        String user = base.getConfig().getProperty("username", "");
        String pass = base.getConfig().getProperty("password", "");
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.clickLogin();
    }

    @When("user logs in with invalid credentials")
    public void loginWithInvalid() {
        loginPage.enterUsername("invalid@example.com");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();
    }

    @Then("user should see recommended jobs page or dashboard")
    public void verifyRecommendedJobs() {
        // simple verification: check that URL contains recommendedjobs or user redirected
        String current = driver.getCurrentUrl();
        Assert.assertTrue("Expected recommended jobs or dashboard", current.contains("recommendedjobs") || current.contains("dashboard") || current.contains("mnjuser"));
    }

    @Then("user should see an error message")
    public void verifyError() {
        Assert.assertTrue("Expected an error message displayed", loginPage.isErrorDisplayed());
    }
}
