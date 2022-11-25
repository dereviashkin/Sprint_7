package tests;

import entities.Orders;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static data.Endpoints.getOrdersCreateEndpoint;
import static testhelpers.JsonHelper.*;
import static testhelpers.OrdersHelper.*;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest extends BaseTest {
    private Orders orders;
    private int statusCode;

    public CreateOrderParameterizedTest(Orders orders, int statusCode) {
        this.orders = orders;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestDate() {
        return new Object[][]{
                {randomBlackOrder(), HttpStatus.SC_CREATED},
                {randomGreyOrder(), HttpStatus.SC_CREATED},
                {randomBlackGreyOrder(), HttpStatus.SC_CREATED},
                {randomEmptyColorOrder(), HttpStatus.SC_CREATED},
        };
    }

    @Test
    public void createOrderParameterized() {
        Response response = sendPostRequestEndpoint(orders, getOrdersCreateEndpoint());
        validateResponseCode(response, statusCode);
        validateResponseTrackNotNull(response);
    }
}