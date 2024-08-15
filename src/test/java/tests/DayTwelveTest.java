package tests;

import commons.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_12.ParserDayTwelve;
import solutions.day_12.ProgramWithPipes;
import solutions.day_12.SolverDayPart2Twelve;
import solutions.day_12.SolverDayTwelve;

import java.util.*;
import java.util.stream.Stream;

class DayTwelveTest {
    private final String exampleInput = """
            0 <-> 2
            1 <-> 1
            2 <-> 0, 3, 4
            3 <-> 2, 4
            4 <-> 2, 3, 6
            5 <-> 6
            6 <-> 4, 5
            """;

    private static Stream<Arguments> casesForLinesToParse() {
        return Stream.of(
                Arguments.arguments(
                        "1 <-> 1",
                        new ProgramWithPipes("1", new HashSet<>(List.of("1")))
                ),

                Arguments.arguments(
                        "2 <-> 3, 5",
                        new ProgramWithPipes("2", new HashSet<>(List.of("3", "5")))
                )
        );
    }

    @Test
    void shouldCalcAllGroups() {

        final var input = doneMapFromExample();
        final List<Set<String>> expected = List.of(
                new HashSet<>(List.of("0", "2", "3", "4", "5", "6")),
                new HashSet<>(List.of("1"))
        );

        final var actual = SolverDayPart2Twelve.calcAllGroups(input);

        Assertions.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            final var actualElement = actual.get(i);
            final var expectedElement = expected.get(i);
            Utils.assertSet(expectedElement, actualElement);
        }
    }

    @ParameterizedTest
    @MethodSource("casesForLinesToParse")
    void parseLinesCorrectly(String input, ProgramWithPipes expected) {
        final var actual = ParserDayTwelve.parseOneLine(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldGiveNumberForGivenProgramsWithPipe() {
        final var expected = """
                Counter: 6
                Nodes: [0, 2, 3, 4, 5, 6]""";
        Utils.assertCase(exampleInput, new ParserDayTwelve(), GivenTask.FIRST, expected);
    }

    private Map<String, Set<String>> doneMapFromExample() {
        var expected = new HashMap<String, Set<String>>();
        expected.put("0", new HashSet<>(List.of("2")));
        expected.put("1", new HashSet<>(List.of("1")));
        expected.put("2", new HashSet<>(List.of("0", "3", "4")));
        expected.put("3", new HashSet<>(List.of("2", "4")));
        expected.put("4", new HashSet<>(List.of("2", "3", "6")));
        expected.put("5", new HashSet<>(List.of("6")));
        expected.put("6", new HashSet<>(List.of("5", "4")));
        return expected;
    }

    @Test
    void calcDistanceFromZeroInExample() {
        final var input = doneMapFromExample();
        final var expected = new HashSet<>(List.of("0", "2", "3", "4", "5", "6"));

        final var actual = SolverDayTwelve.calcProgramGroupFrom("0", input);
        Utils.assertSet(expected, actual);
    }

    @Test
    void createGraphFromLines() {
        final var input = List.of(new ProgramWithPipes("0", new HashSet<>(List.of("2"))),
                new ProgramWithPipes("1", new HashSet<>(List.of("1"))),
                new ProgramWithPipes("2", new HashSet<>(List.of("0", "3", "4"))),
                new ProgramWithPipes("3", new HashSet<>(List.of("2", "4"))),
                new ProgramWithPipes("4", new HashSet<>(List.of("2", "3", "6"))),
                new ProgramWithPipes("5", new HashSet<>(List.of("6"))),
                new ProgramWithPipes("6", new HashSet<>(List.of("4", "5")))
        );
        final var actual = SolverDayTwelve.createGraphFromLines(input);
        final var expected = doneMapFromExample();

        Assertions.assertEquals(actual.size(), expected.size());

        for (final var entry : expected.entrySet()) {
            final var value = actual.get(entry.getKey());
            Assertions.assertNotNull(value);

            final var expectedValue = entry.getValue();
            Assertions.assertEquals(value.size(), expectedValue.size());
            for (final var key : value) {
                Assertions.assertTrue(expectedValue.contains(key));
            }
        }
    }
}