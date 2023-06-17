package org.example.calculator.vo;

public class PositiveNumber {
    private final int number;

    public PositiveNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (isNegativeOrZero(number)) {
            throw new IllegalArgumentException("0이나 음수는 연산할 수 없습니다.");
        }
    }

    private boolean isNegativeOrZero(int number) {
        return number <= 0;
    }

    public int getNumber() {
        return number;
    }
}
