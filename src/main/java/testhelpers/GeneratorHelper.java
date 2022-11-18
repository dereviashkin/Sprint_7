package testhelpers;

import io.qameta.allure.Step;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratorHelper {

    @Step("Generating random string of length")
    public static String stringGenerator(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    @Step("Generating random number of length")
    public static Integer numberGenerator(int bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(1, bound);
    }
}
