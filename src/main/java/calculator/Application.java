package calculator;

import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) throws IllegalArgumentException {
        String input = InputView.readInput();

        CalculatorService service = new CalculatorService();

        int result = service.sum(input);
        OutputView.printResult(result);
    }
}