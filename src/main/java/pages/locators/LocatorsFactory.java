package pages.locators;

import utils.ConfigReader;

import static utils.enums.Platforms.ANDROID;

public class LocatorsFactory {

    public static LoginPageLocators getLoginPageLocators() {
        if (ConfigReader.getPlatform().equalsIgnoreCase(ANDROID.text())) {
            return new AndroidLoginPageLocators();
        } else {
            throw new UnsupportedOperationException("Платформа " + ConfigReader.getPlatform() + " пока не поддерживается");
        }
    }

    public static WelcomePageLocators getWelcomePageLocators() {
        if (ConfigReader.getPlatform().equalsIgnoreCase(ANDROID.text())) {
            return new AndroidWelcomePageLocators();
        } else {
            throw new UnsupportedOperationException("Платформа " + ConfigReader.getPlatform() + " пока не поддерживается");
        }
    }
}
