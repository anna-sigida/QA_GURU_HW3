package demo.qa.data;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();


    public String name = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String gender = faker.options().option("Male", "Female", "Other");
    public String number = faker.number().digits(10);
    public String year = Integer.toString(faker.number().numberBetween(1900, 2100));
    public String month = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    public String day = Integer.toString(faker.number().numberBetween(1, 27));
    public String subject = faker.options().option("Maths", "Accounting", "Arts", "Social Studies", "Biology", "Physics", "Chemistry", "Computer Science", "Commerce", "Economics", "Civics", "English");
    public String hobby = faker.options().option("Sports", "Reading", "Music");
    public String picture = "avatar.png";
    public String address = faker.address().fullAddress();
    public String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String city = getCity();

    public String getCity() {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            default -> faker.options().option("Rajasthan", "Jaipur");
        };

    }
}
