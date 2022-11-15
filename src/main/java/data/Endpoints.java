package data;

public class Endpoints {
    private static final String courierCreate = "api/v1/courier/";
    private static final String courierLogin = courierCreate + "login";

    public static String getCourierCreateEndpoint() {
        return courierCreate;
    }

    public static String getCourierLoginEndpoint() {
        return courierLogin;
    }
}
