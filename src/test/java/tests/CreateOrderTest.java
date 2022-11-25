package tests;

import entities.Orders;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.Test;

import static testhelpers.JsonHelper.sendPostCreateOrderEndpoint;
import static testhelpers.OrdersHelper.printOrderData;
import static testhelpers.OrdersHelper.randomOrder;

public class CreateOrderTest extends BaseTest {
    @Test
    @Description("Создание заказа")
    @TmsLink("TestCase-CreateOrder-1")
    public void createCorrectOrder() {
        Orders randomOrder = randomOrder();
        printOrderData(randomOrder);
        sendPostCreateOrderEndpoint(randomOrder);
    }
}
