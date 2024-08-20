package solutions.day_13;

public record CatchDetector(int position, int height) {

    public boolean doesCatchAt(int time) {
        final var indexToCheck = time % height;
        return indexToCheck == position;
    }
}
