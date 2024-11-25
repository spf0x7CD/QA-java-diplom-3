package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage{
    private final WebDriver driver;
    private final WebDriverWait w8;
    private final By emailField = By.xpath(".//label[contains(text(),'Email')]/parent::*/input");
    private final By passwordField = By.xpath(".//label[contains(text(),'Пароль')]/parent::*/input");
    private final By loginButton = By.xpath(".//button[contains(text(),'Войти')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public LoginPage fillUserEmail(String email) {
        fillUserField(emailField, email);
        return this;
    }

    public LoginPage fillUserPassword(String password) {
        fillUserField(passwordField, password);
        return this;
    }

    private void fillUserField(By fieldPath, String value) {
        WebElement fieldElement = driver.findElement(fieldPath);
        w8.until(ExpectedConditions.visibilityOfElementLocated(fieldPath));
        fieldElement.sendKeys(value);
    }

    @Step
    @DisplayName("Клик по кнопке перехода на страницу логина")
    public LoginPage clickEnterButton() {
        WebElement buttonElement = driver.findElement(loginButton);
        w8.until(ExpectedConditions.elementToBeClickable(loginButton));
        buttonElement.click();
        return this;
    }
}
