package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final WebDriverWait w8;
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        w8 = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickExitButton() {
        w8.until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }
}
