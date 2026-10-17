package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.ConfigReader;

import java.net.URL;
import java.time.Duration;

import static utils.enums.Platforms.ANDROID;

public class DriverFactory {

    public static AppiumDriver createDriver() throws Exception {
        String platform = ConfigReader.getPlatform();
        String serverUrl = ConfigReader.get("appium.server.url");
        URL url = new URL(serverUrl);
        Duration timeout = Duration.ofSeconds(ConfigReader.getInt("new.command.timeout"));

        if (platform.equalsIgnoreCase(ANDROID.text())) {
            return createAndroidDriver(url, timeout);
        } else {
            throw new Exception(platform + "платформа не поддерживается!");
        }
    }

    public static AndroidDriver createAndroidDriver(URL url, Duration timeout) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ANDROID.text());
        options.setDeviceName(ConfigReader.get("android.device.name"));
        options.setAutomationName(ConfigReader.get("android.automation.name"));
        options.setAppPackage(ConfigReader.get("android.app.package"));
        options.setAppActivity(ConfigReader.get("android.app.activity"));
        options.setNewCommandTimeout(timeout);
        options.setNoReset(false);

        return new AndroidDriver(url, options);
    }
}