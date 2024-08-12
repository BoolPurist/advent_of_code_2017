package tests;

import commons.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_3.FoundRing;
import solutions.day_3.ParserDayThree;
import solutions.day_3.SolverDayThree;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

// Examples:
// Data from square 1 is carried 0 steps, since it's at the access port.
// Data from square 12 is carried 3 steps, such as: down, left, left.
// Data from square 23 is carried only 2 steps: up twice.
// Data from square 1024 must be carried 31 steps.
public final class DayThreeTest {

    static Stream<Arguments> ringLevels() {
        return Stream.of(
                arguments(1, new FoundRing(0, 1, 1)),
                arguments(12, new FoundRing(2, 10, 3)),
                arguments(23, new FoundRing(2, 10, 3)),
                arguments(48, new FoundRing(3, 26, 5)));
    }

    static Stream<Arguments> solutionsFromExamples() {
        return Stream.of(
                arguments("1", "0"),
                arguments("12", "3"),
                arguments("23", "2"),
                arguments("48", "5"),
                arguments("1024", "31"));
    }

    static Stream<Arguments> solutionsFromExampleTaskTwo() {
        return Stream.of(
                arguments("5", "10"),
                arguments("4", "5"),
                arguments("1", "2"),
                arguments("2", "4"),
                arguments("304", "330"),
                arguments("747", "806"));
    }

    @ParameterizedTest
    @MethodSource("ringLevels")
    void assertDayRingLevels(int number, FoundRing expectedRing) {
        var actualRing = SolverDayThree.calculateRing(number);
        assertEquals(expectedRing, actualRing);
    }

    @ParameterizedTest
    @MethodSource("solutionsFromExamples")
    void assertDayOneTaskOne(String input, String expected) {
        Utils.assertCase(input, new ParserDayThree(), GivenTask.FIRST, expected);
    }

    @ParameterizedTest
    @MethodSource("solutionsFromExampleTaskTwo")
    void assertDayOneTaskTwo(String input, String expected) {
        Utils.assertCase(input, new ParserDayThree(), GivenTask.SECOND, expected);
    }

}
