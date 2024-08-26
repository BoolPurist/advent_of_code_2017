package tests.day_16;

import commons.Utils;
import org.junit.jupiter.api.Test;
import solutions.GivenTask;
import solutions.day_16.ParserDaySixteen;

final class SolveDayTest {

    static final String EXAMPLE_INPUT = """
            5
            s1,x3/4,pe/b
            """;

    @Test
    void solveTask1() {
        final var EXPECTED = "baedc";
        Utils.assertCase(EXAMPLE_INPUT, new ParserDaySixteen(), GivenTask.FIRST, EXPECTED);
    }

}
