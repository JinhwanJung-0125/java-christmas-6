package christmas.consoleUI;

import static christmas.Discount.Discount.getTotalDiscount;
import static christmas.Discount.Discount.getTotalPrice;

import christmas.Discount.Discount;
import christmas.model.Item;
import christmas.model.UserOrders;
import java.text.DecimalFormat;
import java.util.HashMap;

public class OutputView {
    public static void printFirstMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printResultMessage(int date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printOrders(UserOrders userOrders) {
        HashMap<String, Integer> orders = userOrders.getOrders();

        System.out.println("<주문 메뉴>");
        for (String order : orders.keySet()) {
            System.out.println(order + " " + orders.get(order) + "개");
        }
    }

    public static void printBeforeTotalPrice(UserOrders userOrders) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d", getTotalPrice(userOrders)) + "원");
    }

    public static void printBadge(int date, UserOrders userOrders) {
        int totalDiscount = Discount.getTotalDiscount(date, userOrders);
        System.out.println("<12월 이벤트 배지>");
        if (totalDiscount < 5000) {
            System.out.println("없음");
        }
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
        boolean christmas = printChristmasDiscount(date, userOrders);
        boolean week = printWeekDiscount(date, userOrders);
        boolean weekend = printWeekendDiscount(date, userOrders);
        boolean special = printSpecialDiscount(date, userOrders);
        boolean given = printGivenItem(userOrders);

        if (!(christmas || week || weekend || special || given)) {
            System.out.println("없음");
        }
    }

    public static void printTotalDiscount(int date, UserOrders userOrders) {
        System.out.println("<총혜택 금액>");
        int totalDiscount = Discount.getTotalDiscount(date, userOrders);
        System.out.println(String.format("%,d", totalDiscount) + "원");
    }

    public static void printAfterTotalPrice(int date, UserOrders userOrders) {
        System.out.println("<할인 후 예상 결제 금액>");
        int totalPrice = (getTotalPrice(userOrders) - getTotalDiscount(date, userOrders));
        System.out.println(String.format("%,d", totalPrice) + "원");
    }

    private static boolean printChristmasDiscount(int date, UserOrders userOrders) {
        int christmasDiscount = Discount.getChristmasDiscount(date, userOrders);
        if (christmasDiscount == 0) {
            return false;
        }
        System.out.println("크리스마스 디데이 할인: -" + String.format("%,d", christmasDiscount) + "원");
        return true;
    }

    private static boolean printWeekDiscount(int date, UserOrders userOrders) {
        int weekDiscount = Discount.getWeekDiscount(date, userOrders);
        if (weekDiscount == 0) {
            return false;
        }
        System.out.println("평일 할인: -" + String.format("%,d", weekDiscount) + "원");
        return true;
    }

    private static boolean printWeekendDiscount(int date, UserOrders userOrders) {
        int weekendDiscount = Discount.getWeekendDiscount(date, userOrders);
        if (weekendDiscount == 0) {
            return false;
        }
        System.out.println("주말 할인: -" + String.format("%,d", weekendDiscount) + "원");
        return true;
    }

    private static boolean printSpecialDiscount(int date, UserOrders userOrders) {
        int specialDiscount = Discount.getSpecialDiscount(date, userOrders);
        if (specialDiscount == 0) {
            return false;
        }
        System.out.println("특별 할인: -" + String.format("%,d", specialDiscount) + "원");
        return true;
    }

    private static boolean printGivenItem(UserOrders userOrders) {
        Item givenItem = Discount.getGivenEvent(userOrders);
        if (givenItem == null) {
            return false;
        }
        System.out.println("증정 이벤트: -" + String.format("%,d", givenItem.getPrice()) + "원");
        return true;
    }
}
