package extensions;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

import java.io.ByteArrayInputStream;

public class ScreenshotOnFailure implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        // проверяем, упал ли тест (если ошибки нет - выходим)
        if (context.getExecutionException().isEmpty()) {
            return;
        }
        // достаем экземпляр тестового класса (например LoginTests, который extends BaseTest)
        Object testInstance = context.getRequiredTestInstance();
        // проверяем, что это BaseTest и берем драйвер
        if (testInstance instanceof BaseTest baseTest) {
            AppiumDriver driver = baseTest.getDriver();
            // делаем скриншот, если драйвер жив
            if (driver instanceof TakesScreenshot screenshot) {
                byte[] png = screenshot.getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Экран при падении", "image/png", new ByteArrayInputStream(png), "png");
            }
        }
    }
}