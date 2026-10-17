package pages.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class AndroidWelcomePageLocators implements WelcomePageLocators {

    @Override
    public By successMessage() {
        return AppiumBy.xpath("//android.widget.TextView[@text='Вход в Alfa-Test выполнен']");
    }
}
