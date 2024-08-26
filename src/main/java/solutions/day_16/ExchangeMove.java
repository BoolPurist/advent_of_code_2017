package solutions.day_16;

public record ExchangeMove(int left, int right) implements DanceMove {
    @Override
    public void applyMoveTo(DancingPrograms applyTo) {
        applyTo.swap(left, right);
    }
}
