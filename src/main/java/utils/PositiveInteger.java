package utils;

public record PositiveInteger(int value) {
    public PositiveInteger {
        if (value < 0) {
            throw new IllegalArgumentException("Negative value are not allowed. Faulty value = " + value);
        }
    }

    public static PositiveInteger zero() {
        return new PositiveInteger(0);
    }

    public PositiveInteger add(PositiveInteger other) {
        return new PositiveInteger(value + other.value);
    }
}
