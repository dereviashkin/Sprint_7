package tests;

import entities.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static data.Endpoints.getCourierLoginEndpoint;
import static data.TextResponses.ACCOUNT_NOT_FOUND;
import static data.TextResponses.LACK_OF_DATA_FOR_LOGIN;
import static testhelpers.CourierHelper.getExistingCourier;
import static testhelpers.GeneratorHelper.stringGenerator;
import static testhelpers.JsonHelper.*;

public class LoginCourierTest extends BaseTest {

    @Test
    @Description("Логин существующим курьером, все поля заполнены правильно")
    @TmsLink("TestCase-Login-1")
    public void loginCorrectSuccess() {
        Response response = sendPostRequestEndpoint(getExistingCourier(), getCourierLoginEndpoint());
        validateResponseCode(response, HttpStatus.SC_OK);
        validateResponseValueNotNull(response, "id");
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин неверный")
    @TmsLink("TestCase-Login-2")
    public void loginIncorrectLoginFail() {
        Courier courier = getExistingCourier();
        courier.setLogin(stringGenerator(4));
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, HttpStatus.SC_NOT_FOUND);
        validateResponseValue(response, "message", ACCOUNT_NOT_FOUND);
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин существует, пароль неверный")
    @TmsLink("TestCase-Login-3")
    public void loginIncorrectPasswordFail() {
        Courier courier = getExistingCourier();
        courier.setPassword(stringGenerator(4));
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, HttpStatus.SC_NOT_FOUND);
        validateResponseValue(response, "message", ACCOUNT_NOT_FOUND);
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин существует, пароль пусто")
    @TmsLink("TestCase-Login-4")
    public void loginEmptyPasswordFail() {
        Courier courier = getExistingCourier();
        courier.setPassword("");
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        validateResponseValue(response, "message", LACK_OF_DATA_FOR_LOGIN);
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин пустой, пароль верный")
    @TmsLink("TestCase-Login-5")
    public void loginEmptyLoginFail() {
        Courier courier = getExistingCourier();
        courier.setLogin("");
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        validateResponseValue(response, "message", LACK_OF_DATA_FOR_LOGIN);
        printResponseBodyToConsole(response);
    }
}