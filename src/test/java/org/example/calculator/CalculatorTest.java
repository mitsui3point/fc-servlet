package org.example.calculator;

import org.example.calculator.vo.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 요구사항
 * 간단한 사칙 연산을 할 수 있다.(덧셈, 뺄셈, 곱셈, 나눗셈)
 * 양수로만 계산할 수 있다.
 * 나눗셈에서 0을 나누는 경우 {@link IllegalArgumentException} 예외를 발생시킨다
 * MVC 패턴(Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {

    @DisplayName("연산을 수행한다.")
    @ParameterizedTest
    @MethodSource("operandSource")
    void operation(int operand1, String operation, int operand2, int result) {
        //when
        int actual = new Calculator().calculate(new PositiveNumber(operand1), operation, new PositiveNumber(operand2));
        //then
        assertThat(actual).isEqualTo(result);
    }

    @DisplayName("양수가 아니거나 연산자가 없을경우 IllegalArgumentException 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource({"operandNegativeOrZeroSource", "operationNotExistSource"})
    void operationFail(int operand1, String operation, int operand2, String errorMessage) {
        assertThatCode(() -> {
            //when
            new Calculator().calculate(new PositiveNumber(operand1), operation, new PositiveNumber(operand2));
        })
                //then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    public static Stream<Arguments> operandSource() {
        return Stream.of(
                Arguments.arguments(1, "+", 2, 3),
                Arguments.arguments(1, "-", 2, -1),
                Arguments.arguments(1, "*", 2, 2),
                Arguments.arguments(1, "/", 2, 0)
        );
    }

    public static Stream<Arguments> operandNegativeOrZeroSource() {
        String message = "0이나 음수는 연산할 수 없습니다.";
        return Stream.of(
                Arguments.arguments(0, "/", 0, message),
                Arguments.arguments(0, "/", 1, message),
                Arguments.arguments(1, "/", 0, message),
                Arguments.arguments(-1, "+", 1, message),
                Arguments.arguments(1, "+", -1, message)
        );
    }
    public static Stream<Arguments> operationNotExistSource() {
        String message = "연산할 수 없는 수식입니다.";
        return Stream.of(
                Arguments.arguments(1, "^", 1, message),
                Arguments.arguments(1, "!", 1, message),
                Arguments.arguments(1, "&", 1, message),
                Arguments.arguments(1, null, 1, message),
                Arguments.arguments(1, "", 1, message)
        );
    }
}
