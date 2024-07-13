package demo.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }
    @BeforeEach
    void login() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Test
    void fillRegistrationFormTest() {
        $("#firstName").setValue("Sasha");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("SashaIvanovaTestData@gmail.com");

        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1111111111");
        $("#dateOfBirthInput").click();

        $(".react-datepicker__year-dropdown-container--select").click();
        $(".react-datepicker__year-select").$(byText("1990")).click();

        $(".react-datepicker__month-dropdown-container--select").click();
        $(".react-datepicker__month-select").$(byText("April")).click();

        $(".react-datepicker__month").$(byText("16")).click();

        $(".subjects-auto-complete__input").click();
        $("#subjectsInput").setValue("Che").pressEnter();
        $("#subjectsInput").setValue("Eng").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("avatar.png");

        $("#currentAddress").setValue("Current Address Value 123");
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Karnal")).click();
        $("#submit").click();

        $(".table").shouldHave(text("Student Name Sasha Ivanova"));
        $(".table").shouldHave(text("Student Email SashaIvanovaTestData@gmail.com"));
        $(".table").shouldHave(text("Gender Female"));
        $(".table").shouldHave(text("Mobile 1111111111"));
        $(".table").shouldHave(text("Date of Birth 16 April,1990"));
        $(".table").shouldHave(text("Subjects Chemistry, English"));
        $(".table").shouldHave(text("Hobbies Sports"));
        $(".table").shouldHave(text("Picture avatar.png"));
        $(".table").shouldHave(text("Address Current Address Value 123"));
        $(".table").shouldHave(text("State and City Haryana Karnal"));
    }
}
