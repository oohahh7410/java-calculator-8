package calculator.domain;

import calculator.util.StringUtils;

public class NumberParser {
    public static int[] parse(String[] tokens) {
        int[] numbers = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();
            if (StringUtils.isNullOrEmpty(token)) {
                throw new IllegalArgumentException("빈 값이 포함되어 있습니다.");
            }
            try {
                numbers[i] = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자 형식이 아닙니다: " + token);
            }
        }
        return numbers;
    }
}