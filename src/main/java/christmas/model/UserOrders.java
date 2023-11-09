package christmas.model;

import java.util.ArrayList;

public class UserOrders {
    private ArrayList<Item> orders;

    public UserOrders() {
        this.orders = new ArrayList<Item>();
    }

    public void addOrder(Item item) {
        this.orders.add(item);
    }

    public ArrayList<Item> getOrders() {
        return this.orders;
    }
}
