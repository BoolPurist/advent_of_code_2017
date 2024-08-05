
import org.junit.jupiter.api.Test;

import solutions.GivenTask;
import solutions.day_6.ParserDaySix;

public class DaySixTest {

    @Test
    void findFirstRepetition() {
        final var input = "0 2 7 0";
        final var expected = "5";
        Utils.assertCase(input, new ParserDaySix(), GivenTask.First, expected);
    }
    @Test
    void findLoopOfFirstRepetition() {
        final var input = "0 2 7 0";
        final var expected = "4";
        Utils.assertCase(input, new ParserDaySix(), GivenTask.Second, expected);
    }
}
