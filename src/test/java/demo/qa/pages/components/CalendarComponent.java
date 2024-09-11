package demo.qa.pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(String year, String month, String day) {
        $(".react-datepicker__year-dropdown-container--select").click();
        $(".react-datepicker__year-select").$(byText(year)).click();
        $(".react-datepicker__month-dropdown-container--select").click();
        $(".react-datepicker__month-select").$(byText(month)).click();
        $(".react-datepicker__month").$(byText(day)).click();
    }
}
