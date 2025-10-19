package calculator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @Test
    @DisplayName("입력값이 null이면 true를 반환한다")
    void isNullOrEmpty_NullInput() {
        String input = null;
        boolean result = StringUtils.isNullOrEmpty(input);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("입력값이 빈 문자열이면 true를 반환한다")
    void isNullOrEmpty_EmptyInput() {
        String input = "";
        boolean result = StringUtils.isNullOrEmpty(input);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("입력값이 공백 문자열이면 true를 반환한다")
    void isNullOrEmpty_BlankInput() {
        String input = "   ";
        boolean result = StringUtils.isNullOrEmpty(input);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("입력값이 문자열이면 false를 반환한다")
    void isNullOrEmpty_ValidInput() {
        String input = "1,2";
        boolean result = StringUtils.isNullOrEmpty(input);
        assertThat(result).isFalse();
    }
}