package org.example.calculator.operator.impl;

import org.example.calculator.vo.PositiveNumber;

public class AddOperator implements ArithmeticInterfaceOperator {
    private final String ADD = "+";
    @Override
    public boolean support(String operator) {
        return ADD.equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.getNumber() + operand2.getNumber();
    }
}
