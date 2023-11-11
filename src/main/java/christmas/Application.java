package christmas;

import christmas.Discount.Discount;
import christmas.consoleUI.InputView;
import christmas.consoleUI.OutputView;
import christmas.model.Item;
import christmas.model.UserOrders;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int date = InputView.readDate();
        UserOrders orders = InputView.readMenu();
        Item givenItem = Discount.getGivenEvent(orders);

        System.out.println(date);
        OutputView.printOrders(orders);
        OutputView.printBeforeTotalPrice(orders);
        OutputView.printGivenItem(givenItem);
        OutputView.printDiscountHistory(date, orders);
        OutputView.printTotalDiscount(date, orders);
        OutputView.printAfterTotalPrice(date, orders);
        OutputView.printBadge(date, orders);
    }
}
