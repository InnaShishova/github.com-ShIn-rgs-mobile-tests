package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import screens.WikipediaScreen;

import static io.qameta.allure.SeverityLevel.CRITICAL;

@Epic("Mobile")
@Feature("Wikipedia Android")
@Owner("InnaShishova")
@Tag("mobile")
public class WikipediaTests extends TestBase {

    @Test
    @Severity(CRITICAL)
    @DisplayName("Поиск статьи в Wikipedia")
    void searchArticleTest() {
        new WikipediaScreen(driver)
                .skipOnboardingIfVisible()
                .openSearch()
                .searchFor("BrowserStack")
                .checkSearchResults();
    }
}