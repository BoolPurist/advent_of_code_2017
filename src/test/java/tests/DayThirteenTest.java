package tests;

import commons.ComplexAssertions;
import commons.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_13.FirewallLayerConfiguration;
import solutions.day_13.ParserDayThirteen;
import solutions.day_13.SolverDayThirteen;
import utils.Pair;
import utils.Triple;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

final class DayThirteenTest {
    static final List<FirewallLayerConfiguration> EXAMPLE_PARSED = List.of(
            new FirewallLayerConfiguration(0, 3),
            new FirewallLayerConfiguration(1, 2),
            new FirewallLayerConfiguration(4, 4),
            new FirewallLayerConfiguration(6, 4)
    );
    private static final List<Integer> EXPECTED_FOR_EXAMPLE = List.of(0, 6);

    static Stream<Pair<Integer, List<Integer>>> rangeExpectedRoute() {
        return Stream.of(
                new Pair<>(0, List.of()),
                new Pair<>(1, List.of(0)),
                new Pair<>(2, List.of(0, 1)),
                new Pair<>(3, List.of(0, 1, 2, 1)),
                new Pair<>(4, List.of(0, 1, 2, 3, 2, 1))
        );

    }

    static Stream<Triple<Integer, Integer, Integer>> indexAndFirewallLayer() {
        return Stream.of(
                new Triple<>(0, 0, 0),
                new Triple<>(0, 1, 0),
                new Triple<>(1, 3, 1),
                new Triple<>(2, 3, 2),
                new Triple<>(3, 3, 1),
                new Triple<>(4, 4, 2),
                new Triple<>(6, 4, 0),
                new Triple<>(7, 4, 1),
                new Triple<>(9, 4, 3),
                new Triple<>(10, 4, 2),
                new Triple<>(11, 4, 1),
                new Triple<>(12, 4, 0),
                new Triple<>(13, 4, 1)
        );

    }

    @ParameterizedTest
    @MethodSource("indexAndFirewallLayer")
    void getCurrentPositionFromScanLayer(Triple<Integer, Integer, Integer> givenAndExpected) {
        final var givenIndex = givenAndExpected.first();
        final var givenLayer = givenAndExpected.second();
        final var expected = givenAndExpected.third();
        final var actual = SolverDayThirteen.fetchPositionInFirewallLayer(givenIndex, givenLayer);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("rangeExpectedRoute")
    void createCorrectUpAndDownRouteForRange(Pair<Integer, List<Integer>> givenAndExpected) {
        final var given = givenAndExpected.first();
        final var expected = givenAndExpected.second();
        final var actual = SolverDayThirteen.createScannerRoute(given);
        ComplexAssertions.assertList(expected, actual);
    }

    @Test
    void solveTask1() {
        final var input = """
                0: 3
                1: 2
                4: 4
                6: 4
                """;

        Utils.assertCase(input, new ParserDayThirteen(), GivenTask.FIRST, "24");
    }

    @Test
    void solveTask2() {
        final var input = """
                0: 3
                1: 2
                4: 4
                6: 4
                """;

        Utils.assertCase(input, new ParserDayThirteen(), GivenTask.SECOND, "10");
    }

    @Test
    void getIndicesWhenCaught() {

        final var solver = new SolverDayThirteen(EXAMPLE_PARSED);
        final var mapping = solver.createMapIndexToHeight();
        final var actual = solver.indicesWhenCaught(mapping);

        ComplexAssertions.assertList(EXPECTED_FOR_EXAMPLE, actual);

        final var emptyActual = solver.indicesWhenCaught(new HashMap<>());
        ComplexAssertions.assertList(List.of(), emptyActual);

    }
}
