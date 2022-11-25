package testhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Courier;
import io.qameta.allure.Step;

import static testhelpers.GeneratorHelper.randomFirstName;
import static testhelpers.GeneratorHelper.stringGenerator;

public class CourierHelper {

    private static Integer idCourier = 0;

    public static Integer getIdCourier() {
        return idCourier;
    }

    public static void setIdCourier(Integer idCourier) {
        CourierHelper.idCourier = idCourier;
    }

    @Step("Generate random courier")
    public static Courier generateRandomCourier() {
        return new Courier(stringGenerator(6), stringGenerator(6), randomFirstName());
    }

    @Step("Print courier data")
    public static void printCourierData(Courier courier) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(courier));
    }
}