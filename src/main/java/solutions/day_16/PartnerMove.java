package solutions.day_16;

public record PartnerMove(char left, char right) implements DanceMove {
    @Override
    public void applyMoveTo(DancingPrograms applyTo) {
        applyTo.swapByName(left, right);
    }
}
