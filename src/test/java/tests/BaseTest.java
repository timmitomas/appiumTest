package tests;

import driver.DriverFactory;
import extensions.ScreenshotOnFailure;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ScreenshotOnFailure.class)
public class BaseTest {

    protected AppiumDriver driver;

    public AppiumDriver getDriver() {
        return driver;
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver = DriverFactory.createDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
