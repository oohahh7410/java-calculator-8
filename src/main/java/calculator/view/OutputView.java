package calculator.view;

public class OutputView {

    private OutputView() {}

    public static void printResult(int result) {
        System.out.println();
        System.out.println("결과 : " + result);
    }

    public static void printError(String message) {
        System.out.println("잘못된 입력: " + message);
    }
}