package demo.qa.tests;

import demo.qa.data.Languages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тесты GitHub")
public class GitHubParamTests {
    String baseUrl = "https://github.com/";
    String baseDocUrl = "https://docs.github.com/";

    @ParameterizedTest(name = "При поиске документации {0} должен возвращаться не пустой результат")
    @ValueSource(strings = {
            "Git",
            "Api",
            "Docs"
    })
    void checkSearchResultsIsNotEmptyTest(String search) {
        open(baseDocUrl);
        $("[data-testid='site-search-input']").setValue(search).pressEnter();
        $("[data-testid='search-result']").should(exist);
    }

    @ParameterizedTest(name = "При поиске библиотеки {0} на странице присутствует заголовок {1}")
    @CsvSource(value = {
            "selenide, Selenide",
            "allure-framework, Allure Framework"
    })
    void headerShouldBeVisibleTest(String link, String header) {
        open(baseUrl + link);
        $("h1.h2").shouldHave(text(header));
    }

    @ParameterizedTest()
    @DisplayName("При указанной локали на странице присутствует заголовок на соответствующем языке")
    @EnumSource(Languages.class)
    void headerShouldMatchLanguageTest(Languages language) {
        open(baseDocUrl + language.name());
        $("#title-h1").shouldHave(text(language.title));
    }
}
