package christmas.utility;

import christmas.model.Item;
import christmas.model.Menu;
import java.util.ArrayList;
import java.util.Objects;

public class Util {
    public static String[] splitString(String input, String splitter) {
        try {
            input = input.replaceAll(" ", "");
            return input.split(splitter, 0);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다.다시 입력해 주세요.");
        }
    }

    public static int convertStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("올바른 숫자 형식이 아닙니다!");
        }
    }

    public static void checkDuplicated(int currIdx, String[] checkers) {
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
    public static int findMenuPrice(String menuName) {
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
