package testhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Orders;
import io.qameta.allure.Step;

import static testhelpers.GeneratorHelper.numberGenerator;
import static testhelpers.GeneratorHelper.stringGenerator;

public class OrderHelper {

    private static Integer idOrder = 0;

    public static Integer getIdOrder() {
        return idOrder;
    }

    public static void setIdOrder(Integer idOrder) {
        OrderHelper.idOrder = idOrder;
    }

    @Step("Generate random order")
    public static Orders randomOrder() {
        return new Orders(
                stringGenerator(6),
                stringGenerator(6),
                stringGenerator(6),
                stringGenerator(6),
                stringGenerator(6),
                numberGenerator(6),
                "2020-06-06",
                stringGenerator(6),
                new String[]{"Black", "Grey"}
        );
    }

    @Step("Print order data")
    public static void printOrderData(Orders order) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(order));
    }
}
