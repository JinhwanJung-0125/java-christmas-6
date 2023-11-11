package christmas.consoleUI;

import christmas.Discount.Discount;
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

    public static void printBadge(int date, UserOrders userOrders) {
        int totalDiscount = Discount.getTotalDiscount(date, userOrders);

        System.out.println("<12월 이벤트 배지>");
        if (5000 <= totalDiscount && totalDiscount < 10000) {
            System.out.println("별");
        }
        if (10000 <= totalDiscount && totalDiscount < 20000) {
            System.out.println("트리");
        }
        if (20000 <= totalDiscount) {
            System.out.println("산타");
        }
    }

    public static void printGivenItem(Item givenItem) {
        System.out.println("<증정 메뉴>");

        if (givenItem != null) {
            System.out.println(givenItem.getName());
            return;
        }

        System.out.println("없음");
    }
}
