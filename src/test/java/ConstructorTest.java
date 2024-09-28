import client.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.*;
import webpages.*;

import java.util.concurrent.TimeUnit;

public class ConstructorTest extends Browser {
    private Client client;


    @Before
    public void setUp() {
        driver.get(Methods.BASE_URL);
        RestAssured.baseURI = Methods.BASE_URL;
        client = FakeClient.getRandomClient();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход к разделу Булки на главной странице")
    public void bunTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        String text = mainPage.getMenuLocator();
        Assert.assertEquals("Булки", text);
    }

    @Test
    @DisplayName("Переход к разделу Соусы на главной странице")
    public void sauceTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        String text = mainPage.getMenuLocator();
        Assert.assertEquals("Соусы", text);
    }

    @Test
    @DisplayName("Переход к разделу Начинки на главной странице")
    public void fillingTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
        String text = mainPage.getMenuLocator();
        Assert.assertEquals("Начинки", text);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}