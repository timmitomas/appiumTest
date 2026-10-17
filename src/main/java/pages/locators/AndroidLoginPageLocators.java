package pages.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class AndroidLoginPageLocators implements LoginPageLocators {
    @Override
    public By loginField() {
        return AppiumBy.id("com.alfabank.qapp:id/etUsername");
    }

    @Override
    public By passwordField() {
        return AppiumBy.id("com.alfabank.qapp:id/etPassword");
    }

    @Override
    public By enterButton() {
        return AppiumBy.id("com.alfabank.qapp:id/btnConfirm");
    }

    @Override
    public By showPasswordIcon() {
        return AppiumBy.id("com.alfabank.qapp:id/text_input_end_icon");
    }

    @Override
    public By title() {
        return AppiumBy.xpath("//android.widget.TextView[@resource-id='com.alfabank.qapp:id/tvTitle']");
    }

    @Override
    public By titleCss() {
        return By.cssSelector("[resource-id='com.alfabank.qapp:id/tvTitle']");
    }

    @Override
    public By errorMessage() {
        return AppiumBy.xpath("//android.widget.TextView[@resource-id='com.alfabank.qapp:id/tvError']");
    }
}