package testhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Courier;
import io.qameta.allure.Step;

import static testhelpers.GeneratorHelper.stringGenerator;

public class CourierHelper {

    @Step("Returns existing courier")
    public static Courier getExistingCourier() {
        return new Courier("KD6-3.7", "Joi");
    }

    @Step("Generate random courier")
    public static Courier generateRandomCourier() {
        return new Courier(stringGenerator(6), stringGenerator(6), stringGenerator(6));
    }

    @Step("Print courier data")
    public static void printCourierData(Courier courier) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(courier));
    }
}
