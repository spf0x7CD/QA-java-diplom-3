package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private WebDriverWait w8;

    private final By enterAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By bunSectionButton = By.xpath(".//span[text()='Булки']/parent::div");
    private final By sauceSectionButton = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingSectionButton = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By ingredientsSectionMenu = By.xpath(".//div[contains(@class, 'menu')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public MainPage scrollIngredientsToPosition(int pixels) {
        WebElement scrollableElement = driver.findElement(ingredientsSectionMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0, " + pixels + ")", scrollableElement);
        return this;
    }

    public MainPage clickBunSectionButton() {
        clickSectionButton(bunSectionButton);
        return this;
    }

    public MainPage clickSauceSectionButton() {
        clickSectionButton(sauceSectionButton);
        return this;
    }

    public MainPage clickFillingSectionButton() {
        clickSectionButton(fillingSectionButton);
        return this;
    }

    private void clickSectionButton(By buttonPath) {
        w8.until(ExpectedConditions.elementToBeClickable(buttonPath));
        driver.findElement(buttonPath).click();
    }

    @Step
    @DisplayName("Клик по кнопке перехода на страницу логина")
    public MainPage clickEnterAccountButton() {
        w8.until(ExpectedConditions.elementToBeClickable(enterAccountButton));
        driver.findElement(enterAccountButton).click();
        return this;
    }
}
