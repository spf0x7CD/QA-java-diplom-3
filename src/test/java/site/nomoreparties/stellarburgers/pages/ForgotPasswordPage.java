package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final WebDriverWait w8;
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step
    @DisplayName("Клик по ссылке перехода на страницу логина")
    public ForgotPasswordPage clickLoginLink() {
        w8.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();
        return this;
    }
}
