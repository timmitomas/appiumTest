package pages;

import org.openqa.selenium.By;
import pages.locators.LocatorsFactory;
import pages.locators.LoginPageLocators;

public class LoginPage {

    private final LoginPageLocators locators = LocatorsFactory.getLoginPageLocators();

    public By loginField() {
        return locators.loginField();
    }

    public By passwordField() {
        return locators.passwordField();
    }

    public By enterButton() {
        return locators.enterButton();
    }

    public By showPasswordIcon() {
        return locators.showPasswordIcon();
    }

    public By title() {
        return locators.title();
    }

    public By titleCss() {
        return locators.titleCss();
    }

    public By errorMessage() {
        return locators.errorMessage();
    }

}