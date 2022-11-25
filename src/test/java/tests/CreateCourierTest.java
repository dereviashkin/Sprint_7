package tests;

import entities.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static data.Endpoints.getCourierCreateEndpoint;
import static data.Endpoints.getCourierLoginEndpoint;
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
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Создаем курьера
        //Выполняем запрос
        Response responseCreate = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Проверяем код ответа
        validateResponseCode(responseCreate, HttpStatus.SC_CREATED);
        //Проверяем тело ответа
        validateResponseOkValueIsTrue(responseCreate);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(responseCreate);
        //Логинимся
        //Выполняем запрос
        Response responseLogin = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        //Проверяем тело ответа
        validateResponseIdNotNull(responseLogin);
    }

    @Test
    @Description("Невозможность создания 2 одинаковых курьеров")
    @TmsLink("TestCase-CreateCourier-2")
    public void createTwinCouriersFail() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Создаем первого курьера
        Response firstResponse = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Проверяем код ответа
        validateResponseCode(firstResponse, HttpStatus.SC_CREATED);
        //Проверяем тело ответа
        validateResponseOkValueIsTrue(firstResponse);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(firstResponse);
        //Пытаемся создать второго курьера
        Response secondResponse = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Проверяем код ответа
        validateResponseCode(secondResponse, HttpStatus.SC_CONFLICT);
        //Проверяем тело ответа
        validateResponseMessageValue(secondResponse, LOGIN_OCCUPIED);
        //Выводим тело ответа
        printResponseBodyToConsole(secondResponse);
    }

    @Test
    @Description("Создание курьера с ошибкой: пустой логин")
    @TmsLink("TestCase-CreateCourier-3")
    public void createEmptyLoginCourierFail() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Ломаем логин
        courier.setLogin("");
        //Создаем курьера
        //Выполняем запрос
        Response response = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Проверяем код ответа
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        //Проверяем тело ответа
        validateResponseMessageValue(response, LACK_OF_DATA_FOR_CREATION);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Создание курьера с ошибкой: пустой пароль")
    @TmsLink("TestCase-CreateCourier-4")
    public void createEmptyPasswordCourierFail() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Ломаем пароль
        courier.setPassword("");
        //Создаем курьера
        //Выполняем запрос
        Response response = sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Проверяем код ответа
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        //Проверяем тело ответа
        validateResponseMessageValue(response, LACK_OF_DATA_FOR_CREATION);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(response);
    }
}
