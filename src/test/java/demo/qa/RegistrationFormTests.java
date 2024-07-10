package demo.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {
    File avatar = new File("src/test/resources/avatar.png");
    @BeforeAll
    static void setUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }
    @BeforeEach
    void login() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void testFillRegistrationForm() {
        $("#firstName").setValue("Sasha");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("SashaIvanovaTestData@gmail.com");

        $(byText("Female")).click();
        $("#userNumber").setValue("1111111111");
        $("#dateOfBirthInput").click();

        $(".react-datepicker__year-dropdown-container--select").click();
        $(byText("1990")).click();

        $(".react-datepicker__month-dropdown-container--select").click();
        $(byText("April")).click();

        $(byText("16")).click();

        $(".subjects-auto-complete__input").click();
        $("#subjectsInput").setValue("Che").pressEnter();
        $("#subjectsInput").setValue("Eng").pressEnter();

        $(byText("Sports")).click();
        $("#uploadPicture").uploadFile(avatar);

        $("#currentAddress").setValue("Current Address Value 123");
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();
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
