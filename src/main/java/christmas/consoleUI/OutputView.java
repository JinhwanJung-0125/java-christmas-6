package christmas.consoleUI;

import christmas.model.Item;
import christmas.model.UserOrders;
import java.util.HashMap;

public class OutputView {
    public static void printOrders(UserOrders userOrders) {
        HashMap<Item, Integer> orders = userOrders.getOrders();

        System.out.println("<주문 메뉴>");
        for (Item order : orders.keySet()) {
            System.out.println(order.getName() + " " + orders.get(order) + "개");
        }
    }
}
