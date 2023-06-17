package org.example.calculator.operator.constants;


import org.example.calculator.vo.PositiveNumber;

import java.util.Arrays;

public enum ArithmeticEnumOperator {
    ADD("+") {
        @Override
        public int operate(PositiveNumber operand1, PositiveNumber operand2) {
            return operand1.getNumber() + operand2.getNumber();
        }
    },
    SUBTRACT("-") {
        @Override
        public int operate(PositiveNumber operand1, PositiveNumber operand2) {
            return operand1.getNumber() - operand2.getNumber();
        }
    },
    MULTIPLY("*") {
        @Override
        public int operate(PositiveNumber operand1, PositiveNumber operand2) {
            return operand1.getNumber() * operand2.getNumber();
        }
    },
    DIVIDE("/") {
        @Override
        public int operate(PositiveNumber operand1, PositiveNumber operand2) {
            return operand1.getNumber() / operand2.getNumber();
        }
    };

    private final String operator;

    ArithmeticEnumOperator(String operator) {
        this.operator = operator;
    }

    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return Arrays.stream(values())
                .filter(arithmeticOperator -> arithmeticOperator.operator.equals(operator))
                .map(value -> value.operate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("연산할 수 없는 수식입니다."));
    }

    public abstract int operate(PositiveNumber operand1, PositiveNumber operand2);
}
