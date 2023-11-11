package christmas.consoleUI;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.UserOrders;
import christmas.model.Menu;
import christmas.model.Item;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();

        int date = convertStringToInt(input);
        validateDate(date);

        return date;
    }

    public static UserOrders readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();

        String[] orders = splitString(input, ",");

        validateMenuName(orders);
        validateMenuNum(orders);
        validateOrderOnlyBeverage(orders);
        validateDuplicatedOrder(orders);

        return makeUserOrders(orders);
    }

    private static String[] splitString(String input, String splitter) {
        try {
            input = input.replaceAll(" ", "");
            return input.split(splitter, 0);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다.다시 입력해 주세요.");
        }
    }

    private static int convertStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("올바른 숫자 형식이 아닙니다!");
        }
    }

    private static void checkDuplicated(int currIdx, String[] checkers) {
        for (int checkerIdx = 0; checkerIdx < checkers.length; checkerIdx++) {
            if (currIdx == checkerIdx) {
                continue;
            }
            String checkString = splitString(checkers[currIdx], "-")[0];
            String checker = splitString(checkers[checkerIdx], "-")[0];
            if (Objects.equals(checkString, checker)) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private static void validateDate(int date) {
        if (1 > date || date > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateMenuName(String[] orders) {
        Menu menu = new Menu();

        boolean checkAppetizer = checkMenuIsAppetizer(menu.getAppetizerList(), orders);
        boolean checkMain = checkMenuIsMain(menu.getMainList(), orders);
        boolean checkDessert = checkMenuIsDessert(menu.getDessertList(), orders);
        boolean checkBeverage = checkMenuIsBeverage(menu.getBeverageList(), orders);

        if (!(checkAppetizer || checkMain || checkDessert || checkBeverage)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean checkMenuIsAppetizer(ArrayList<Item> appetizerList, String[] orders) {
        for (Item appetizer : appetizerList) {
            String name = appetizer.getName();

            boolean check = matchMenuName(name, orders);

            if (check) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkMenuIsMain(ArrayList<Item> mainList, String[] orders) {
        for (Item main : mainList) {
            String name = main.getName();

            boolean check = matchMenuName(name, orders);

            if (check) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkMenuIsDessert(ArrayList<Item> dessertList, String[] orders) {
        for (Item dessert : dessertList) {
            String name = dessert.getName();

            boolean check = matchMenuName(name, orders);

            if (check) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkMenuIsBeverage(ArrayList<Item> beverageList, String[] orders) {
        for (Item beverage : beverageList) {
            String name = beverage.getName();

            boolean check = matchMenuName(name, orders);

            if (check) {
                return true;
            }
        }

        return false;
    }

    private static boolean matchMenuName(String menuName, String[] orders) {
        for (String order : orders) {
            String[] oneOrder = splitString(order, "-");
            String orderName = oneOrder[0];

            if (Objects.equals(menuName, orderName)) {
                return true;
            }
        }

        return false;
    }

    private static void validateMenuNum(String[] orders) {
        int menuSum = 0;
        for (String order : orders) {
            String[] oneOrder = splitString(order, "-");
            int menuNum = convertStringToInt(oneOrder[1]);
            if (menuNum < 1) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            menuSum += menuNum;
        }
        if (menuSum > 20) {
            throw new IllegalArgumentException("메뉴는 한번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        }
    }

    private static void validateOrderOnlyBeverage(String[] orders) {
        Menu menu = new Menu();
        ArrayList<ArrayList<Item>> menuTypes = menu.getMenuLists();
        int sum = 0;
        for (int idx = 0; idx < 3; idx++) {
            sum += getItemNum(menuTypes.get(idx), orders);
        }

        if (sum == 0) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static int getItemNum(ArrayList<Item> items, String[] orders) {
        int sum = 0;
        for (Item item : items) {
            sum += getOrderNum(item, orders);
        }

        return sum;
    }

    private static int getOrderNum(Item item, String[] orders) {
        int retVal = 0;
        for (String order : orders) {
            String[] splitedOrder = splitString(order, "-");
            if (Objects.equals(splitedOrder[0], item.getName())) {
                retVal += convertStringToInt(splitedOrder[1]);
            }
        }

        return retVal;
    }

    private static void validateDuplicatedOrder(String[] orders) {
        for (int currIdx = 0; currIdx < orders.length; currIdx++) {
            checkDuplicated(currIdx, orders);
        }
    }

    private static UserOrders makeUserOrders(String[] orders) {
        UserOrders retVal = new UserOrders();

        for (String order : orders) {
            String[] oneOrder = order.split("-", 0);
            String menuName = oneOrder[0];
            int menuNum = convertStringToInt(oneOrder[1]);

            Item item = makeItem(menuName);
            retVal.addOrder(item, menuNum);
        }

        return retVal;
    }

    private static Item makeItem(String menuName) {
        int menuPrice = findMenuPrice(menuName);
        return new Item(menuName, menuPrice);
    }

    private static int findMenuPrice(String menuName) {
        Menu menu = new Menu();
        for (ArrayList<Item> menuList : menu.getMenuLists()) {
            int retVal = searchList(menuName, menuList);
            if (retVal != 0) {
                return retVal;
            }
        }

        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    private static int searchList(String menuName, ArrayList<Item> list) {
        for (Item item : list) {
            if (Objects.equals(menuName, item.getName())) {
                return item.getPrice();
            }
        }
        return 0;
    }
}
