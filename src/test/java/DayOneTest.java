import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_1.ParserDayOne;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DayOneTest {

    // 1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the
    // second digit and the third digit (2) matches the fourth digit.
    // 1111 produces 4 because each digit (all 1) matches the next.
    // 1234 produces 0 because no digit matches the next.
    // 91212129 produces 9 because the only digit that matches the next one is the
    // last digit, 9.
    static Stream<Arguments> inputAndExpectedSolution() {
        return Stream.of(
                arguments("1122", "3"),
                arguments("1111", "4"),
                arguments("1234", "0"),
                arguments("91212129", "9"));
    }

    // For example:
    //
    // 1212 produces 6: the list contains 4 items, and all four digits match the
    // digit 2 items ahead.
    // 1221 produces 0, because every comparison is between a 1 and a 2.
    // 123425 produces 4, because both 2s match each other, but no other digit has a
    // match.
    // 123123 produces 12.
    // 12131415 produces 4
    static Stream<Arguments> inputAndExpectedSolutionTwo() {
        return Stream.of(
                arguments("1212", "6"),
                arguments("1221", "0"),
                arguments("123425", "4"),
                arguments("123123", "12"),
                arguments("12131415", "4"));
    }

    @ParameterizedTest
    @MethodSource("inputAndExpectedSolution")
    void assertDayOneTaskOne(String input, String expected) {
        Utils.assertCase(input, new ParserDayOne(), GivenTask.FIRST, expected);
    }

    @Test
    void assertDayOneReadFirstTask() {
        var input = Utils.tryGetResource(getClass(), "day_1_real.txt");
        Utils.assertCase(input, new ParserDayOne(), GivenTask.FIRST, "1029");

    }

    @Test
    void assertDayOneReadSecondTask() {
        var input = Utils.tryGetResource(getClass(), "day_1_real.txt");
        Utils.assertCase(input, new ParserDayOne(), GivenTask.SECOND, "1220");

    }

    @ParameterizedTest
    @MethodSource("inputAndExpectedSolutionTwo")
    void assertDayOneTaskTwo(String input, String expected) {
        Utils.assertCase(input, new ParserDayOne(), GivenTask.SECOND, expected);
    }
}
