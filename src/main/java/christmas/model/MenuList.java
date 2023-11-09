package christmas.model;

import java.util.ArrayList;

public class MenuList {
    private ArrayList<Menu> menuList = null;

    public MenuList() {
        addAppetizer();
        addMain();
        addDessert();
        addBeverage();
    }

    private void addAppetizer() {
        String[] menuName = {"양송이수프", "타파스", "시저샐러드"};
        int[] menuPrice = {6000, 5500, 8000};

        for (int i = 0; i < menuName.length; i++) {
            menuList.add(new Menu("애피타이저", menuName[i], menuPrice[i]));
        }
    }

    private void addMain() {
        String[] menuName = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"};
        int[] menuPrice = {55000, 54000, 35000, 25000};

        for (int i = 0; i < menuName.length; i++) {
            menuList.add(new Menu("메인", menuName[i], menuPrice[i]));
        }
    }

    private void addDessert() {
        String[] menuName = {"초코케이크", "아이스크림"};
        int[] menuPrice = {15000, 5000};

        for (int i = 0; i < menuName.length; i++) {
            menuList.add(new Menu("디저트", menuName[i], menuPrice[i]));
        }
    }

    private void addBeverage() {
        String[] menuName = {"제로콜라", "레드와인", "샴페인"};
        int[] menuPrice = {3000, 60000, 25000};

        for (int i = 0; i < menuName.length; i++) {
            menuList.add(new Menu("음료", menuName[i], menuPrice[i]));
        }
    }
}
