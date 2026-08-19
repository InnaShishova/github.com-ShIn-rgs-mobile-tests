package screens;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaScreen {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By skipButton =
            By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button");

    private final By searchButton =
            By.xpath("//*[@content-desc='Search Wikipedia']");

    private final By searchInput =
            By.id("org.wikipedia.alpha:id/search_src_text");

    private final By searchResultTitles =
            By.id("org.wikipedia.alpha:id/page_list_item_title");


    public WikipediaScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );
    }


    @Step("Пропустить onboarding, если он отображается")
    public WikipediaScreen skipOnboardingIfVisible() {
        try {
            WebElement button = driver.findElement(skipButton);

            if (button.isDisplayed()) {
                button.click();
            }
        } catch (NoSuchElementException ignored) {
            // onboarding уже пройден
        }

        return this;
    }


    @Step("Открыть поиск Wikipedia")
    public WikipediaScreen openSearch() {
        WebElement search = wait.until(
                ExpectedConditions.elementToBeClickable(
                        searchButton
                )
        );

        search.click();

        return this;
    }


    @Step("Проверить открытие экрана поиска")
    public WikipediaScreen checkSearchOpened() {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        searchInput
                )
        );

        assertTrue(
                input.isDisplayed(),
                "Поле поиска должно отображаться"
        );

        return this;
    }


    @Step("Выполнить поиск: {text}")
    public WikipediaScreen searchFor(String text) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(
                        searchInput
                )
        );

        input.clear();
        input.sendKeys(text);

        return this;
    }


    @Step("Проверить наличие результатов поиска")
    public WikipediaScreen checkSearchResults() {
        List<WebElement> results = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        searchResultTitles
                )
        );

        assertFalse(
                results.isEmpty(),
                "Список результатов поиска не должен быть пустым"
        );

        return this;
    }


    @Step("Открыть первую статью из результатов поиска")
    public WikipediaScreen openFirstSearchResult() {
        List<WebElement> results = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        searchResultTitles
                )
        );

        results.get(0).click();

        return this;
    }


    @Step("Проверить, что статья открылась")
    public WikipediaScreen checkArticleOpened() {
        boolean articleOpened = wait.until(webDriver -> {
            String source = webDriver.getPageSource();

            return source.contains("Save")
                    || source.contains("Language")
                    || source.contains("Contents");
        });

        assertTrue(
                articleOpened,
                "Экран статьи должен отображаться"
        );

        return this;
    }
}