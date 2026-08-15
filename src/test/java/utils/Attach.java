package utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

public class Attach {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] screenshot(AndroidDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/xml")
    public static byte[] pageSource(AndroidDriver driver) {
        return driver.getPageSource()
                .getBytes(StandardCharsets.UTF_8);
    }
}