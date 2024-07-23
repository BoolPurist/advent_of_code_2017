package solutions.day_1;

import solutions.GivenTask;
import solutions.ProducesSolution;

import java.util.function.Function;

public final class SolverDayOne implements ProducesSolution {
    private final int[] digits;

    public SolverDayOne(int[] digits) {
        this.digits = digits;
    }

    @Override
    public String produce(GivenTask task) {
        switch (task) {
            case First -> {
                return calcSum(index -> (index + 1));
            }
            case Second -> {
                final var halfwayLength = this.digits.length / 2;
                return calcSum(index -> (index + halfwayLength));
            }
            default -> throw new IllegalStateException("Unexpected value: " + task);
        }
    }

    private String calcSum(Function<Integer, Integer> onGetNext) {

        int sum = 0;
        for (int index = 0; index < digits.length; index++) {
            final var nextIndex = onGetNext.apply(index) % digits.length;
            final var currentChar = digits[index];
            final var nextChar = digits[nextIndex];
            if (currentChar == nextChar) {
                sum += currentChar;
            }
        }
        return String.valueOf(sum);
    }
}
