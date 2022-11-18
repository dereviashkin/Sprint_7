package testhelpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;

import static data.Endpoints.getCourierCreateEndpoint;
import static data.Endpoints.getOrdersCancelEndpoint;
import static io.restassured.RestAssured.given;
import static testhelpers.CourierHelper.getIdCourier;
import static testhelpers.CourierHelper.setIdCourier;
import static testhelpers.OrderHelper.getIdOrder;
import static testhelpers.OrderHelper.setIdOrder;

public class JsonHelper {

    @Step("Send POST request to required endpoint")
    public static Response sendPostRequestEndpoint(Object serializedJson, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .body(serializedJson)
                .post(endpoint);
    }

    @Step("Send GET request to required endpoint")
    public static Response sendGetRequestEndpoint(String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .get(endpoint);
    }

    @Step("Check if response code is expected")
    public static void validateResponseCode(Response response, int code) {
        response.then().statusCode(code);
    }

    @Step("Check if response id value is not null")
    public static void validateResponseIdNotNull(Response response) {
        Assert.assertNotNull(getValueByKey(response, "id"));
        setIdCourier(Integer.valueOf(getValueByKey(response, "id")));
    }

    @Step("Check if response orders value is not null")
    public static void validateResponseOrdersNotNull(Response response) {
        Assert.assertNotNull(getValueByKey(response, "orders"));
    }

    @Step("Check if response track value is not null")
    public static void validateResponseTrackNotNull(Response response) {
        Assert.assertNotNull(getValueByKey(response, "track"));
        setIdOrder(Integer.valueOf(getValueByKey(response, "track")));
    }

    @Step("Check if response message value is expected")
    public static void validateResponseMessageValue(Response response, String value) {
        Assert.assertEquals(getValueByKey(response, "message"), value);
    }

    @Step("Check if response boolean ok value is expected")
    public static void validateResponseOkValueIsTrue(Response response) {
        Assert.assertEquals(getValueByKey(response, "ok"), "true");
    }

    @Step("Print response body to console")
    public static void printResponseBodyToConsole(Response response) {
        response.prettyPrint();
    }

    @Step("Get value by key")
    public static String getValueByKey(Response response, String key) {
        return response.then().extract().body().path(key).toString();
    }

    @Step("Remove courier and order")
    public static void removeCourierAndOrderById() {
        if (getIdOrder() != 0) {
            given()
                    .header("Content-type", "application/json")
                    .put(getOrdersCancelEndpoint() + "?track=" + getIdOrder());
            System.out.println("Deleted order id: " + getIdOrder());
            System.out.println();
            setIdOrder(0);
        }
        if (getIdCourier() != 0) {
            given()
                    .header("Content-type", "application/json")
                    .delete(getCourierCreateEndpoint() + "/" + getIdCourier());
            System.out.println("Deleted courier id: " + getIdCourier());
            System.out.println();
            setIdCourier(0);
        }
    }
}