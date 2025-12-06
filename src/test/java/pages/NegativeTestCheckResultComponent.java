package pages;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;

public class NegativeTestCheckResultComponent {
    public void negativeTestCheckResult() {
        $("#userForm").shouldHave(cssClass("was-validated"));
    }
}
