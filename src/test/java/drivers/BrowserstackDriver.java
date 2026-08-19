package drivers;

import config.MobileConfig;
import io.appium.java_client.android.AndroidDriver;
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

        String username =
                System.getenv("BROWSERSTACK_USERNAME");

        String accessKey =
                System.getenv("BROWSERSTACK_ACCESS_KEY");

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

        MutableCapabilities capabilities =
                new MutableCapabilities();

        Map<String, Object> bstackOptions =
                new HashMap<>();

        bstackOptions.put(
                "userName",
                username
        );

        bstackOptions.put(
                "accessKey",
                accessKey
        );

        bstackOptions.put(
                "deviceName",
                config.device()
        );

        bstackOptions.put(
                "osVersion",
                config.osVersion()
        );

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

        capabilities.setCapability(
                "bstack:options",
                bstackOptions
        );

        capabilities.setCapability(
                "platformName",
                "Android"
        );

        capabilities.setCapability(
                "appium:app",
                config.app()
        );

        capabilities.setCapability(
                "appium:automationName",
                "UiAutomator2"
        );

        try {
            return new AndroidDriver(
                    new URL(
                            "https://hub.browserstack.com/wd/hub"
                    ),
                    capabilities
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Incorrect BrowserStack URL",
                    e
            );
        }
    }
}