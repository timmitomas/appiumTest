package pages;

import org.openqa.selenium.By;
import pages.locators.LocatorsFactory;
import pages.locators.WelcomePageLocators;

public class WelcomePage {

    private final WelcomePageLocators locators = LocatorsFactory.getWelcomePageLocators();

    public By successMessage() {
        return locators.successMessage();
    }
}
