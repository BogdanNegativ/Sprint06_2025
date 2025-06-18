package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Objects;

@Listeners({utils.Listeners.class, io.qameta.allure.testng.AllureTestNg.class})
public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    static {
        LOGGER.info("ЧАС ПОЧАТКУ: {}", LocalTime.now());
        cleanStaticDirectories();
    }

    @BeforeSuite
    public void setupSuite() throws IOException {
        // SetUp WebDriver Chrome without another browser (WebDriverManager)
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);


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
