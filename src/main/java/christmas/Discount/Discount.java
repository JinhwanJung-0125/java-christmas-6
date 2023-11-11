package christmas.Discount;

import static christmas.utility.Util.findMenuPrice;

import christmas.model.Calinder;
import christmas.model.Item;
import christmas.model.Menu;
import christmas.model.UserOrders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Discount {
    public static int getChristmasDiscount(int date, UserOrders userOrders) {
        if (validateCanJoinEvent(userOrders)) {
            if (date <= 25) {
                return (1000 + (100 * (date - 1)));
            }
        }

        return 0;
    }

    public static int getWeekDiscount(int date, UserOrders userOrders) {
        if (validateCanJoinEvent(userOrders)) {
            if (!checkIsWeekend(date)) {
                return (2023 * getDessertNum(userOrders));
            }
        }

        return 0;
    }

    public static int getWeekendDiscount(int date, UserOrders userOrders) {
        if (validateCanJoinEvent(userOrders)) {
            if (checkIsWeekend(date)) {
                return (2023 * getMainNum(userOrders));
            }
        }

        return 0;
    }

    public static int getSpecialDiscount(int date, UserOrders userOrders) {
        if (validateCanJoinEvent(userOrders)) {
            if (checkIsSpecialDate(date)) {
                return 1000;
            }
        }

        return 0;
    }

    public static Item getGivenEvent(UserOrders userOrders) {
        if (validateCanJoinEvent(userOrders)) {
            if (getTotalPrice(userOrders) >= 120000) {
                return new Item("샴페인", 25000);
            }
        }

        return null;
    }

    public static int getTotalPrice(UserOrders userOrders) {
        int totalPrice = 0;
        HashMap<String, Integer> orders = userOrders.getOrders();
        for (String name : orders.keySet()) {
            totalPrice += (findMenuPrice(name) * orders.get(name));
        }

        return totalPrice;
    }

    public static int getTotalDiscount(int date, UserOrders userOrders) {
        int christmasDiscount = getChristmasDiscount(date, userOrders);
        int weekDiscount = getWeekDiscount(date, userOrders);
        int weekendDiscount = getWeekendDiscount(date, userOrders);
        int specialDiscount = getSpecialDiscount(date, userOrders);
        int givenItemPrice = 0;
        Item givenItem = getGivenEvent(userOrders);

        if (givenItem != null) {
            givenItemPrice = givenItem.getPrice();
        }

        return (christmasDiscount + weekDiscount + weekendDiscount + specialDiscount + givenItemPrice);
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
                retVal += userOrders.getOrders().get(item.getName());
            }
        }

        return retVal;
    }

    private static int getMainNum(UserOrders userOrders) {
        Menu menu = new Menu();
        int retVal = 0;

        for (Item item : menu.getMainList()) {
            if (checkThisItemIsExist(item, userOrders)) {
                retVal += userOrders.getOrders().get(item.getName());
            }
        }

        return retVal;
    }

    private static boolean validateCanJoinEvent(UserOrders userOrders) {
        return (getTotalPrice(userOrders) >= 10000);
    }

    private static boolean checkThisItemIsExist(Item item, UserOrders userOrders) {
        for (String userItemName : userOrders.getOrders().keySet()) {
            if (Objects.equals(item.getName(), userItemName)) {
                return true;
            }
        }

        return false;
    }
}
