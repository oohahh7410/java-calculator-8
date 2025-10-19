package calculator.service;

import calculator.domain.NumberParser;
import calculator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CalculatorService {

    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";
    private static final String CUSTOM_PREFIX = "//";
    private static final String LINE_SEPARATOR = "\n";

    public int sum(String input) {
        if (StringUtils.isNullOrEmpty(input)) {
            return 0;
        }

        String[] tokens = splitInput(input);
        int[] numbers = NumberParser.parse(tokens);
        return validateAndSum(numbers);
    }

    private String[] splitInput(String input) {
        if (input.startsWith(CUSTOM_PREFIX)) {
            return parseCustomDelimiter(input);
        }
        return input.split(DEFAULT_DELIMITER_REGEX);
    }

    private String[] parseCustomDelimiter(String input) {
        int index = input.indexOf(LINE_SEPARATOR);
        if (index == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }

        String customDelimiter = input.substring(2, index);
        String numbersPart = input.substring(index + 1);
        return numbersPart.split(Pattern.quote(customDelimiter));
    }

    private int validateAndSum(int[] numbers) {
        List<String> negatives = new ArrayList<>();
        int sum = 0;

        for (int number : numbers) {
            if (number < 0) {
                negatives.add(String.valueOf(number));
            }
            sum += number;
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException(
                    "음수는 허용되지 않습니다: " + String.join(", ", negatives)
            );
        }
        return sum;
    }
}