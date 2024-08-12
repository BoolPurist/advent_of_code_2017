package tests;

import commons.Utils;
import org.junit.jupiter.api.Test;
import solutions.GivenTask;
import solutions.day_5.ParserDayFive;

class DayFiveTest {
    private static final String INPUT = """
            0
            3
            0
            1
            -3""";

    @Test
    void calcStepsWhenLeavingList() {
        Utils.assertCase(INPUT, new ParserDayFive(), GivenTask.FIRST, "5");
    }

    @Test
    void calcStepsWhenLeavingListTask2() {
        Utils.assertCase(INPUT, new ParserDayFive(), GivenTask.SECOND, "10");
    }
}
