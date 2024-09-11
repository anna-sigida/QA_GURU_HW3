package demo.qa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CheckResultComponent {
    private final SelenideElement resultsTable = $(".table");

    public void checkResults(String key, String value) {
        resultsTable.shouldHave(text(key + " " + value));
    }
}
