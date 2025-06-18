package pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {

    @Getter
    @AllArgsConstructor
    public enum SearchPageElements {
        SEARCH_RESULT_TITLE("//*[@class='css-ccbgiv']"),
        PRODUCTS_NAMES("//*[@class='CatalogProductTile__title']"),
        SHOW_MORE("//*[text()='Показати ще']");

        private final String element;
    }

    @Step("Зчитуємо текст {element}")
    public String getTextOfElement(SearchPageElements element) {
        return $x(element.getElement()).getText();
    }

    @Step("Отримуєм текст {element}")
    public List<String> getTextsOfElements(SearchPageElements element) {
        return $$x(element.getElement()).texts();
    }

    @Step("Скролимо до елемента {element}")
    public SearchPage scrollTo(SearchPageElements element) {
        $x(element.getElement()).scrollIntoCenter();
        return this;
    }

    @Step("Клікаємо на {element}")
    public SearchPage clickOn(SearchPageElements element) {
        $x(element.getElement()).click();
        return this;
    }
}
