package calculator;

import camp.nextstep.edu.missionutils.Console;
import calculator.service.CalculatorService;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        CalculatorService service = new CalculatorService();

        try {
            int result = service.sum(input);

            System.out.println();
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력: " + e.getMessage());
        }
    }
}