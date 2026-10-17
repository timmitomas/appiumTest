package steps;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import pages.WelcomePage;

import static utils.enums.text.WelcomePageText.SUCCESSFUL_LOGIN;

public class WelcomeSteps extends BaseSteps {

    private final WelcomePage welcomePage = new WelcomePage();

    public WelcomeSteps(AppiumDriver driver) {
        super(driver);
    }

    @Step("Проверить заголовок на главной странице")
    public void verifyWelcomeTitle() {
        baseChecks.verifyValueEqualsTo(text(welcomePage.successMessage()), SUCCESSFUL_LOGIN.text(), "Проверка заголовка на главной странице");
    }
}
