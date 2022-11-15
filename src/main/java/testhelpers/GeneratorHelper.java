package testhelpers;

import io.qameta.allure.Step;

import java.util.UUID;

public class GeneratorHelper {

    @Step("Generating random string of length")
    public static String stringGenerator(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }
}
