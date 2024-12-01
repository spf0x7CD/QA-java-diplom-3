package site.nomoreparties.stellarburgers.webdriverultils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverBuilder {

    // YANDEX_BROWSER_PATH - переменная пути окружения к яндекс браузеру
    public static WebDriver createDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver();
        }

        switch (browser) {
            case "yandex":
                return createYandexDriver();
            default:
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
                .setBinary(System.getenv("YANDEX_BROWSER_PATH")); // Путь к яндекс браузеру
        return new ChromeDriver(options);
    }
}
