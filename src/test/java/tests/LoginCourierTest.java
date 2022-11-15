package tests;

import entities.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.junit.Test;

import static data.Endpoints.getCourierLoginEndpoint;
import static testhelpers.JsonHelper.*;

public class LoginCourierTest extends BeforeAndAfter {

    @Test
    @Description("Логин существующим курьером, все поля заполнены правильно")
    @TmsLink("TestCase-Login-1")
    public void loginCorrectSuccess() {
        Courier courier = new Courier("KD6-3.7", "Joi");
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, 200);
        validateResponseValueNotNull(response, "id");
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин неверный")
    @TmsLink("TestCase-Login-2")
    public void loginIncorrectLoginFail() {
        Courier courier = new Courier("INCORRECT_LOGIN", "Joi");
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, 404);
        validateResponseValue(response, "message", "Учетная запись не найдена");
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин существует, пароль неверный")
    @TmsLink("TestCase-Login-3")
    public void loginIncorrectPasswordFail() {
        Courier courier = new Courier("KD6-3.7", "INCORRECT_PASSWORD");
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, 404);
        validateResponseValue(response, "message", "Учетная запись не найдена");
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин существует, пароль пусто")
    @TmsLink("TestCase-Login-4")
    public void loginEmptyPasswordFail() {
        Courier courier = new Courier("KD6-3.7", "");
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, 400);
        validateResponseValue(response, "message", "Недостаточно данных для входа");
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин пустой, пароль верный")
    @TmsLink("TestCase-Login-5")
    public void loginEmptyLoginFail() {
        Courier courier = new Courier("", "Joi");
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, 400);
        validateResponseValue(response, "message", "Недостаточно данных для входа");
        printResponseBodyToConsole(response);
    }
}