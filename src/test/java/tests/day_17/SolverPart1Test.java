package tests.day_17;

import commons.Utils;
import org.junit.jupiter.api.Test;
import solutions.GivenTask;
import solutions.day_17.Parser;

final class SolverPart1Test {

    @Test
    void solveTask1() {
        final var INPUT = """
                2017
                3""";
        final var EXPECTED = "638";
        Utils.assertCase(INPUT, new Parser(), GivenTask.FIRST, EXPECTED);
    }

}