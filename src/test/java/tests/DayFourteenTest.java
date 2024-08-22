package tests;

import commons.ComplexAssertions;
import commons.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_14.ParserDayFourteen;
import solutions.day_14.SolverDayFourteen;

import java.util.List;
import java.util.stream.Stream;

final class DayFourteenTest {
    private static final String INPUT = "flqrgnkx";

    private static Stream<GivenNumberExpectedOneBits> givenNumbersExpectedOneBits() {
        return Stream.of(
                new GivenNumberExpectedOneBits(0, 0),
                new GivenNumberExpectedOneBits(1, 1),
                new GivenNumberExpectedOneBits(2, 1),
                new GivenNumberExpectedOneBits(3, 2),
                new GivenNumberExpectedOneBits(4, 1),
                new GivenNumberExpectedOneBits(20, 2),
                new GivenNumberExpectedOneBits(15, 4)
        );
    }

    @Test
    void ExampleWordToZeroTo127Prefixed() {
        final var expectedSize = 3;
        final var prefix = "-";
        final var actual = SolverDayFourteen.createNumberedPrefixedWith(INPUT, prefix, expectedSize);
        final var expectedSequence = List.of("flqrgnkx-0", "flqrgnkx-1", "flqrgnkx-2");
        ComplexAssertions.assertList(expectedSequence, actual);
    }

    @ParameterizedTest
    @MethodSource("givenNumbersExpectedOneBits")
    void numberToHowManyOneBits(GivenNumberExpectedOneBits givenAndExpected) {
        final var given = givenAndExpected.number();
        final var expected = givenAndExpected.foundOneBits();
        final var actual = SolverDayFourteen.extractNumberOfOnesFrom(given);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void exampleWordToHowManyOneBits() {
        final var EXPECTED = "8108";
        Utils.assertCase(INPUT, new ParserDayFourteen(), GivenTask.FIRST, EXPECTED);
    }

    @Test
    void day2NumberOfRegions() {
        final var EXPECTED = "1242";
        Utils.assertCase(INPUT, new ParserDayFourteen(), GivenTask.SECOND, EXPECTED);
    }

    private record GivenNumberExpectedOneBits(int number, int foundOneBits) {

    }
}
