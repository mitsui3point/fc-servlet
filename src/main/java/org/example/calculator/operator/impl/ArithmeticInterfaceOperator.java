package org.example.calculator.operator.impl;

import org.example.calculator.vo.PositiveNumber;

public interface ArithmeticInterfaceOperator {
    boolean support(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
