package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.ProductPage;

import java.util.List;

import static constant.Constant.Owners.BOHDAN;
import static java.lang.String.format;
import static pages.HomePage.HomePageElements.SEARCH_BUTTON;
import static pages.HomePage.HomePageElements.SEARCH_FIELD;
import static pages.ProductPage.SearchPageElements.PRODUCTS_NAMES;
import static pages.ProductPage.SearchPageElements.SEARCH_RESULT_TITLE;

public class SearchTests extends BaseTest {
    private static final String SEARCH_QUERY_UKRAINIAN = "Чипси";


    @Owner(BOHDAN)
    @Test
    @Description("Check that search results matches with search query")
    public void checkSearchMatches() {
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        SoftAssert softAssert = new SoftAssert();

        homePage
                .openHomePage()
                .scrollTo(SEARCH_FIELD)
                .clickOn(SEARCH_FIELD)
                .typeInSearchField(SEARCH_QUERY_UKRAINIAN, SEARCH_FIELD)
                .clickOn(SEARCH_BUTTON);

        String actualSearchQuery = productPage.getTextOfElement(SEARCH_RESULT_TITLE);
        String expectedSearchQuery = format("Результати пошуку “%s”", SEARCH_QUERY_UKRAINIAN);
        Assert.assertEquals(actualSearchQuery, expectedSearchQuery,
                format("Expected message: %s, actual: %s", expectedSearchQuery, actualSearchQuery));

        List<String> productNames = productPage.getTextsOfElements(PRODUCTS_NAMES);
        productNames.forEach(name -> softAssert.assertTrue(name.toLowerCase().contains(SEARCH_QUERY_UKRAINIAN.toLowerCase()),
                format("Expected that '%s' contains '%s'", name, SEARCH_QUERY_UKRAINIAN)));
        softAssert.assertAll();
    }
}
