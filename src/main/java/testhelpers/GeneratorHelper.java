package testhelpers;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GeneratorHelper {

    static Faker faker = new Faker(new Locale("ru"));

    @Step("Generating random string of length")
    public static String stringGenerator(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    @Step("Generating random number of length")
    public static Integer numberGenerator(int bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(1, bound);
    }

    @Step("Generating random first name")
    public static String randomFirstName() {
        return faker.name().firstName();
    }

    @Step("Generating random last name")
    public static String randomLastName() {
        return faker.name().lastName();
    }

    @Step("Generating random address")
    public static String randomAddress() {
        return faker.address().fullAddress();
    }

    @Step("Generating random phone number")
    public static String randomPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    @Step("Generating random delivery date")
    public static String randomDeliveryDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(faker.date().future(3, TimeUnit.DAYS));
    }

    public static String[] randomColour() {
        String[] color = new String[0];
        int value = numberGenerator(5);
        switch (value) {
            case 1:
                color = new String[]{"Black", "Grey"};
                break;
            case 2:
                color = new String[]{"Grey"};
                break;
            case 3:
                color = new String[]{"Black"};
                break;
            case 4:
                color = new String[]{};
                break;
        }
        return color;
    }
}
