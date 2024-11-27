package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FixedHeader {

    private final WebDriver driver;
    private final WebDriverWait w8;

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By orderFeedButton = By.xpath(".//p[text()='Лента заказов']");
    private final By logoButton = By.xpath(".//a[@href]/parent::div");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");


    public FixedHeader(WebDriver driver) {
        this.driver = driver;
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
    }
    public FixedHeader clickConstructorButton() {
        w8.until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
        return this;
    }

    public FixedHeader clickOrderFeedButton() {
        w8.until(ExpectedConditions.elementToBeClickable(orderFeedButton));
        driver.findElement(orderFeedButton).click();
        return this;
    }

    public FixedHeader clickLogoImage() {
        w8.until(ExpectedConditions.elementToBeClickable(logoButton));
        driver.findElement(logoButton).click();
        return this;
    }

    public FixedHeader clickPersonalAccountButton() {
        w8.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        return this;
    }
}
