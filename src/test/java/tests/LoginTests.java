package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import steps.LoginSteps;
import steps.WelcomeSteps;

import java.util.stream.Stream;

import static utils.constantData.LoginConstantData.*;

@Feature("Авторизация")
public class LoginTests extends BaseTest {

    private static final String UNALLOWED_CHARS = "Login!Л";

    private LoginSteps loginSteps;

    private WelcomeSteps welcomeSteps;

    @BeforeEach
    void openLogin() {
        loginSteps = new LoginSteps(driver);
        welcomeSteps = new WelcomeSteps(driver);
    }

    @Test
    @DisplayName("А.01.01 Успешный вход")
    @Description("Проверка успешного входа в систему")
    @TmsLink("TC1")
    @Severity(SeverityLevel.BLOCKER)
    void successfulLogin() {
        loginSteps.verifyLoginTitle()
                .loginWithValidData();
        welcomeSteps.verifyWelcomeTitle();
    }

    @ParameterizedTest(name = "логин {0} / пароль {1}")
    @MethodSource("invalidCredentials")
    @DisplayName("А.01.02 Авторизация с неверными данными")
    @Description("Проверка авторизации с неверными данными")
    @TmsLink("TC2")
    @Severity(SeverityLevel.CRITICAL)
    void loginWithInvalidData(String login, String password) {
        loginSteps.loginWith(login, password)
                .verifyInvalidDataMessage();
    }

    @Test
    @DisplayName("А.01.03 Маскировка ввода пароля")
    @Description("Проверка работы маскировки ввода пароля")
    @TmsLink("TC3")
    @Severity(SeverityLevel.NORMAL)
    void passwordVisibilityToggle() {
        loginSteps.enterPassword(VALID_PASSWORD)
                .verifyPasswordIsHidden()
                .togglePasswordVisibility()
                .verifyPasswordIsShown()
                .verifyPasswordValue(VALID_PASSWORD)
                .togglePasswordVisibility()
                .verifyPasswordIsHidden();
    }

    @Test
    @DisplayName("А.01.04 Неразрешённые символы при вводе и вставке")
    @Description("Логин не принимает запрещённые символы, пароль сохраняет любые")
    @TmsLink("TC4")
    @Issues({@Issue("B1"), @Issue("B2")})
    @Severity(SeverityLevel.NORMAL)
    void entryAndPasteOfUnallowedSymbols() {
        loginSteps.verifyUnallowedChars(UNALLOWED_CHARS);
    }

    @Test
    @DisplayName("А.01.05 Ввод значений длиннее 50 символов")
    @Description("Проверка ввода значения длиннее 50 символов в логин и пароль")
    @TmsLink("TC5")
    @Issue("B3")
    @Severity(SeverityLevel.MINOR)
    void entryMoreThanLimitSymbols() {
        loginSteps.verifyLimitSymbolsInLoginAndPassword();
    }

    private static Stream<Arguments> invalidCredentials() {
        return Stream.of(
                Arguments.of(LOGIN_1, PASS_1),
                Arguments.of(LOGIN_1, PASS_2),
                Arguments.of(LOGIN_1, PASS_3),
                Arguments.of(LOGIN_2, PASS_1),
                Arguments.of(LOGIN_2, PASS_2),
                Arguments.of(LOGIN_2, PASS_3),
                Arguments.of("", ""),
                Arguments.of(VALID_USERNAME, ""),
                Arguments.of("", VALID_PASSWORD)
        );
    }
}