package org.example.calculator.operator.impl;

import org.example.calculator.vo.PositiveNumber;

public class SubtractOperator implements ArithmeticInterfaceOperator {
    private final String SUBTRACT = "-";
    @Override
    public boolean support(String operator) {
        return SUBTRACT.equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.getNumber() - operand2.getNumber();
    }
}

