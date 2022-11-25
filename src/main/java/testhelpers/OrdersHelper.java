package testhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Orders;
import io.qameta.allure.Step;

import static data.MetroStations.randomMetroStation;
import static testhelpers.GeneratorHelper.*;

public class OrdersHelper {

    private static Integer idOrder = 0;

    public static Integer getIdOrder() {
        return idOrder;
    }

    public static void setIdOrder(Integer idOrder) {
        OrdersHelper.idOrder = idOrder;
    }

    @Step("Generate random order")
    public static Orders randomOrder() {
        return new Orders(
                randomFirstName(),
                randomLastName(),
                randomAddress(),
                randomMetroStation(),
                randomPhoneNumber(),
                numberGenerator(6),
                randomDeliveryDate(),
                stringGenerator(6),
                randomColour()
        );
    }

    @Step("Generate black order")
    public static Orders randomBlackOrder() {
        return new Orders(
                randomFirstName(),
                randomLastName(),
                randomAddress(),
                randomMetroStation(),
                randomPhoneNumber(),
                numberGenerator(6),
                randomDeliveryDate(),
                stringGenerator(6),
                new String[]{"Black"}
        );
    }

    @Step("Generate grey order")
    public static Orders randomGreyOrder() {
        return new Orders(
                randomFirstName(),
                randomLastName(),
                randomAddress(),
                randomMetroStation(),
                randomPhoneNumber(),
                numberGenerator(6),
                randomDeliveryDate(),
                stringGenerator(6),
                new String[]{"Grey"}
        );
    }

    @Step("Generate black and grey order")
    public static Orders randomBlackGreyOrder() {
        return new Orders(
                randomFirstName(),
                randomLastName(),
                randomAddress(),
                randomMetroStation(),
                randomPhoneNumber(),
                numberGenerator(6),
                randomDeliveryDate(),
                stringGenerator(6),
                new String[]{"Black", "Grey"}
        );
    }
    @Step("Generate empty colour order")
    public static Orders randomEmptyColorOrder() {
        return new Orders(
                randomFirstName(),
                randomLastName(),
                randomAddress(),
                randomMetroStation(),
                randomPhoneNumber(),
                numberGenerator(6),
                randomDeliveryDate(),
                stringGenerator(6),
                new String[]{}
        );
    }

    @Step("Print order data")
    public static void printOrderData(Orders order) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(order));
    }
}
