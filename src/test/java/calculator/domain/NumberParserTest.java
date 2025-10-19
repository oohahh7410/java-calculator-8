package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberParserTest {

    @Test
    @DisplayName("문자열 배열을 숫자 배열로 올바르게 변환한다")
    void parse_Success() {
        String[] tokens = {"1", "2", "3"};
        int[] result = NumberParser.parse(tokens);
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("배열에 숫자가 아닌 값이 포함되면 예외를 발생시킨다")
    void parse_NotANumber() {
        String[] tokens = {"1", "a", "3"};

        assertThatThrownBy(() -> NumberParser.parse(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 아닙니다");
    }

    @Test
    @DisplayName("배열에 빈 값(공백)이 포함되면 예외를 발생시킨다")
    void parse_EmptyToken() {
        String[] tokens = {"1", " ", "3"};

        assertThatThrownBy(() -> NumberParser.parse(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 값이 포함되어 있습니다");
    }

    @Test
    @DisplayName("배열에 쉼표 연속으로 인한 빈 문자열이 포함되면 예외를 발생시킨다")
    void parse_EmptyStringToken() {
        String[] tokens = {"1", "", "3"};

        assertThatThrownBy(() -> NumberParser.parse(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 값이 포함되어 있습니다");
    }
}