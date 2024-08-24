package tests.day_15;

import commons.Utils;
import org.junit.jupiter.api.Test;
import solutions.GivenTask;
import solutions.day_15.ParserDayFifteen;

class SolverDayFifteenTest {

    @Test
    void produce() {
        final var INPUT = """
                Generator A starts with 65
                Generator B starts with 8921
                """;
        final var EXPECTED = "588";
        Utils.assertCase(INPUT, new ParserDayFifteen(), GivenTask.FIRST, EXPECTED);
    }
}