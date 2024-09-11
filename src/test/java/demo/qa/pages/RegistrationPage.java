package demo.qa.pages;

import com.codeborne.selenide.SelenideElement;
import demo.qa.pages.components.CalendarComponent;
import demo.qa.pages.components.CheckResultComponent;

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

    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }
    public RegistrationPage setName(String value) {
        nameInput.setValue(value);
        return this;
    }
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }
    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }
    public RegistrationPage setGender(String value) {
        gender.$(byText(value)).click();
        return this;
    }
    public RegistrationPage setNumber(String value) {
        numberInput.setValue(value);
        return this;
    }
    public RegistrationPage setDate(String year, String month, String day) {
        dateInput.click();
        calendarComponent.setDate(year, month, day);
        return this;
    }

    public RegistrationPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }
    public RegistrationPage setHobbies(String value) {
        hobby.$(byText(value)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        pictureUpload.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        address.setValue(value);
        return this;
    }
    public RegistrationPage setState(String value) {
        stateInput.click();
        stateInput.$(byText(value)).click();
        return this;
    }
    public RegistrationPage setCity(String value) {
        cityInput.click();
        cityInput.$(byText(value)).click();
        return this;
    }
    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkResults(String key, String value) {
        checkResultComponent.checkResults(key, value);
        return this;
    }

    public void checkErrors() {
        resultsTable.shouldNotBe(visible);
    }
}
