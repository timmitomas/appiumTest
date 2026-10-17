package steps;

import checks.BaseChecks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.clipboard.HasClipboard;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.ConfigReader.getInt;

public abstract class BaseSteps {

    protected final AppiumDriver driver;
    protected final WebDriverWait wait;
    protected final BaseChecks baseChecks;

    protected BaseSteps(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(getInt("explicit.wait.seconds")));
        this.baseChecks = new BaseChecks();
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        clickable(locator).click();
    }

    protected String text(By locator) {
        return visible(locator).getText();
    }

    /**
     * Ждёт текст в элементе. По таймауту возвращает {@code false}, чтобы soft-проверка могла идти дальше.
     */
    protected boolean expectedTextPresented(By locator, String expectedText) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected String attribute(By locator, String name) {
        return visible(locator).getAttribute(name);
    }

    protected boolean isAttributeTrue(By locator, String value) {
        return Boolean.parseBoolean(attribute(locator, value));
    }

    /**
     * Заменяет текст в поле. На Android {@code sendKeys} дописывает, поэтому сначала фокус и очистка.
     */
    protected void clearFieldAndEnterText(By locator, String value) {
        WebElement element = clickable(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Вставляет текст из буфера. {@code sendKeys} печатает по символу и вставкой не считается.
     */
    protected void paste(By locator, String value) {
        ((HasClipboard) driver).setClipboardText(value);
        WebElement element = clickable(locator);
        element.click();
        element.clear();
        ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.PASTE));
    }
}
