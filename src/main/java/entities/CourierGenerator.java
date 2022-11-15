package entities;

import static testhelpers.GeneratorHelper.stringGenerator;

public class CourierGenerator {
    public static Courier getExistingCourier() {
        return new Courier("KD6-3.7", "Joi");
    }

    public Courier getRandomCourier() {
        return new Courier(stringGenerator(6), stringGenerator(6));
    }

}
