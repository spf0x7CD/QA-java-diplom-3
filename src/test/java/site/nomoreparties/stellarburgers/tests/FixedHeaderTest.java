package site.nomoreparties.stellarburgers.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.pages.FixedHeader;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.clients.SiteClient.*;
import static site.nomoreparties.stellarburgers.webdriverultils.DriverBuilder.createDriver;
import static site.nomoreparties.stellarburgers.webdriverultils.Url.*;

@DisplayName("Кнопки шайпки сайта")
public class FixedHeaderTest {
    private WebDriver driver;
    private WebDriverWait w8;
    private String email;
    private String password;
    private String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        driver = createDriver();
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
        Faker faker = new Faker();
        String name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 8);
        accessToken = createUserByApi(name, email, password);
    }

    @Test
    @DisplayName("Клик по кнопке «Личный Кабинет» открывает страницу авторизации для неавторизованных порльзователей")
    public void personalAccountButtonRedirectionWithoutAuthorizationTest() {
        openMainPage(driver);
        new FixedHeader(driver).clickPersonalAccountButton();

        w8.until(ExpectedConditions.urlToBe(BASE_URL + LOGIN_PAGE_URL_RELATIVE));
        assertEquals(BASE_URL + LOGIN_PAGE_URL_RELATIVE, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Клик по кнопке «Личный Кабинет» открывает страницу личного кабинета для авторизованных порльзователей")
    public void personalAccountButtonRedirectionWithAuthorizationTest() {
        openLoginPage(driver);
        loginUser(driver, email, password);
        new FixedHeader(driver).clickPersonalAccountButton();

        w8.until(ExpectedConditions.urlToBe(BASE_URL  + PERSONAL_ACCOUNT_PAGE_URL_RELATIVE));
        assertEquals(BASE_URL + PERSONAL_ACCOUNT_PAGE_URL_RELATIVE, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Клик по кнопке «Конструктор» из личного кабинета открывает главную страницу")
    public void constructorButtonRedirectionTest() {
        openLoginPage(driver);
        loginUser(driver, email, password);
        new FixedHeader(driver)
                .clickPersonalAccountButton()
                .clickConstructorButton();

        w8.until(ExpectedConditions.urlToBe(BASE_URL + MAIN_PAGE_URL_RELATIVE));
        assertEquals(BASE_URL + MAIN_PAGE_URL_RELATIVE, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Клик по кнопке с логотипом из личного кабинета открывает главную страницу")
    public void logoButtonRedirectionTest() {
        openLoginPage(driver);
        loginUser(driver, email, password);
        new FixedHeader(driver)
                .clickPersonalAccountButton()
                .clickLogoImage();

        w8.until(ExpectedConditions.urlToBe(BASE_URL + MAIN_PAGE_URL_RELATIVE));
        assertEquals(BASE_URL + MAIN_PAGE_URL_RELATIVE, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
        deleteUserByAPI(accessToken);
    }
}
