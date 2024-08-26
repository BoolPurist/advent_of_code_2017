package solutions.day_16;

public record SpinMove(int spinAmount) implements DanceMove {
    @Override
    public void applyMoveTo(DancingPrograms applyTo) {
        applyTo.spinSoManyTimes(spinAmount);
    }
}
