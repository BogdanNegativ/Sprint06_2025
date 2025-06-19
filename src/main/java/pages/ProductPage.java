package pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage{

    @Getter
    @AllArgsConstructor
    public enum SearchPageElements {
        SEARCH_RESULT_TITLE("//*[@class='css-ccbgiv']"),
        PRODUCTS_NAMES("//*[@class='CatalogProductTile__title']");

        private final String locator;
    }

    @Step("Read text of {locator}")
    public String getTextOfElement(SearchPageElements locator) {
        return $x(locator.getLocator()).shouldBe(visible).getText();
    }

    @Step("Get texts of {locator}")
    public List<String> getTextsOfElements(SearchPageElements locator) {
        return waitElementsAreVisible(locator.getLocator()).texts();
    }

    @Step("Scroll to {locator}")
    public ProductPage scrollTo(SearchPageElements locator) {
        $x(locator.getLocator()).shouldBe(visible).scrollIntoCenter();
        return this;
    }

    @Step("Click on {locator}")
    public ProductPage clickOn(SearchPageElements locator) {
        $x(locator.getLocator()).shouldBe(clickable).click();
        return this;
    }
}
