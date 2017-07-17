package org.testobject.appium.supertest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testobject.rest.api.appium.common.TestObjectCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class SuperTest {
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

		driver = new RemoteWebDriver(getAppiumServer(), capabilities);
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
		System.out.println(driver.getPageSource());
		byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
		Files.write(Paths.get("screenshot.png"), screenshot);
	}
}
