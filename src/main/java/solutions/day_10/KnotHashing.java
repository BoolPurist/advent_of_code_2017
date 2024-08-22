package solutions.day_10;

import solutions.GivenTask;

public final class KnotHashing {
    private KnotHashing() {
    }

    public static String createKnotHashFrom(String input) {
        final var parser = new ParserDayTen();
        final var solver = parser.parse(input, GivenTask.SECOND);
        return solver.produce();
    }
}
