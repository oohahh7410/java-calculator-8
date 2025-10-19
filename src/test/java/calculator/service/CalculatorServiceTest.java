package calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    @DisplayName("null 또는 빈 문자열을 입력하면 0을 반환한다")
    void sum_NullOrEmpty() {
        assertThat(calculatorService.sum(null)).isZero();
        assertThat(calculatorService.sum("")).isZero();
    }

    @Test
    @DisplayName("기본 구분자(쉼표, 콜론)로 합계를 올바르게 계산한다")
    void sum_DefaultDelimiters() {
        int result = calculatorService.sum("1,2:3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자로 합계를 올바르게 계산한다")
    void sum_CustomDelimiter() {
        int result = calculatorService.sum("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("특수문자 커스텀 구분자(*)도 올바르게 계산한다 (Pattern.quote 검증)")
    void sum_CustomDelimiter_SpecialChar() {
        int result = calculatorService.sum("//*\n1*2*3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("숫자 아닌 값이 포함되면 예외를 발생시킨다")
    void sum_NotANumber() {
        assertThatThrownBy(() -> calculatorService.sum("1,a,2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 아닙니다");
    }

    @Test
    @DisplayName("음수가 포함되면 모든 음수를 모아 예외를 발생시킨다")
    void sum_NegativeNumbers() {
        assertThatThrownBy(() -> calculatorService.sum("-1,2,-5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다")
                .hasMessageContaining("-1")
                .hasMessageContaining("-5");
    }

    @Test
    @DisplayName("잘못된 커스텀 구분자 형식일 경우 예외를 발생시킨다 (줄바꿈 없음)")
    void sum_InvalidCustomDelimiterFormat() {
        assertThatThrownBy(() -> calculatorService.sum("//;1;2;3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커스텀 구분자 형식이 올바르지 않습니다");
    }

    @Test
    @DisplayName("입력값에 공백이 포함되어 있어도 올바르게 계산한다")
    void sum_WithSpaces() {
        int result = calculatorService.sum(" 1 , 2 : 3 ");
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("구분자 사이에 빈 값이 포함되면 예외를 발생시킨다")
    void sum_ContainsEmptyValue() {
        assertThatThrownBy(() -> calculatorService.sum("1,,2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 값이 포함되어 있습니다");
    }
}