package christmas.model;

import java.util.HashMap;

public class UserOrders {
    private HashMap<Item, Integer> orders;

    public UserOrders() {
        this.orders = new HashMap<Item, Integer>();
    }

    public void addOrder(Item item, Integer num) {
        this.orders.put(item, num);
    }

    public HashMap<Item, Integer> getOrders() {
        return this.orders;
    }
}
