package tests;

import entities.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static data.Endpoints.getCourierCreateEndpoint;
import static data.TextResponses.LACK_OF_DATA_FOR_CREATION;
import static data.TextResponses.LOGIN_OCCUPIED;
import static testhelpers.CourierHelper.generateRandomCourier;
import static testhelpers.CourierHelper.printCourierData;
import static testhelpers.JsonHelper.*;

public class CreateCourierTest extends BaseTest {

    @Test
    @Description("Создание курьера")
    @TmsLink("TestCase-CreateCourier-1")
    public void createCorrectCourierSuccess() {
        Courier courier = generateRandomCourier();
        Response response = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        validateResponseCode(response, HttpStatus.SC_CREATED);
        validateResponseValueBoolean(response, "ok", true);
        printCourierData(courier);
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Невозможность создания 2 одинаковых курьеров")
    @TmsLink("TestCase-CreateCourier-2")
    public void createTwinCouriersFail() {
        Courier courier = generateRandomCourier();
        Response firstResponse = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        validateResponseCode(firstResponse, HttpStatus.SC_CREATED);
        validateResponseValueBoolean(firstResponse, "ok", true);
        printCourierData(courier);
        printResponseBodyToConsole(firstResponse);
        Response secondResponse = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        validateResponseCode(secondResponse, HttpStatus.SC_CONFLICT);
        validateResponseValue(secondResponse, "message", LOGIN_OCCUPIED);
        printResponseBodyToConsole(secondResponse);
    }

    @Test
    @Description("Создание курьера с ошибкой: пустой логин")
    @TmsLink("TestCase-CreateCourier-3")
    public void createEmptyLoginCourierFail() {
        Courier courier = generateRandomCourier();
        courier.setLogin("");
        Response response = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        validateResponseValue(response, "message", LACK_OF_DATA_FOR_CREATION);
        printCourierData(courier);
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Создание курьера с ошибкой: пустой пароль")
    @TmsLink("TestCase-CreateCourier-4")
    public void createEmptyPasswordCourierFail() {
        Courier courier = generateRandomCourier();
        courier.setPassword("");
        Response response = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        validateResponseValue(response, "message", LACK_OF_DATA_FOR_CREATION);
        printCourierData(courier);
        printResponseBodyToConsole(response);
    }
}
