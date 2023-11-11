package christmas.consoleUI;

import static christmas.Discount.Discount.getTotalPrice;

import christmas.Discount.Discount;
import christmas.model.Item;
import christmas.model.UserOrders;
import java.util.HashMap;

public class OutputView {
    public static void printOrders(UserOrders userOrders) {
        HashMap<String, Integer> orders = userOrders.getOrders();

        System.out.println("<주문 메뉴>");
        for (String order : orders.keySet()) {
            System.out.println(order + " " + orders.get(order) + "개");
        }
    }

    public static void printBeforeTotalPrice(UserOrders userOrders) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(getTotalPrice(userOrders) + "원");
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

    public static void printDiscountHistory(int date, UserOrders userOrders) {
        System.out.println("<혜택 내역>");

        if (getTotalPrice(userOrders) == 0) {
            System.out.println("없음");
            return;
        }
        printChristmasDiscount(date, userOrders);
        printWeekDiscount(date, userOrders);
        printWeekendDiscount(date, userOrders);
        printSpecialDiscount(date, userOrders);
        printGivenItem(userOrders);
    }

    public static void printTotalDiscount(int date, UserOrders userOrders) {
        System.out.println("<총혜택 금액>");
        int totalDiscount = Discount.getTotalDiscount(date, userOrders);
        System.out.println(totalDiscount + "원");
    }

    private static void printChristmasDiscount(int date, UserOrders userOrders) {
        int christmasDiscount = Discount.getChristmasDiscount(date, userOrders);
        if (christmasDiscount == 0) {
            return;
        }
        System.out.println("크리스마스 디데이 할인: -" + christmasDiscount + "원");
    }

    private static void printWeekDiscount(int date, UserOrders userOrders) {
        int weekDiscount = Discount.getWeekDiscount(date, userOrders);
        if (weekDiscount == 0) {
            return;
        }
        System.out.println("평일 할인: -" + weekDiscount + "원");
    }

    private static void printWeekendDiscount(int date, UserOrders userOrders) {
        int weekendDiscount = Discount.getWeekendDiscount(date, userOrders);
        if (weekendDiscount == 0) {
            return;
        }
        System.out.println("주말 할인: -" + weekendDiscount + "원");
    }

    private static void printSpecialDiscount(int date, UserOrders userOrders) {
        int specialDiscount = Discount.getSpecialDiscount(date, userOrders);
        if (specialDiscount == 0) {
            return;
        }
        System.out.println("특별 할인: -" + specialDiscount + "원");
    }

    private static void printGivenItem(UserOrders userOrders) {
        Item givenItem = Discount.getGivenEvent(userOrders);
        if (givenItem == null) {
            return;
        }
        System.out.println("증정 이벤트: -" + givenItem.getPrice() + "원");
    }
}
