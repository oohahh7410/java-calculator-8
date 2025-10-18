package calculator.service;

public class CalculatorService {

    // 쉼표(,) 또는 콜론(:)으로 구분하는 기본 구분자 정규식
    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";

    public int sumByDefaultDelimiters(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] tokens = input.split(DEFAULT_DELIMITER_REGEX);

        int sum = 0;
        for (String token : tokens) {
            if (token.trim().isEmpty()) {
                throw new IllegalArgumentException("빈 값이 포함되어 있습니다.");
            }
            try {
                sum += Integer.parseInt(token.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자 형식이 아닙니다: " + token);
            }
        }
        return sum;
    }
}
