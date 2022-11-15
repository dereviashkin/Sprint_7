package testhelpers;

import java.util.UUID;

public class GeneratorHelper {
    public static String stringGenerator(int length){
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }
}
