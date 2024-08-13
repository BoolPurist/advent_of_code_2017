package solutions.day_10;

public record AfterRound(int skipSize, int currentPosition, int[] reversed) {
    public static AfterRound firstRound(int[] reversed) {
        return new AfterRound(0, 0, reversed);
    }
}
