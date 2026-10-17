package steps;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriverException;
import pages.LoginPage;

import static utils.constantData.LoginConstantData.*;
import static utils.enums.Attributes.PASSWORD;
import static utils.enums.messages.LoginMessages.*;
import static utils.enums.text.LoginPageText.LOGIN_TITLE;

public class LoginSteps extends BaseSteps {

    private final LoginPage loginPage = new LoginPage();

    public LoginSteps(AppiumDriver driver) {
        super(driver);
    }

    @Step("Ввести {login} в поле \"Логин\"")
    public void enterLogin(String login) {
        clearFieldAndEnterText(loginPage.loginField(), login);
    }

    @Step("Ввести {password} в поле \"Пароль\"")
    public LoginSteps enterPassword(String password) {
        clearFieldAndEnterText(loginPage.passwordField(), password);
        return this;
    }

    @Step("Ввести данные: {login} / {password}")
    public void enterLoginData(String login, String password) {
        enterLogin(login);
        enterPassword(password);
    }

    @Step("Нажать кнопку \"Вход\"")
    public void clickEnterButton() {
        click(loginPage.enterButton());
    }

    @Step("Авторизоваться данными: \"{login}\" / \"{password}\"")
    public LoginSteps loginWith(String login, String password) {
        enterLoginData(login, password);
        clickEnterButton();
        return this;
    }

    @Step("Авторизоваться валидными данными: \"" + VALID_USERNAME + "\" / \"" + VALID_PASSWORD + "\"")
    public LoginSteps loginWithValidData() {
        loginWith(VALID_USERNAME, VALID_PASSWORD);
        return this;
    }

    @Step("Проверить сообщения об ошибке при невалидных данных")
    public LoginSteps verifyInvalidDataMessage() {
        baseChecks.verifyThatIsTrue(expectedTextPresented(loginPage.errorMessage(), INVALID_DATA.text()), "Проверка сообщения об ошибке при невалидных данных");
        return this;
    }

    @Step("Проверить заголовок на странице входа")
    public LoginSteps verifyLoginTitle() {
        try {
            baseChecks.verifyValueEqualsTo(text(loginPage.titleCss()), LOGIN_TITLE.text(), "Проверка заголовка на странице входа");
        } catch (WebDriverException e) {
            baseChecks.verifyValueEqualsTo(text(loginPage.title()), LOGIN_TITLE.text(), "Проверка заголовка на странице входа");
        }
        return this;
    }

    @Step("Проверить, что пароль закрыт точками")
    public LoginSteps verifyPasswordIsHidden() {
        baseChecks.verifyThatIsTrue(isPasswordMasked(), "Изначально и при четном нажатия пароль должен быть закрыт точками");
        return this;
    }

    @Step("Проверить, что пароль виден")
    public LoginSteps verifyPasswordIsShown() {
        baseChecks.verifyThatIsFalse(isPasswordMasked(), "Нечетное нажатие должно показать пароль");
        return this;
    }

    @Step("Проверить, что поле \"Пароль\" содержит \"{password}\"")
    public LoginSteps verifyPasswordValue(String password) {
        baseChecks.verifyValueEqualsTo(text(loginPage.passwordField()), password, "Проверить что пароль совпадает с введенным");
        return this;
    }

    @Step("Проверить, что длина введенного значения в поле \"Логин\" не превышает {maxLength} символов")
    public void verifyLoginFieldMaxLength(SoftAssertions softly, int maxLength) {
        baseChecks.verifyValueEqualsTo(softly, text(loginPage.loginField()).length(), maxLength, "Длина введенного значения в поле \"Логин\" не должна превышать " + maxLength + " символов");
    }

    @Step("Проверить, что длина введенного значения в поле \"Пароль\" не превышает {maxLength} символов")
    public void verifyPasswordFieldMaxLength(SoftAssertions softly, int maxLength) {
        baseChecks.verifyValueEqualsTo(softly, text(loginPage.passwordField()).length(), maxLength, "Длина введенного значения в поле \"Пароль\" не должна превышать " + maxLength + " символов");
    }

    @Step("Проверить лимит символов для поля \"Логин\" и \"Пароль\"")
    public void verifyLimitSymbolsInLoginAndPassword() {
        SoftAssertions softly = baseChecks.softly();
        enterLogin("a".repeat(MAX_LOGIN_FIELD_LENGTH + 1));
        verifyLoginFieldMaxLength(softly, MAX_LOGIN_FIELD_LENGTH);
        enterPassword("a".repeat(MAX_PASSWORD_FIELD_LENGTH + 1));
        verifyPasswordFieldMaxLength(softly, MAX_PASSWORD_FIELD_LENGTH);
        softly.assertAll();
    }

    @Step("Проверить, что в поле \"Логин\" только разрешённые символы")
    public void verifyLoginContainsOnlyAllowedChars(SoftAssertions softly) {
        baseChecks.verifyThatIsTrue(softly, ALLOWED_LOGIN_CHARS.matcher(text(loginPage.loginField())).matches(), "Поле \"Логин\" содержит запрещённые символы");
    }

    @Step("Проверить, что отобразилось сообщение \"InvalidValue\" для поля \"Логин\"")
    public void verifyLoginShowsInvalidValueMessage(SoftAssertions softly) {
        baseChecks.verifyThatIsTrue(softly, expectedTextPresented(loginPage.errorMessage(), INVALID_VALUE.text()), "Не отобразилось сообщение InvalidValue для \"Логин\"");
    }

    @Step("Проверить, что отобразилось сообщение \"ExceptValue\" для поля \"Логин\"")
    public void verifyLoginShowsExceptValueMessage(SoftAssertions softly) {
        baseChecks.verifyThatIsTrue(softly, expectedTextPresented(loginPage.errorMessage(), EXCEPT_VALUE.text()), "Не отобразилось сообщение ExceptValue для \"Логин\"");
    }

    @Step("Проверить, что поле \"Пароль\" сохранило \"{password}\"")
    public void verifyPasswordKeepsAnyChars(SoftAssertions softly, String password) {
        baseChecks.verifyValueEqualsTo(softly, text(loginPage.passwordField()), password, "В пароле разрешены любые символы, значение не должно обрезаться");
    }

    @Step("Проверить обработку неразрешённых символов")
    public void verifyUnallowedChars(String text) {
        SoftAssertions softly = baseChecks.softly();
        enterLogin(text);
        verifyLoginContainsOnlyAllowedChars(softly);
        verifyLoginShowsInvalidValueMessage(softly);
        paste(loginPage.loginField(), text);
        verifyLoginContainsOnlyAllowedChars(softly);
        verifyLoginShowsExceptValueMessage(softly);
        enterPassword(text);
        verifyPasswordKeepsAnyChars(softly, text);
        paste(loginPage.passwordField(), text);
        verifyPasswordKeepsAnyChars(softly, text);
        softly.assertAll();
    }

    @Step("Переключить видимость пароля")
    public LoginSteps togglePasswordVisibility() {
        click(loginPage.showPasswordIcon());
        return this;
    }

    public boolean isPasswordMasked() {
        return isAttributeTrue(loginPage.passwordField(), PASSWORD.text());
    }
}
