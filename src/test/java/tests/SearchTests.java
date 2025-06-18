package tests;

import base.BaseTest;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SearchPage;

import java.util.List;

import static constant.Constant.Owners.BOHDAN;
import static java.lang.String.format;
import static pages.HomePage.HomePageElements.SEARCH_BUTTON;
import static pages.HomePage.HomePageElements.SEARCH_FIELD;
import static pages.SearchPage.SearchPageElements.PRODUCTS_NAMES;
import static pages.SearchPage.SearchPageElements.SEARCH_RESULT_TITLE;

public class SearchTests extends BaseTest {
    private static final String SEARCH_QUERY_UKRAINIAN = "Чипси";
    //Write simple test for example


    @Owner(BOHDAN)
    @Test
    public void checkTest() {
        HomePage homePage = new HomePage();
        SearchPage searchPage = new SearchPage();
        SoftAssert softAssert = new SoftAssert();

        homePage.openUrl()
                .scrollTo(SEARCH_FIELD)
                .clickOn(SEARCH_FIELD)
                .typeInSearchField(SEARCH_QUERY_UKRAINIAN, SEARCH_FIELD)
                .clickOn(SEARCH_BUTTON);

        String actualSearchQuery = searchPage.getTextOfElement(SEARCH_RESULT_TITLE);
        String expectedSearchQuery = format("Результати пошуку “%s”", SEARCH_QUERY_UKRAINIAN);
        Assert.assertEquals(actualSearchQuery, expectedSearchQuery,
                format("Expected message: %s, actual: %s", expectedSearchQuery, actualSearchQuery));

        List<String> productNames = searchPage.getTextsOfElements(PRODUCTS_NAMES);
        productNames.forEach(name -> softAssert.assertTrue(name.toLowerCase().contains(SEARCH_QUERY_UKRAINIAN.toLowerCase()),
                format("Expected that '%s' contains '%s'", name, SEARCH_QUERY_UKRAINIAN)));

//        MegaRofl
//        while ($x(SHOW_MORE.getElement()).is(Condition.exist,Duration.ofSeconds(2))) {
//            searchPage.scrollTo(SHOW_MORE)
//                    .clickOn(SHOW_MORE);
//        }
//
//        productNames = searchPage.getTextsOfElements(PRODUCTS_NAMES);
//        productNames.forEach(name -> softAssert.assertTrue(name.toLowerCase().contains(SEARCH_QUERY_UKRAINIAN.toLowerCase()),
//                format("Expected that '%s' contains '%s'", name, SEARCH_QUERY_UKRAINIAN)));
        softAssert.assertAll();
    }
}
