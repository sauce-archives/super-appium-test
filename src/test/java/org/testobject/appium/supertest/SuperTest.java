package org.testobject.appium.supertest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testobject.rest.api.appium.common.TestObjectCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class SuperTest {
	private static final Logger log = LoggerFactory.getLogger(SuperTest.class);

	private DesiredCapabilities capabilities = new DesiredCapabilities();
	private RemoteWebDriver driver;

	@Before
    public void setUp() {
		setOptionalCapability(TestObjectCapabilities.TESTOBJECT_DEVICE);
		setOptionalCapability("TESTOBJECT_PLATFORM_NAME");
		setOptionalCapability("TESTOBJECT_PLATFORM_VERSION");
		setOptionalCapability("TESTOBJECT_TABLET_ONLY");
		setOptionalCapability("TESTOBJECT_PHONE_ONLY");
		setOptionalCapability("TESTOBJECT_POOL_ID");
		setOptionalCapability(TestObjectCapabilities.TESTOBJECT_TEST_NAME);
		setOptionalCapability(TestObjectCapabilities.TESTOBJECT_APPIUM_VERSION);
		setOptionalCapability(TestObjectCapabilities.TESTOBJECT_CACHE_DEVICE);
		setOptionalCapability(TestObjectCapabilities.TESTOBJECT_APP_ID);
		setOptionalCapability(TestObjectCapabilities.TESTOBJECT_SESSION_CREATION_RETRY);
		setOptionalCapability(TestObjectCapabilities.TESTOBJECT_SESSION_CREATION_TIMEOUT);

		setRequiredCapability(TestObjectCapabilities.TESTOBJECT_API_KEY);

		log.info("Initializing driver with DesiredCapabilities:\n" + capabilities);
		driver = new RemoteWebDriver(getAppiumServer(), capabilities);
		log.info("Driver initialized.");
		log.info(capabilities.getCapability(TestObjectCapabilities.TESTOBJECT_TEST_LIVE_VIEW_URL).toString());
		log.info(capabilities.getCapability(TestObjectCapabilities.TESTOBJECT_TEST_REPORT_URL).toString());
	}

	private void setRequiredCapability(String var) {
		String data = System.getenv(var.toUpperCase());
		if (data == null || data.isEmpty()) {
			throw new RuntimeException("Missing required environment variable " + var);
		} else {
			capabilities.setCapability(var, data);
		}
	}

	private URL getAppiumServer() {
		String url = System.getenv("APPIUM_URL");
		if (url == null || url.isEmpty()) {
			throw new RuntimeException("Missing required environment variable APPIUM_URL");
		} else {
			try {
				return new URL(url);
			} catch (MalformedURLException e) {
				throw new RuntimeException("Invalid URL: " + url, e);
			}
		}
	}

	private void setOptionalCapability(String var) {
		Optional.ofNullable(System.getenv(var.toUpperCase()))
				.filter(env -> !env.isEmpty())
				.ifPresent(data -> capabilities.setCapability(var, data));
	}

	@Test
    public void superTest() throws IOException {
		log.info(driver.getPageSource());
		byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
		Path screenshotPath = Paths.get("screenshot.png");
		Files.write(screenshotPath, screenshot);
		log.info("Screenshot saved to " + screenshotPath);
	}

	@After
	public void tearDown() {
		log.info("Test complete.");
		if (driver != null) {
			driver.quit();
			log.info("Ended session.");
		}
	}
}
