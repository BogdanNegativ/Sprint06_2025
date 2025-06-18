package pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;


public class HomePage extends BasePage {
    private static final String HOME_URL = BASE_URL;


    @Getter
    @AllArgsConstructor
    public enum HomePageElements {
        SEARCH_FIELD("//*[@class='SearchLineDesktop']//input"),
        SEARCH_BUTTON("//*[@class='SearchBox__icon']");

        private final String element;
    }

    @Step("Open home page")
    public HomePage openUrl() {
        open(HOME_URL);
        return this;
    }

    @Step("Скролимо до елемента {element}")
    public HomePage scrollTo(HomePageElements element) {
        $x(element.getElement()).scrollIntoCenter();
        return this;
    }

    @Step("Клікаємо на {element}")
    public HomePage clickOn(HomePageElements element) {
        $x(element.getElement()).click();
        return this;
    }

    @Step("Вводимо текст '{text}' у поле {element}")
    public HomePage typeInSearchField(String text, HomePageElements element) {
        $x(element.getElement()).setValue(text);
        return this;
    }
}
