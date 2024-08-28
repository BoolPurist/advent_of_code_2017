package solutions.day_17;

public final class SolverPart2 extends AbstractSolver {

    public SolverPart2(PixelatedSpinLockParams initialData) {
        super(initialData);
    }

    public static int calcAfterZeroInCycle(int cycles, int steps) {
        var currentPosition = 0;
        var currentAfterZero = 0;

        for (int i = 0, currentSize = 1; i < cycles; i++, currentSize++) {
            currentPosition = ((currentPosition + steps) % currentSize) + 1;

            if (currentPosition  == 1) {
                currentAfterZero = currentSize;
            }
        }

        return currentAfterZero;
    }

    @Override
    public String produce() {
        final var afterThatManyCycles = calcAfterZeroInCycle(initialData.endValue(), initialData.steps());
        return String.valueOf(afterThatManyCycles);
    }
}
