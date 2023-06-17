package org.example.calculator.operator.impl;

import org.example.calculator.vo.PositiveNumber;

public class MultiplyOperator implements ArithmeticInterfaceOperator {
    private final String MULTIPLY = "*";
    @Override
    public boolean support(String operator) {
        return MULTIPLY.equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.getNumber() * operand2.getNumber();
    }
}

