package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_10.AfterRound;
import solutions.day_10.ParserDayTen;
import solutions.day_10.SolverDayTen;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class DayTenTest {
    //    AoC 2017 becomes 33efeb34ea91902bb2f59c9920caa6cd.
//1,2,3 becomes 3efbe78a8d82f29979031a4aa0b16a9d.
//1,2,4 becomes 63960835bcdc130f0b66d7ff4f6a5a8e.
    private static Stream<Arguments> task2ToHexaNumbers() {
        return Stream.of(
                Arguments.arguments("", "a2582a3a0e66e6e86e3812dcb672a272"),
                Arguments.arguments("AoC 2017", "33efeb34ea91902bb2f59c9920caa6cd"),
                Arguments.arguments("1,2,3", "3efbe78a8d82f29979031a4aa0b16a9d"),
                Arguments.arguments("1,2,4", "63960835bcdc130f0b66d7ff4f6a5a8e")
        );
    }

    private static void assertCase(String input, int length, String expectedOutput, int[] expectedSequence) {
        var parser = new ParserDayTen(length);
        var solver = (SolverDayTen) parser.parse(input);
        var output = solver.produce(GivenTask.FIRST);
        final var firstRound = AfterRound.firstRound(IntStream.range(0, length).toArray());
        var sequence = SolverDayTen.produceReversedSequence(firstRound, solver.getLengthInput());
        Assertions.assertArrayEquals(expectedSequence, sequence.reversed());
        Assertions.assertEquals(expectedOutput, output);
    }

    @ParameterizedTest
    @MethodSource("task2ToHexaNumbers")
    void turnIntoHexaSequenceTask2(String input, String expectedOutput) {
        final var codes = ParserDayTen.createParsedInputForTask2(input);
        final var solution = SolverDayTen.solveTask2By(codes, 256);
        Assertions.assertEquals(expectedOutput, solution);
    }

    @Test
    void convertLengthSequenceIntoACII() {
        final var input = "1,2,3";
        final var expected = "49,44,50,44,51";
        final var actual = ParserDayTen.convertLineToACIICodes(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void convertLengthSequenceACIIAndSuffix() {
        final var input = "1,2,3";
        final var expected = "49,44,50,44,51,17,31,73,47,23";
        final var actual = ParserDayTen.createACIICodesWithSuffix(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void reverseUntilHash() {
        final var input = "3, 4, 1, 5";
        final var expected = "12";
        assertCase(input, 5, expected, new int[]{3, 4, 2, 1, 0});
    }

    @Test
    void reverseUntilHashWithMoreAfterThree() {
        final var input = "3, 4, 1, 5,3";
        assertCase(input, 5, "0", new int[]{3, 0, 2, 1, 4});
    }

    @Test
    void reverseOneElementWithRangeOne() {
        final var input = "1,1,1";
        final var expected = "0";
        assertCase(input, 2, expected, new int[]{0, 1});
    }
}
