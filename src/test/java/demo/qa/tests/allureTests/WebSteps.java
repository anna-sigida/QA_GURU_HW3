package demo.qa.tests.allureTests;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открыть главную страницу GitHub")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Поиск репозитория {repo}")
    public void searchRepository(String repo) {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").pressEnter();
    }

    @Step("Нажать по ссылке репозитория {repo}")
    public void clickOnRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public void shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).shouldBe(visible);
    }
}

