package solutions.day_17;

import solutions.ProvidesPuzzleSolution;

abstract class AbstractSolver implements ProvidesPuzzleSolution {
    protected final PixelatedSpinLockParams initialData;

    protected AbstractSolver(PixelatedSpinLockParams initialData) {
        this.initialData = initialData;
    }

    protected EvolvingSpinLock evolveSpinLockUpTo() {
        final var evolving = new EvolvingSpinLock(initialData.steps());
        final var upperLimit = initialData.endValue();

        for (int i = 0; i < upperLimit; i++) {
            evolving.nextCycle();
        }
        return evolving;
    }
}
