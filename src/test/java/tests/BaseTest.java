package tests;

import io.restassured.RestAssured;
import org.junit.Before;

public abstract class BaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }
}
