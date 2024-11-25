package site.nomoreparties.stellarburgers.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.pages.RegisterPage;
import site.nomoreparties.stellarburgers.webdriverultils.Browser;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.clients.SiteClient.*;
import static site.nomoreparties.stellarburgers.webdriverultils.Browser.CHROME;
import static site.nomoreparties.stellarburgers.webdriverultils.Browser.YANDEX_BROWSER;
import static site.nomoreparties.stellarburgers.webdriverultils.DriverBuilder.getDriver;
import static site.nomoreparties.stellarburgers.webdriverultils.Url.*;

@RunWith(Parameterized.class)
@DisplayName("Регистрация пользователя")
public class RegistrationTest {
    private WebDriver driver;
    private WebDriverWait w8;
    private Faker faker;
    private String email;
    private String password;

    @Parameterized.Parameter
    public Browser browser;

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {YANDEX_BROWSER},
                {CHROME},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        driver = getDriver(browser);
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
        faker = new Faker();
    }

    @DisplayName("Регистрация пользователя")
    @Test
    public void registrationTest() {
        String name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 8);

        openRegisterPage(driver);
        registerUser(driver, name, email, password);

        w8.until(ExpectedConditions.urlToBe(BASE_URL + LOGIN_PAGE_URL_RELATIVE));
        assertEquals(BASE_URL + LOGIN_PAGE_URL_RELATIVE, driver.getCurrentUrl());
    }

    @DisplayName("Неудачная регистрация пользователя с паролем меньше 6 символов")
    @Test
    public void registrationWithShortPasswordTest() {
        String name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(4, 6);

        openRegisterPage(driver);
        RegisterPage page = registerUser(driver, name, email, password);
        List<WebElement> errors = page.getFieldsErrors();

        assertEquals(1, errors.size());
        assertEquals("Некорректный пароль", errors.get(0).getText());
        assertEquals(BASE_URL + REGISTER_PAGE_URL_RELATIVE, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
        deleteUserByAPI(loginUserByAPI(email, password));
    }
}
