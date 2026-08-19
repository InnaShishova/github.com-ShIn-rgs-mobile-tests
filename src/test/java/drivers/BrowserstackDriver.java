package drivers;

import config.MobileConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver {

    private static final MobileConfig config =
            ConfigFactory.create(MobileConfig.class);

    public static AndroidDriver createDriver() {
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        if (username == null || username.isBlank()) {
            throw new IllegalStateException(
                    "Environment variable BROWSERSTACK_USERNAME is not set"
            );
        }

        if (accessKey == null || accessKey.isBlank()) {
            throw new IllegalStateException(
                    "Environment variable BROWSERSTACK_ACCESS_KEY is not set"
            );
        }

        UiAutomator2Options options = new UiAutomator2Options();

        // =========================
        // Appium capabilities
        // =========================
        options.setPlatformName("Android");
        options.setDeviceName(config.device());
        options.setPlatformVersion(config.osVersion());
        options.setAutomationName("UiAutomator2");
        options.setApp(config.app());

        // =========================
        // BrowserStack capabilities
        // =========================
        Map<String, Object> bstackOptions = new HashMap<>();

        bstackOptions.put("userName", username);
        bstackOptions.put("accessKey", accessKey);

        bstackOptions.put(
                "projectName",
                "Mobile Diploma"
        );

        bstackOptions.put(
                "buildName",
                "rgs-mobile-tests"
        );

        bstackOptions.put(
                "sessionName",
                "Wikipedia Android Test"
        );

        options.setCapability(
                "bstack:options",
                bstackOptions
        );

        try {
            return new AndroidDriver(
                    new URL(
                            "https://hub.browserstack.com/wd/hub"
                    ),
                    options
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Incorrect BrowserStack URL",
                    e
            );
        }
    }
}