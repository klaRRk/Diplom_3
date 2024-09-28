import client.*;
import org.junit.*;
import io.qameta.allure.junit4.DisplayName;
import webpages.*;
import io.restassured.RestAssured;

import java.util.concurrent.TimeUnit;

public class RegistrationTest extends Browser {
    private Client client;
    private String accessToken;

    @Before
    public void setUp() {
        driver.get(Methods.BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registerTest() {
        client = FakeClient.getRandomClient();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        registerPage.clickRegButton();
        String text = loginPage.getEnterLabelText();
        Assert.assertEquals("Вход", text);
        Login login = new Login(client.getEmail(), client.getPassword());
        RestAssured.baseURI = Methods.BASE_URL;
        accessToken = Methods.loginClient(login).then().extract().path("accessToken");
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registerWithWrongPwTest() {
        client = FakeClient.getRandomClientWithWrongPw();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        registerPage.clickRegButton();
        String text = registerPage.getPwErrorText();
        Assert.assertEquals("Некорректный пароль", text);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            Methods.deleteClient(accessToken);
        }
    }
}