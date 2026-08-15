package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class WikipediaScreen {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public WikipediaScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Пропустить onboarding, если он отображается")
    public WikipediaScreen skipOnboardingIfVisible() {
        List<WebElement> skipButtons = driver.findElements(
                AppiumBy.id(
                        "org.wikipedia.alpha:id/fragment_onboarding_skip_button"
                )
        );

        if (!skipButtons.isEmpty()) {
            skipButtons.get(0).click();
        }

        return this;
    }

    @Step("Открыть поиск Wikipedia")
    public WikipediaScreen openSearch() {
        WebElement search = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Search Wikipedia")
                )
        );

        search.click();

        return this;
    }

    @Step("Выполнить поиск: {text}")
    public WikipediaScreen searchFor(String text) {
        WebElement searchInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id(
                                "org.wikipedia.alpha:id/search_src_text"
                        )
                )
        );

        searchInput.sendKeys(text);

        return this;
    }

    @Step("Проверить наличие результатов поиска")
    public WikipediaScreen checkSearchResults() {
        List<WebElement> results = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        AppiumBy.className("android.widget.TextView")
                )
        );

        assertFalse(
                results.isEmpty(),
                "Список результатов поиска не должен быть пустым"
        );

        return this;
    }
}