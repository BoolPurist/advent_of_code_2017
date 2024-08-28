package solutions.day_17;

public final class SolverPart2 extends AbstractSolver {

    public SolverPart2(PixelatedSpinLockParams initialData) {
        super(initialData);
    }

    @Override
    public String produce() {
        final var evolved = super.evolveSpinLockUpTo();
        final var firstElement = evolved.getFirstElement();
        return String.valueOf(firstElement);
    }
}
