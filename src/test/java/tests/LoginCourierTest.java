package tests;

import entities.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static data.Endpoints.getCourierCreateEndpoint;
import static data.Endpoints.getCourierLoginEndpoint;
import static data.TextResponses.ACCOUNT_NOT_FOUND;
import static data.TextResponses.LACK_OF_DATA_FOR_LOGIN;
import static testhelpers.CourierHelper.generateRandomCourier;
import static testhelpers.CourierHelper.printCourierData;
import static testhelpers.GeneratorHelper.stringGenerator;
import static testhelpers.JsonHelper.*;

public class LoginCourierTest extends BaseTest {

    @Test
    @Description("Логин курьером, все поля заполнены правильно")
    @TmsLink("TestCase-Login-1")
    public void loginCorrectSuccess() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Запрос на создание курьера
        sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Выполняем запрос
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        //Проверяем код ответа
        validateResponseCode(response, HttpStatus.SC_OK);
        validateResponseIdNotNull(response);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин неверный")
    @TmsLink("TestCase-Login-2")
    public void loginIncorrectLoginFail() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Выполняем запрос
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        //Проверяем код ответа
        validateResponseCode(response, HttpStatus.SC_NOT_FOUND);
        //Проверяем тело ответа
        validateResponseMessageValue(response, ACCOUNT_NOT_FOUND);
        //Выводим тело ответа
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин существует, пароль неверный")
    @TmsLink("TestCase-Login-3")
    public void loginIncorrectPasswordFail() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Запрос на создание курьера
        sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Ломаем пароль
        courier.setPassword(stringGenerator(4));
        //Выполняем запрос
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        //Проверяем код ответа
        validateResponseCode(response, HttpStatus.SC_NOT_FOUND);
        //Проверяем тело ответа
        validateResponseMessageValue(response, ACCOUNT_NOT_FOUND);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин существует, пароль пусто")
    @TmsLink("TestCase-Login-4")
    public void loginEmptyPasswordFail() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Запрос на создание курьера
        sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Обнуляем пароль
        courier.setPassword("");
        //Выполняем запрос
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        //Проверяем код ответа
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        //Проверяем тело ответа
        validateResponseMessageValue(response, LACK_OF_DATA_FOR_LOGIN);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(response);
    }

    @Test
    @Description("Логин пустой, пароль верный")
    @TmsLink("TestCase-Login-5")
    public void loginEmptyLoginFail() {
        //Создаем экземпляр курьера
        Courier courier = generateRandomCourier();
        //Запрос на создание курьера
        sendPostRequestEndpoint(courier, getCourierCreateEndpoint());
        //Обнуляем логин
        courier.setLogin("");
        //Выполняем запрос
        Response response = sendPostRequestEndpoint(courier, getCourierLoginEndpoint());
        //Проверяем код ответа
        validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
        //Проверяем тело ответа
        validateResponseMessageValue(response, LACK_OF_DATA_FOR_LOGIN);
        //Выводим json-тело курьера
        printCourierData(courier);
        //Выводим тело ответа
        printResponseBodyToConsole(response);
    }
}