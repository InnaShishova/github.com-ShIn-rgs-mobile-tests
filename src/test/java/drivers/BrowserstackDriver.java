package drivers;

import config.MobileConfigReader;
import config.RemoteMobileConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver {

    private static final RemoteMobileConfig config =
            MobileConfigReader.getRemoteConfig();

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

        String appUrl =
                System.getenv("BROWSERSTACK_APP_URL");

        if (appUrl == null || appUrl.isBlank()) {
            appUrl = config.app();
        }

        if (appUrl == null || appUrl.isBlank()) {
            throw new IllegalStateException(
                    "BrowserStack app URL is not set"
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

        bstackOptions.put(
                "deviceName",
                config.device()
        );

        bstackOptions.put(
                "osVersion",
                config.osVersion()
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
                "appium:automationName",
                "UiAutomator2"
        );

        capabilities.setCapability(
                "appium:app",
                appUrl
        );

        try {
            return new AndroidDriver(
                    new URL(config.remoteUrl()),
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