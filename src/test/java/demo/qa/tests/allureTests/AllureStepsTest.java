package demo.qa.tests.allureTests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureStepsTest {
    private static final String REPO = "eroshenkoam/allure-example";
    private static final int ISSUE = 90;

    @Test
    public void lambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть главную страницу GitHub", () -> {
            open("https://github.com");
        });
        step("Поиск репозитория " + REPO, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").sendKeys(REPO);
            $("#query-builder-test").pressEnter();
        });
        step("Нажать по ссылке репозитория " + REPO, () -> {
            $(linkText(REPO)).click();
        });
        step("Открываем вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).shouldBe(visible);
        });
    }

    @Test
    public void annotatedStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchRepository(REPO);
        steps.clickOnRepository(REPO);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
