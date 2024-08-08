import org.junit.jupiter.api.Test;
import solutions.GivenTask;
import solutions.day_5.ParserDayFive;

public class DayFiveTest {
    private static final String INPUT = """
            0
            3
            0
            1
            -3""";

    @Test
    public void calcStepsWhenLeavingList() {
        Utils.assertCase(INPUT, new ParserDayFive(), GivenTask.FIRST, "5");
    }

    @Test
    public void calcStepsWhenLeavingListTask2() {
        Utils.assertCase(INPUT, new ParserDayFive(), GivenTask.SECOND, "10");
    }
}
