package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class PracticeFormPage {
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userPhoneInput = $("#userNumber");
    private final SelenideElement calendarInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement subjectsMenu = $(".subjects-auto-complete__menu");
    private final SelenideElement hobbyCheckbox = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateDropdown = $("#state");
    private final SelenideElement stateCityWrapper = $("#stateCity-wrapper");
    private final SelenideElement cityDropdown = $("#city");
    private final SelenideElement submitButton = $("#submit");


    CalendarComponent calendarComponent = new CalendarComponent();
    CheckResultComponent checkResultComponent = new CheckResultComponent();
    NegativeTestCheckResultComponent negativeTestCheckResultComponent = new NegativeTestCheckResultComponent();


    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public PracticeFormPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;

    }

    public PracticeFormPage setUserPhone(String value) {
        userPhoneInput.setValue(value);
        return this;

    }

    public PracticeFormPage setBirthday(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        subjectsInput.click();
        subjectsInput.setValue(subject);
        subjectsMenu.click();
        return this;
    }

    public PracticeFormPage hobbyCheckbox(String value) {
        hobbyCheckbox.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage uploadPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public PracticeFormPage stateDropdown() {
        stateDropdown.scrollTo().click();
        return this;
    }

    public PracticeFormPage stateCityWrapper(String value) {
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage cityDropdown() {
        cityDropdown.click();
        return this;
    }

    public PracticeFormPage submitForm() {
        submitButton.click();
        return this;
    }

    public PracticeFormPage verifyResult(String key, String value) {
        checkResultComponent.checkResult(key, value);
        return this;
    }

    public PracticeFormPage verifyResultForNegativeTest() {
        negativeTestCheckResultComponent.negativeTestCheckResult();
        return this;
    }
}
