package demo.qa.tests;

import com.codeborne.selenide.Configuration;
import demo.qa.BaseTest;
import demo.qa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillRegistrationFormTest() {
        registrationPage
                .openPage()
                .removeBanner()
                .setName("Sasha")
                .setLastName("Ivanova")
                .setEmail("SashaIvanovaTestData@gmail.com")
                .setGender("Female")
                .setNumber("1111111111")
                .setDate("1990", "April", "16")
                .setSubject("Chemistry")
                .setHobbies("Sports")
                .uploadPicture("avatar.png")
                .setAddress("Current Address Value 123")
                .setState("Haryana")
                .setCity("Karnal")
                .submit()

                .checkResults("Student Name", "Sasha Ivanova")
                .checkResults("Student Email", "SashaIvanovaTestData@gmail.com")
                .checkResults("Gender", "Female")
                .checkResults("Mobile", "1111111111")
                .checkResults("Date of Birth", "16 April,1990")
                .checkResults("Subjects", "Chemistry")
                .checkResults("Hobbies", "Sports")
                .checkResults("Picture", "avatar.png")
                .checkResults("Address", "Current Address Value 123")
                .checkResults("State and City", "Haryana Karnal");
    }

    @Test
    void fillRequiredOnlyFormTest() {
        registrationPage
                .openPage()
                .removeBanner()
                .setName("Sasha")
                .setLastName("Ivanova")
                .setGender("Female")
                .setNumber("1111111111")
                .submit()

                .checkResults("Student Name", "Sasha Ivanova")
                .checkResults("Gender", "Female")
                .checkResults("Mobile", "1111111111");
    }

    @Test
    void EmptyNameAndNumberFormTest() {
        registrationPage
                .openPage()
                .removeBanner()
                .setName("")
                .setLastName("")
                .setGender("Female")
                .setNumber("")
                .submit()

                .checkErrors();
    }
}
