package drivers;

import config.LocalMobileConfig;
import config.MobileConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class LocalMobileDriver {

    private static final LocalMobileConfig config =
            MobileConfigReader.getLocalConfig();

    public static AndroidDriver createDriver() {

        String appPath = System.getenv("LOCAL_APP_PATH");

        if (appPath == null || appPath.isBlank()) {
            appPath = config.app();
        }

        if (appPath == null || appPath.isBlank()) {
            throw new IllegalStateException(
                    "Local application path is not set. " +
                            "Set LOCAL_APP_PATH or app in local.properties"
            );
        }

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(config.device())
                .setPlatformVersion(config.osVersion())
                .setAutomationName("UiAutomator2")
                .setApp(appPath);

        try {
            return new AndroidDriver(
                    new URL(config.appiumServerUrl()),
                    options
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Incorrect local Appium server URL",
                    e
            );
        }
    }
}