package calculator.service;

import calculator.domain.NumberParser;
import calculator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CalculatorService {

    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";
    private static final String CUSTOM_PREFIX = "//";

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
        String normalized = input.replaceAll("\\r\\n|\\r|\\n", "\n");
        int idx = normalized.indexOf('\n');

        String customDelimiter;
        String numbersPart;

        if (idx != -1) {
            customDelimiter = normalized.substring(2, idx).trim();
            numbersPart = normalized.substring(idx + 1);
        } else {
            int lit = input.indexOf("\\n");
            if (lit == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
            customDelimiter = input.substring(2, lit).trim();
            numbersPart = input.substring(lit + 2);
        }

        if (customDelimiter.matches("\\d+")) {
            throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다: " + customDelimiter);
        }

        String regex = Pattern.quote(customDelimiter) + "|,|:";
        return numbersPart.split(regex);
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