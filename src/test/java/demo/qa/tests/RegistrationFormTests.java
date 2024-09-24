package demo.qa.tests;

import demo.qa.BaseTest;
import demo.qa.data.TestData;
import demo.qa.pages.RegistrationPage;

import org.junit.jupiter.api.Test;


public class RegistrationFormTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    void fillRegistrationFormTest() {
        registrationPage
                .openPage()
                .removeBanner()
                .setName(testData.name)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setNumber(testData.number)
                .setDate(testData.year, testData.month, testData.day)
                .setSubject(testData.subject)
                .setHobbies(testData.hobby)
                .uploadPicture(testData.picture)
                .setAddress(testData.address)
                .setState(testData.state)
                .setCity(testData.city)
                .submit()

                .checkResults("Student Name", testData.name + " " + testData.lastName)
                .checkResults("Student Email", testData.email)
                .checkResults("Gender", testData.gender)
                .checkResults("Mobile", testData.number)
                .checkResults("Date of Birth", testData.day + " " + testData.month + "," + testData.year)
                .checkResults("Subjects", testData.subject)
                .checkResults("Hobbies", testData.hobby)
                .checkResults("Picture", testData.picture)
                .checkResults("Address", testData.address)
                .checkResults("State and City", testData.state + " " + testData.city);
    }

    @Test
    void fillRequiredOnlyFormTest() {
        registrationPage
                .openPage()
                .removeBanner()
                .setName(testData.name)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setNumber(testData.number)
                .submit()

                .checkResults("Student Name", testData.name + " " + testData.lastName)
                .checkResults("Gender", testData.gender)
                .checkResults("Mobile", testData.number);
    }

    @Test
    void EmptyNameAndNumberFormTest() {
        registrationPage
                .openPage()
                .removeBanner()
                .setName("")
                .setLastName("")
                .setGender(testData.gender)
                .setNumber("")
                .submit()

                .checkErrors();
    }


}
