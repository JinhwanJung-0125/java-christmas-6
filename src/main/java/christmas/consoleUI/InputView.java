package christmas.consoleUI;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();

        int date = convertStringToInt(input);
        validateDate(date);

        return date;
    }

    private int convertStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("올바른 숫자 형식이 아닙니다!");
        }
    }


    private void validateDate(int date) {
        if (1 > date || date > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해주세요.");
        }
    }
}
