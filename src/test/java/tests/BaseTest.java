package tests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.BeforeClass;

import static testhelpers.JsonHelper.removeCourierAndOrderById;

public abstract class BaseTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void cleanUp() {
        removeCourierAndOrderById();
    }
}
