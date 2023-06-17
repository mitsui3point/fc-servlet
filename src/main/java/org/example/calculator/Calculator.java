package org.example.calculator;

import org.example.calculator.operator.impl.*;
import org.example.calculator.vo.PositiveNumber;

import java.util.List;

public class Calculator {

    private final List<ArithmeticInterfaceOperator> arithmeticInterfaceOperators = List.of(
            new AddOperator(),
            new SubtractOperator(),
            new MultiplyOperator(),
            new DivideOperator());

    public int calculate(PositiveNumber operand1, String operatorStr, PositiveNumber operand2) {
        return arithmeticInterfaceOperators
                .stream()
                .filter(operator -> operator.support(operatorStr))
                .map(operator -> operator.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("연산할 수 없는 수식입니다."));
    }

}
