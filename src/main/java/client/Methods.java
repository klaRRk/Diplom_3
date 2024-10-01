package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class Methods {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String CLIENT_LOGIN_PATH = "/api/auth/login";
    public static final String CLIENT_PATH = "/api/auth/user";
    public static final String CLIENT_REGISTER_PATH = "api/auth/register";

    @Step("Создание пользователя")
    public Response createNewClient(Client client) {
        return given()
                .header("Content-type", "application/json")
                .body(client)
                .when()
                .post(CLIENT_REGISTER_PATH);
    }

    @Step("Логин пользователя")
    public Response loginClient(Login clientLogin) {
        return given()
                .header("Content-type", "application/json")
                .body(clientLogin)
                .when()
                .post(CLIENT_LOGIN_PATH);
    }

    @Step("Удаление пользователя")
    public Response deleteClient(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(CLIENT_PATH);
    }
}