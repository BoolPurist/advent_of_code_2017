package solutions.day_17;

public final class SolverPart1 extends AbstractSolver {

    public SolverPart1(PixelatedSpinLockParams initialData) {
        super(initialData);
    }

    @Override
    public String produce() {
        final var evolved = super.evolveSpinLockUpTo();
        return String.valueOf(evolved.getAfterCurrent());
    }
}
