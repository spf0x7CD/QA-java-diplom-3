package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait w8;
    private final By nameField = By.xpath(".//label[contains(text(),'Имя')]/parent::*/input");
    private final By emailField = By.xpath(".//label[contains(text(),'Email')]/parent::*/input");
    private final By passwordField = By.xpath(".//label[contains(text(),'Пароль')]/parent::*/input");
    private final By registerButton = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public RegisterPage fillUserName(String name) {
        fillUserField(nameField, name);
        return this;
    }

    public RegisterPage fillUserEmail(String email) {
        fillUserField(emailField, email);
        return this;
    }

    public RegisterPage fillUserPassword(String password) {
        fillUserField(passwordField, password);
        return this;
    }

    private void fillUserField(By fieldPath, String value) {
        WebElement fieldElement = driver.findElement(fieldPath);
        w8.until(ExpectedConditions.visibilityOfElementLocated(fieldPath));
        fieldElement.sendKeys(value);
    }

    @Step
    @DisplayName("Клик по ссылке перехода на страницу логина")
    public RegisterPage clickLoginLink() {
        w8.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();
        return this;
    }

    @Step
    @DisplayName("Клик по кнопке перехода на страницу логина")
    public RegisterPage clickRegisterButton() {
        WebElement buttonElement = driver.findElement(registerButton);
        w8.until(ExpectedConditions.elementToBeClickable(registerButton));
        buttonElement.click();
        return this;
    }

    public List<WebElement> getFieldsErrors() {
        w8.until(ExpectedConditions.presenceOfElementLocated(By.className("input__error")));
        return driver.findElements(By.className("input__error"));
    }

}
