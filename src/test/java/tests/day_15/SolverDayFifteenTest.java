package tests.day_15;

import commons.Utils;
import org.junit.jupiter.api.Test;
import solutions.GivenTask;
import solutions.day_15.ParserDayFifteen;

class SolverDayFifteenTest {

    static final String INPUT = """
            Generator A starts with 65
            Generator B starts with 8921
            """;

    @Test
    void produce() {
        final var EXPECTED = "588";
        Utils.assertCase(INPUT, new ParserDayFifteen(), GivenTask.FIRST, EXPECTED);
    }
    

    @Test
    void producePart2() {
        final var EXPECTED = "309";
        Utils.assertCase(INPUT, new ParserDayFifteen(), GivenTask.SECOND, EXPECTED);
    }
}