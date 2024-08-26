package solutions.day_16;

public sealed interface DanceMove permits SpinMove, ExchangeMove, PartnerMove {
    void applyMoveTo(DancingPrograms applyTo);
}
