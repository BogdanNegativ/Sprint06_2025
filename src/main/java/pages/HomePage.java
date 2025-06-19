package pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class HomePage extends BasePage {
    private static final String HOME_URL = BASE_URL;

    @Getter
    @AllArgsConstructor
    public enum HomePageElements {
        SEARCH_FIELD("//*[@data-marker='Search Input']"),
        SEARCH_BUTTON("//*[@class='SearchBox__icon']");

        private final String locator;
    }

    @Step("Open home page")
    public HomePage openUrl() {
        open(HOME_URL);
        return this;
    }

    @Step("Scroll to {locator}")
    public HomePage scrollTo(HomePageElements locator) {
        $x(locator.getLocator()).shouldBe(visible).scrollIntoCenter();
        return this;
    }

    @Step("Click on {locator}")
    public HomePage clickOn(HomePageElements locator) {
        $x(locator.getLocator()).shouldBe(clickable).click();
        return this;
    }



    @Step("Input text '{text}' into field {locator}")
    public HomePage typeInSearchField(String text, HomePageElements locator) {
        $x(locator.getLocator()).shouldBe(editable).setValue(text);
        return this;
    }
}
