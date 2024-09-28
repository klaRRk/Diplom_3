import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.*;
import webpages.*;
import client.*;

import java.util.concurrent.TimeUnit;

public class ProfilePageTest extends Browser {
    private Client client;
    private String accessToken;

    @Before
    public void setUp() {
        driver.get(Methods.BASE_URL);
        RestAssured.baseURI = Methods.BASE_URL;
        client = FakeClient.getRandomClient();
        accessToken = Methods.createNewClient(client).then().extract().path("accessToken");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToProfileTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccButton();
        String text = profilePage.getLogoutButtonText();
        Assert.assertEquals("Выход", text);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            Methods.deleteClient(accessToken);
        }
    }
}