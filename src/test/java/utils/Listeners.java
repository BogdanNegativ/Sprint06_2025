package utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Listeners implements ITestListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Listeners.class);

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("FirstTest FAILED: {}", result.getName());

        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriver driver = WebDriverRunner.getWebDriver();

            saveScreenshotLocally(driver);
            attachScreenshotToAllure(driver);
            attachLogs(result);
        } else {
            LOGGER.warn("WebDriver ще не створено — скріншот не збережено.");
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] attachScreenshotToAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void saveScreenshotLocally(WebDriver driver) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File folder = new File("target/screenshots");
            if (!folder.exists() && folder.mkdirs()) {
                LOGGER.info("Створено папку для скріншотів: {}", folder.getAbsolutePath());
            }

            File dest = new File(folder, "screenshot_" + System.currentTimeMillis() + ".png");
            FileUtils.copyFile(src, dest);
            LOGGER.info("Збережено локальний скріншот: {}", dest.getAbsolutePath());
        } catch (Exception e) {
            LOGGER.error("Помилка при збереженні скріншота: {}", e.getMessage());
        }
    }

    @Attachment(value = "FirstTest logs", type = "text/plain")
    private String attachLogs(ITestResult result) {
        return "FirstTest: " + result.getName() + "\n" +
                "Status: " + getStatus(result) + "\n" +
                "Exception: " + getExceptionMessage(result);
    }

    private String getStatus(ITestResult result) {
        return switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "SUCCESS";
            case ITestResult.FAILURE -> "FAILURE";
            case ITestResult.SKIP -> "SKIPPED";
            default -> "UNKNOWN";
        };
    }

    private String getExceptionMessage(ITestResult result) {
        return result.getThrowable() != null
                ? result.getThrowable().getMessage()
                : "None";
    }
}
