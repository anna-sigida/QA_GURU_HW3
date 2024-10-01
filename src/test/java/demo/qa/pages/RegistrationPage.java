package demo.qa.pages;

import com.codeborne.selenide.SelenideElement;
import demo.qa.pages.components.CalendarComponent;
import demo.qa.pages.components.CheckResultComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    CheckResultComponent checkResultComponent = new CheckResultComponent();
    private final SelenideElement dateInput = $("#dateOfBirthInput");

    private SelenideElement nameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            gender =  $("#genterWrapper"),
            numberInput =  $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            hobby = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            address = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            resultsTable = $(".table");

    @Step("Убрать баннер")
    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Открыть главную страницу")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Заполнить имя")
    public RegistrationPage setName(String value) {
        nameInput.setValue(value);
        return this;
    }

    @Step("Заполнить фамилию")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Заполнить email")
    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Выбрать пол")
    public RegistrationPage setGender(String value) {
        gender.$(byText(value)).click();
        return this;
    }

    @Step("Заполнить номер")
    public RegistrationPage setNumber(String value) {
        numberInput.setValue(value);
        return this;
    }

    @Step("Выбрать дату рождения")
    public RegistrationPage setDate(String year, String month, String day) {
        dateInput.click();
        calendarComponent.setDate(year, month, day);
        return this;
    }

    @Step("Выбрать предметы")
    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Заполнить хобби")
    public RegistrationPage setHobbies(String value) {
        hobby.$(byText(value)).click();
        return this;
    }

    @Step("Выбрать аватар")
    public RegistrationPage uploadPicture(String value) {
        pictureUpload.uploadFromClasspath(value);
        return this;
    }

    @Step("Заполнить адрес")
    public RegistrationPage setAddress(String value) {
        address.setValue(value);
        return this;
    }

    @Step("Выбрать штат")
    public RegistrationPage setState(String value) {
        stateInput.click();
        stateInput.$(byText(value)).click();
        return this;
    }

    @Step("Выбрать город")
    public RegistrationPage setCity(String value) {
        cityInput.click();
        cityInput.$(byText(value)).click();
        return this;
    }

    @Step("Нажать отправить")
    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    @Step("Проверить что заполненные результаты совпадают в итоговой форме")
    public RegistrationPage checkResults(String key, String value) {
        checkResultComponent.checkResults(key, value);
        return this;
    }

    @Step("Проверить ошибки")
    public void checkErrors() {
        resultsTable.shouldNotBe(visible);
    }
}
