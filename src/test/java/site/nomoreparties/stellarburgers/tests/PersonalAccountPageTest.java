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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.pages.FixedHeader;
import site.nomoreparties.stellarburgers.pages.PersonalAccountPage;
import site.nomoreparties.stellarburgers.webdriverultils.Browser;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.clients.SiteClient.*;
import static site.nomoreparties.stellarburgers.webdriverultils.Browser.CHROME;
import static site.nomoreparties.stellarburgers.webdriverultils.Browser.YANDEX_BROWSER;
import static site.nomoreparties.stellarburgers.webdriverultils.DriverBuilder.getDriver;
import static site.nomoreparties.stellarburgers.webdriverultils.Url.*;

@RunWith(Parameterized.class)
@DisplayName("Личный кабинет")
public class PersonalAccountPageTest {
    private WebDriver driver;
    private WebDriverWait w8;
    private String email;
    private String password;
    private String accessToken;

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
        Faker faker = new Faker();
        String name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 8);
        accessToken = createUserByApi(name, email, password);
    }

    @DisplayName("Выход из аккаунта")
    @Test
    public void registrationTest() {
        openLoginPage(driver);
        loginUser(driver, email, password);
        new FixedHeader(driver).clickPersonalAccountButton();
        new PersonalAccountPage(driver).clickExitButton();

        w8.until(ExpectedConditions.urlToBe(BASE_URL + LOGIN_PAGE_URL_RELATIVE));
        assertEquals(BASE_URL + LOGIN_PAGE_URL_RELATIVE, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
        deleteUserByAPI(accessToken);
    }
}
