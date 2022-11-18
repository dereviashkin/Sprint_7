package tests;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static data.Endpoints.getOrdersCreateEndpoint;
import static testhelpers.JsonHelper.*;

public class ShowOrdersTest extends BaseTest{

    @Test
    @Description("Получение списка заказов")
    @TmsLink("TestCase-GetOrderList-1")
    public void getOrderList() {
        Response response = sendGetRequestEndpoint(getOrdersCreateEndpoint());
        validateResponseCode(response, HttpStatus.SC_OK);
        validateResponseOrdersNotNull(response);
        response.prettyPrint();
    }
}
