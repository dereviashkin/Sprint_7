package testhelpers;

public class OrderHelper {

    private static Integer idOrder = 0;

    public static Integer getIdOrder() {
        return idOrder;
    }

    public static void setIdOrder(Integer idOrder) {
        OrderHelper.idOrder = idOrder;
    }
}
