package christmas;

import christmas.Discount.Discount;
import christmas.consoleUI.InputView;
import christmas.consoleUI.OutputView;
import christmas.model.Item;
import christmas.model.UserOrders;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OutputView.printFirstMessage();
        int date = InputView.readDate();
        UserOrders orders = InputView.readMenu();
        Item givenItem = Discount.getGivenEvent(orders);

        OutputView.printResultMessage(date);
        System.out.println();
        OutputView.printOrders(orders);
        System.out.println();
        OutputView.printBeforeTotalPrice(orders);
        System.out.println();
        OutputView.printGivenItem(givenItem);
        System.out.println();
        OutputView.printDiscountHistory(date, orders);
        System.out.println();
        OutputView.printTotalDiscount(date, orders);
        System.out.println();
        OutputView.printAfterTotalPrice(date, orders);
        System.out.println();
        OutputView.printBadge(date, orders);
    }
}
