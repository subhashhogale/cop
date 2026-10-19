package copilot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.InputStream;

public class base {

	protected static WebDriver driver;
	private static Properties config = new Properties();

	static {
		// load config properties
		try (InputStream in = base.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (in != null) {
				getConfig().load(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WebDriver initDriver() {
		if (driver == null) {
			String browser = getConfig().getProperty("browser", "chrome").toLowerCase();
			if ("chrome".equals(browser)) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions opts = new ChromeOptions();
				opts.addArguments("--disable-notifications");
				driver = new ChromeDriver(opts);
			} else {
				// extend for other browsers as needed
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static Properties getConfig() {
		return config;
	}

	public static void setConfig(Properties config) {
		base.config = config;
	}
}
