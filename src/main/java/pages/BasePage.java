package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;

import static com.codeborne.selenide.Selenide.$$x;

public class BasePage {
    protected static final String BASE_URL = ConfigReader.getProperty("production.baseUrl");

    public ElementsCollection waitElementsAreVisible(String locator) {
        return $$x(locator).shouldBe(CollectionCondition.allMatch("visible", WebElement::isDisplayed));
    }
}
