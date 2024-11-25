package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import site.nomoreparties.stellarburgers.webdriverultils.Browser;

import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.clients.SiteClient.openMainPage;
import static site.nomoreparties.stellarburgers.webdriverultils.Browser.CHROME;
import static site.nomoreparties.stellarburgers.webdriverultils.Browser.YANDEX_BROWSER;
import static site.nomoreparties.stellarburgers.webdriverultils.DriverBuilder.getDriver;
import static site.nomoreparties.stellarburgers.webdriverultils.Url.BASE_URL;

@RunWith(Parameterized.class)
@DisplayName("Секция конструктора бургера")
public class BurgerConstructorTest {
    private WebDriver driver;
    private final int endPosition = 2000;
    private final int startPosition = 0;

    @Parameterized.Parameter
    public Browser browser;


    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {YANDEX_BROWSER},
                {CHROME},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        driver = getDriver(browser);
    }

    @Test
    @DisplayName("Клик по кнопке «Булки» в конструкторе бургера скроллит ингредиенты до раздела с булками")
    public void clickOnBunButtonTest() {
        openMainPage(driver)
                .scrollIngredientsToPosition(endPosition)
                .clickBunSectionButton();

        By sectionName = new ByChained(By.className("tab_tab_type_current__2BEPc"), By.xpath("./span"));
        assertEquals("Булки", driver.findElement(sectionName).getText());
    }

    @Test
    @DisplayName("Клик по кнопке «Начинки» в конструкторе бургера скроллит ингредиенты до раздела с начинками")
    public void clickOnFillingButtonTest() {
        openMainPage(driver)
                .scrollIngredientsToPosition(startPosition)
                .clickFillingSectionButton();

        By sectionName = new ByChained(By.className("tab_tab_type_current__2BEPc"), By.xpath("./span"));
        assertEquals("Начинки", driver.findElement(sectionName).getText());
    }

    @Test
    @DisplayName("Клик по кнопке «Соусы» в конструкторе бургера скроллит ингредиенты до раздела с соусами")
    public void clickOnSauceButtonTest() {
        openMainPage(driver)
                .scrollIngredientsToPosition(endPosition)
                .clickSauceSectionButton();

        By sectionName = new ByChained(By.className("tab_tab_type_current__2BEPc"), By.xpath("./span"));
        assertEquals("Соусы", driver.findElement(sectionName).getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
