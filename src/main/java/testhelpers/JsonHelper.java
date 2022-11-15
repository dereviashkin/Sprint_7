package testhelpers;

import entities.Courier;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class JsonHelper {

    @Step("Send POST request to required endpoint")
    public static Response sendPostRequestEndpoint(Courier courier, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(endpoint);
    }

    @Step("Check if response code is expected")
    public static void validateResponseCode(Response response, int code) {
        response.then().statusCode(code);
    }

    @Step("Check if response value is not null")
    public static void validateResponseValueNotNull(Response response, String key) {
        response.then().assertThat().body(key, notNullValue());
    }

    @Step("Check if response value is expected")
    public static void validateResponseValue(Response response, String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Step("Print response body to console")
    public static void printResponseBodyToConsole(Response response) {
        System.out.println(response.body().asString());
    }
}
