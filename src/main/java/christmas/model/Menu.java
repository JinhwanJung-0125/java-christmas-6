package christmas.model;

import java.util.ArrayList;

public class Menu {
    private final ArrayList<Item> appetizerList;  // 에피타이저 메뉴
    private final ArrayList<Item> mainList;       // 메인 메뉴
    private final ArrayList<Item> dessertList;    // 디저트 메뉴
    private final ArrayList<Item> beverageList;   // 음료 메뉴

    public Menu() {
        appetizerList = new ArrayList<Item>();
        mainList = new ArrayList<Item>();
        dessertList = new ArrayList<Item>();
        beverageList = new ArrayList<Item>();
        addAppetizer();
        addMain();
        addDessert();
        addBeverage();
    }

    private void addAppetizer() {
        String[] names = {"양송이수프", "타파스", "시저샐러드"};
        int[] prices = {6000, 5500, 8000};

        for (int i = 0; i < names.length; i++) {
            this.appetizerList.add(new Item(names[i], prices[i]));
        }
    }

    private void addMain() {
        String[] names = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"};
        int[] prices = {55000, 54000, 35000, 25000};

        for (int i = 0; i < names.length; i++) {
            this.mainList.add(new Item(names[i], prices[i]));
        }
    }

    private void addDessert() {
        String[] names = {"초코케이크", "아이스크림"};
        int[] prices = {15000, 5000};

        for (int i = 0; i < names.length; i++) {
            this.dessertList.add(new Item(names[i], prices[i]));
        }
    }

    private void addBeverage() {
        String[] names = {"제로콜라", "레드와인", "샴페인"};
        int[] prices = {3000, 60000, 25000};

        for (int i = 0; i < names.length; i++) {
            this.beverageList.add(new Item(names[i], prices[i]));
        }
    }

    public ArrayList<Item> getAppetizerList() {
        return this.appetizerList;
    }

    public ArrayList<Item> getMainList() {
        return this.mainList;
    }

    public ArrayList<Item> getDessertList() {
        return this.dessertList;
    }

    public ArrayList<Item> getBeverageList() {
        return this.beverageList;
    }
}
