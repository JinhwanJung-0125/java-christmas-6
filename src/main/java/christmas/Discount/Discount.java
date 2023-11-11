package christmas.Discount;

import christmas.model.Calinder;
import christmas.model.Item;
import christmas.model.Menu;
import christmas.model.UserOrders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Discount {
    private static boolean validateCanJoinEvent(UserOrders userOrders) {
        return (getTotalPrice(userOrders) >= 10000);
    }

    private static int getChristmasDiscount(int date) {
        if (date > 25) {
            return 0;
        }
        return (1000 + (100 * (date - 1)));
    }

    private static int getWeekDiscount(int date, UserOrders userOrders) {
        if (!checkIsWeekend(date)) {
            return (2023 * getDessertNum(userOrders));
        }

        return 0;
    }

    private static int getWeekendDiscount(int date, UserOrders userOrders) {
        if (checkIsWeekend(date)) {
            return (2023 * getMainNum(userOrders));
        }

        return 0;
    }

    private static int getSpecialDiscount(int date) {
        if (checkIsSpecialDate(date)) {
            return 1000;
        }

        return 0;
    }

    private static Item getGivenEvent(UserOrders userOrders) {
        if (getTotalPrice(userOrders) >= 120000) {
            return new Item("샴페인", 25000);
        }

        return null;
    }

    private static boolean checkIsWeekend(int date) {
        Calinder calinder = new Calinder();
        ArrayList<Integer> weekend = calinder.getWeekend();
        for (Integer weekendDate : weekend) {
            if (date == weekendDate) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkIsSpecialDate(int date) {
        Calinder calinder = new Calinder();
        ArrayList<Integer> specialDate = calinder.getSpecialDate();
        for (Integer special : specialDate) {
            if (date == special) {
                return true;
            }
        }

        return false;
    }

    private static int getDessertNum(UserOrders userOrders) {
        Menu menu = new Menu();
        int retVal = 0;

        for (Item item : menu.getDessertList()) {
            if (checkThisItemIsExist(item, userOrders)) {
                retVal += userOrders.getOrders().get(item);
            }
        }

        return retVal;
    }

    private static int getMainNum(UserOrders userOrders) {
        Menu menu = new Menu();
        int retVal = 0;

        for (Item item : menu.getMainList()) {
            if (checkThisItemIsExist(item, userOrders)) {
                retVal += userOrders.getOrders().get(item);
            }
        }

        return retVal;
    }

    private static boolean checkThisItemIsExist(Item item, UserOrders userOrders) {
        for (Item userItem : userOrders.getOrders().keySet()) {
            if (Objects.equals(item.getName(), userItem.getName())) {
                return true;
            }
        }

        return false;
    }

    private static int getTotalPrice(UserOrders userOrders) {
        int totalPrice = 0;
        HashMap<Item, Integer> orders = userOrders.getOrders();
        for (Item item : orders.keySet()) {
            totalPrice += (item.getPrice() * orders.get(item));
        }

        return totalPrice;
    }
}
