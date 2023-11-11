package christmas.model;

import java.util.HashMap;

public class UserOrders {
    private HashMap<String, Integer> orders;

    public UserOrders() {
        this.orders = new HashMap<String, Integer>();
    }

    public void addOrder(String name, Integer num) {
        this.orders.put(name, num);
    }

    public HashMap<String, Integer> getOrders() {
        return this.orders;
    }
}
