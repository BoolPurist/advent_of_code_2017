import org.junit.jupiter.api.Test;
import solutions.GivenTask;
import solutions.day_2.ParserDayTwo;

public class DayTwoTest {

    @Test
    public void differenceBetweenMinMax() {
        var input = Utils.tryGetResource(getClass(), "day_2_example.txt");
        Utils.assertCase(input, new ParserDayTwo(), GivenTask.FIRST, "18");

    }

    @Test
    public void divisionBetweenEven() {
        var input = Utils.tryGetResource(getClass(), "day_2_t2_example.txt");
        Utils.assertCase(input, new ParserDayTwo(), GivenTask.SECOND, "9");

    }

}
