import client.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.*;
import webpages.*;

import java.time.Duration;

public class LoginTest extends Browser {
    private Client client;
    private String accessToken;
    private MainPage mainPage;
    private LoginPage loginPage;

    @Before
    @Step("Подготовка теста")
    public void setUp() {
        driver.get(Methods.BASE_URL);
        RestAssured.baseURI = Methods.BASE_URL;
        client = FakeClient.getRandomClient();
        accessToken = Methods.createNewClient(client).then().extract().path("accessToken");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginMainPageTest() {
        mainPage.clickLoginButton();
        performLogin();
        assertSuccessLogin();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginProfilePageTest() {
        mainPage.clickAccButton();
        performLogin();
        assertSuccessLogin();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginRegisterPageTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickAccButton();
        loginPage.clickRegisterButton();
        registerPage.clickLoginButton();
        performLogin();
        assertSuccessLogin();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginForgotPwPageTest() {
        ForgotPwPage forgotPasswordPage = new ForgotPwPage(driver);
        mainPage.clickAccButton();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickLoginButton();
        performLogin();
        assertSuccessLogin();
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            Methods.deleteClient(accessToken);
        }
    }

    @Step("Выполнение входа")
    private void performLogin() {
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
    }

    @Step("Проверка успешного входа")
    private void assertSuccessLogin() {
        String buttonText = mainPage.getCreateOrderButtonText();
         Assert.assertEquals("Неверный текст кнопки после входа", "Оформить заказ", buttonText);
    }
}