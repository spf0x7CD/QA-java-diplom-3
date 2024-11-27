package site.nomoreparties.stellarburgers.clients;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.pages.*;

import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.webdriverultils.Url.*;

public class SiteClient {

    @DisplayName("Открытие главной страницы")
    @Step
    public static MainPage openMainPage(WebDriver driver) {
        driver.get(BASE_URL + MAIN_PAGE_URL_RELATIVE);
        return new MainPage(driver);
    }

    @DisplayName("Открытие страницы регистрации")
    @Step
    public static RegisterPage openRegisterPage(WebDriver driver) {
        driver.get(BASE_URL + REGISTER_PAGE_URL_RELATIVE);
        return new RegisterPage(driver);
    }

    @DisplayName("Открытие страницы входа")
    @Step
    public static LoginPage openLoginPage(WebDriver driver) {
        driver.get(BASE_URL + LOGIN_PAGE_URL_RELATIVE);
        return new LoginPage(driver);
    }

    @DisplayName("Открытие страницы восстановления пароля")
    @Step
    public static ForgotPasswordPage openForgotPasswordPage(WebDriver driver) {
        driver.get(BASE_URL + FORGOT_PASSWORD_PAGE_URL_RELATIVE);
        return new ForgotPasswordPage(driver);
    }

    @DisplayName("Открытие страницы личного кабинета")
    @Step
    public static PersonalAccountPage openPersonalAccountPage(WebDriver driver) {
        driver.get(BASE_URL + PERSONAL_ACCOUNT_PAGE_URL_RELATIVE);
        return new PersonalAccountPage(driver);
    }

    @DisplayName("Вход пользователя")
    @Step
    public static LoginPage loginUser(WebDriver driver, String email, String password) {
        return new LoginPage(driver)
                .fillUserEmail(email)
                .fillUserPassword(password)
                .clickEnterButton();
    }

    @DisplayName("Регистрация пользователя")
    @Step
    public static RegisterPage registerUser(WebDriver driver, String name, String email, String password) {
        return new RegisterPage(driver)
                .fillUserName(name)
                .fillUserEmail(email)
                .fillUserPassword(password)
                .clickRegisterButton();
    }

    @DisplayName("Вход пользователя через API")
    @Step
    public static String loginUserByAPI(String email, String password) {
        return given()
                .header("Content-type", "application/json")
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .post("/api/auth/login")
                .jsonPath().getString("accessToken");
    }

    @DisplayName("Удаление пользователя через API")
    @Step
    public static void deleteUserByAPI(String accessToken) {
        if (accessToken == null) accessToken = "";
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }

    @DisplayName("Создание пользователя через API")
    @Step
    public static String createUserByApi(String name, String email, String password) {
        return given()
                .header("Content-type", "application/json")
                .body("{\"name\":\"" + name + "\", \"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .post("/api/auth/register")
                .jsonPath().getString("accessToken");
    }
}