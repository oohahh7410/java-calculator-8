package calculator.service;

import calculator.util.StringUtils;
import calculator.domain.NumberParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CalculatorService {

    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";

    public int sum(String input) {
        if (StringUtils.isNullOrEmpty(input)) {
            return 0;
        }

        String[] tokens = splitInput(input);
        int[] numbers = NumberParser.parse(tokens);

        return validateAndSum(numbers);
    }

    private String[] splitInput(String input) {
        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }

            String customDelimiter = input.substring(2, delimiterEndIndex);
            String numbersPart = input.substring(delimiterEndIndex + 1);

            return numbersPart.split(Pattern.quote(customDelimiter));
        }
        return input.split(DEFAULT_DELIMITER_REGEX);
    }

    private int validateAndSum(int[] numbers) {
        int sum = 0;
        List<String> negativeNumbers = new ArrayList<>();

        for (int num : numbers) {
            if (num < 0) {
                negativeNumbers.add(String.valueOf(num));
            }
            sum += num;
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + String.join(", ", negativeNumbers));
        }

        return sum;
    }
}