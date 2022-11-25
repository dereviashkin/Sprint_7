package data;

public class Endpoints {
    private static final String courierCreate = "api/v1/courier";
    private static final String courierLogin = courierCreate + "/login";
    private static final String ordersCreate = "api/v1/orders";
    private static final String ordersCancel = ordersCreate + "/cancel";

    public static String getCourierCreateEndpoint() {
        return courierCreate;
    }

    public static String getCourierLoginEndpoint() {
        return courierLogin;
    }

    public static String getOrdersCreateEndpoint() {
        return ordersCreate;
    }

    public static String getOrdersCancelEndpoint() {
        return ordersCancel;
    }
}
