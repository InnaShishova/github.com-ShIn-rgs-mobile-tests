package tests;

import drivers.BrowserstackDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.Attach;

public class TestBase {

    protected AndroidDriver driver;

    @BeforeEach
    void setUp() {
        driver = BrowserstackDriver.createDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            Attach.screenshot(driver);
            Attach.pageSource(driver);
            driver.quit();
        }
    }
}