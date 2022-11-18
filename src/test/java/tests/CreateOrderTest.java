package tests;

import entities.Orders;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static data.Endpoints.getOrdersCreateEndpoint;
import static testhelpers.JsonHelper.*;
import static testhelpers.OrderHelper.printOrderData;
import static testhelpers.OrderHelper.randomOrder;

public class CreateOrderTest extends BaseTest {
    @Test
    @Description("Создание заказа")
    @TmsLink("TestCase-CreateOrder-4")
    public void createCorrectOrder() {
        Orders randomOrder = randomOrder();
        Response response = sendPostRequestEndpoint(randomOrder, getOrdersCreateEndpoint());
        validateResponseCode(response, HttpStatus.SC_CREATED);
        printOrderData(randomOrder);
        validateResponseTrackNotNull(response);
        printResponseBodyToConsole(response);
    }
}
