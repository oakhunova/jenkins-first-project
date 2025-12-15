package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.PracticeFormPage;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;
import helpers.Attach;

public class PracticeFormJenkinsWithPropertiesTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = System.getProperty("browser_resolution", "1920x1080");
        Configuration.timeout = 10000;
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version", "128.0");
        Configuration.remote = System.getProperty("remote_browser");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void allureSetUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    @Tag("property")
    void fillFormTest() {
        step("Open form", () -> {
            practiceFormPage.openPage()
                    .removeBanner();
        });
        step("Fill form", () -> {
            practiceFormPage.setFirstName("Alex")
                    .setLastName("Ivanov")
                    .setUserEmail("test7@mail.com")
                    .setGender("Male")
                    .setUserPhone("1234567890")
                    .setBirthday("01", "January", "1990")
                    .setSubject("Arts")
                    .hobbyCheckbox("Music")
                    .uploadPicture("Cat.jpg")
                    .setAddress("Worldwide")
                    .stateDropdown()
                    .stateCityWrapper("NCR")
                    .cityDropdown()
                    .stateCityWrapper("Delhi")
                    .submitForm();
        });
        step("Verify results", () -> {
            practiceFormPage.verifyResult("Student Name", "Alex Ivanov")
                    .verifyResult("Student Email", "test7@mail.com")
                    .verifyResult("Gender", "Male")
                    .verifyResult("Mobile", "1234567890")
                    .verifyResult("Date of Birth", "01 January,1990")
                    .verifyResult("Subjects", "Arts")
                    .verifyResult("Hobbies", "Music")
                    .verifyResult("Picture", "Cat.jpg")
                    .verifyResult("Address", "Worldwide")
                    .verifyResult("State and City", "NCR Delhi");
        });
    }

    @Test
    @Tag("property")
    void fillRequiredFieldsTest() {
        step("Open form", () -> {
            practiceFormPage.openPage()
                    .removeBanner();
        });
        step("Fill form", () -> {
            practiceFormPage.setFirstName("Alex")
                    .setLastName("Ivanov")
                    .setGender("Male")
                    .setUserPhone("1234567890")
                    .submitForm();
        });
        step("Verify results", () -> {
            practiceFormPage.verifyResult("Student Name", "Alex Ivanov")
                    .verifyResult("Gender", "Male")
                    .verifyResult("Mobile", "1234567890");
        });
    }

    @Test
    @Tag("property")
    void invalidMobileNumberTest() {
        step("Open form", () -> {
            practiceFormPage.openPage()
                    .removeBanner();
        });
        step("Fill form", () -> {
            practiceFormPage.setFirstName("Alex")
                    .setLastName("Ivanov")
                    .setGender("Male")
                    .setUserPhone("123")
                    .submitForm();
        });
        step("Verify results", () -> {
            practiceFormPage.verifyResultForNegativeTest();
        });
    }
}
