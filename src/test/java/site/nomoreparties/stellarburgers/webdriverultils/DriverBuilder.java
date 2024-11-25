package site.nomoreparties.stellarburgers.webdriverultils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static site.nomoreparties.stellarburgers.webdriverultils.Browser.CHROME;
import static site.nomoreparties.stellarburgers.webdriverultils.Browser.YANDEX_BROWSER;

public class DriverBuilder {
    public static WebDriver getDriver(Browser browser) {
        if (browser.equals(YANDEX_BROWSER)) {
            return createYandexDriver();
        } else {
            return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-maximized");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexbrowserdriver.exe");
        ChromeOptions options = new ChromeOptions()
                .addArguments("start-maximized")
                .setBinary("C:\\Users\\zxcpc2\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"); // Путь к яндекс браузеру
        return new ChromeDriver(options);
    }
}
