package base;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.time.LocalTime;
import java.util.Objects;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({utils.Listeners.class, io.qameta.allure.testng.AllureTestNg.class})
public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    static {
        LOGGER.info("ЧАС ПОЧАТКУ: {}", LocalTime.now());
        cleanStaticDirectories();
    }

    @BeforeSuite
    public void setupSuite() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false;
        
        LOGGER.info("Selenide конфігурація: browser = {}, baseUrl = {}, headless = {}",
                Configuration.browser, Configuration.baseUrl, Configuration.headless);
    }

    private static void cleanStaticDirectories() {
        File allureResults = new File("target/allure-results");
        if (allureResults.isDirectory()) {
            for (File file : Objects.requireNonNull(allureResults.listFiles())) {
                if (file.delete()) {
                    LOGGER.debug("Видалено файл: {}", file.getName());
                }
            }
            LOGGER.info("Очищено target/allure-results");
        }
    }
}
