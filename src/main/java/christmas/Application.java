package christmas;

import christmas.consoleUI.InputView;
import christmas.model.Item;
import christmas.model.UserOrders;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int date = InputView.readDate();
        UserOrders orders = InputView.readMenu();

        System.out.println(date);
        for(Item order : orders.getOrders().keySet()) {
            System.out.println(order.getName() + " " + order.getPrice() + " " + orders.getOrders().get(order));
        }
    }
}
