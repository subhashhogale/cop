package copilot.hooks;

import copilot.base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void beforeScenario() {
        WebDriver driver = base.initDriver();
        String url = base.getConfig().getProperty("base.url");
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        }
    }

    @After
    public void afterScenario() {
        base.quitDriver();
    }
}
