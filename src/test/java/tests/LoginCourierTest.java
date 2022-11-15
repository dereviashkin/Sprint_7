package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static data.Endpoints.getCourierLoginEndpoint;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest extends BeforeAndAfter {

    @Test
    @DisplayName("****")
    @Description("Логин существующим курьером, все поля заполнены правильно")
    public void loginCorrectSuccess() {
//        Courier courier = new Courier("KD6-3.7", "Joi", "Nexus-9 replicant");
        String json = "{\"login\":\"KD6-3.7\",\"password\":\"Joi\"}";
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(getCourierLoginEndpoint())
                .then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("****")
    @Description("Логин существует, пароль неверный")
    public void loginIncorrectPasswordFail() {
//        Courier courier = new Courier("KD6-3.7", "Joi", "Nexus-9 replicant");
        String json = "{\"login\":\"KD6-3.7\",\"password\":\"incorrectpassword\"}";
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(getCourierLoginEndpoint())
                .then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("****")
    @Description("Логин неверный")
    public void loginIncorrectLoginFail() {
//        Courier courier = new Courier("KD6-3.7", "Joi", "Nexus-9 replicant");
        String json = "{\"login\":\"incorrectlogin\",\"password\":\"incorrectpassword\"}";
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(getCourierLoginEndpoint())
                .then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}