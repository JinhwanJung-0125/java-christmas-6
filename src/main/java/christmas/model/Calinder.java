package christmas.model;

import java.util.ArrayList;

public class Calinder {
    private ArrayList<Integer> specialDate;
    private ArrayList<Integer> weekend;

    public Calinder() {
        this.specialDate = new ArrayList<Integer>();
        this.weekend = new ArrayList<Integer>();

        setSpecialDate();
        setWeekend();
    }

    private void setSpecialDate() {
        for (int date = 3; date <= 31; date += 7) {
            this.specialDate.add(date);
            if (date == 24) {   // 크리스마스 이브
                this.specialDate.add(date + 1); // 크리스마스 당일
            }
        }
    }

    private void setWeekend() {
        for (int date = 1; date <= 29; date += 7) {
            this.weekend.add(date);
            this.weekend.add(date + 1);
        }
    }

    public ArrayList<Integer> getSpecialDate() {
        return this.specialDate;
    }

    public ArrayList<Integer> getWeekend() {
        return this.weekend;
    }
}
