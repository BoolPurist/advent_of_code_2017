package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.day_11.HexagonDirection;
import solutions.day_11.HexagonPosition;
import solutions.day_11.SolverDayEleven;

import java.util.List;
import java.util.stream.Stream;

final class DayElevenTest {
    //    ne,ne,ne is 3 steps away.
//    ne,ne,sw,sw is 0 steps away (back where you started).
//    ne,ne,s,s is 2 steps away (se,se).
//    se,sw,se,sw,sw is 3 steps away (s,s,sw).
    public static Stream<Arguments> routeWithExpectedEndPosition() {
        return Stream.of(
                Arguments.arguments(List.of(HexagonDirection.NORTH), new HexagonPosition(0, 1)),
                Arguments.arguments(List.of(HexagonDirection.SOUTH), new HexagonPosition(0, -1)),
                Arguments.arguments(List.of(HexagonDirection.SOUTH_EAST), new HexagonPosition(0.5, -0.5)),
                Arguments.arguments(List.of(HexagonDirection.SOUTH_WEST), new HexagonPosition(-0.5, -0.5)),
                Arguments.arguments(List.of(HexagonDirection.NORTH_EAST), new HexagonPosition(0.5, 0.5)),
                Arguments.arguments(List.of(HexagonDirection.NORTH_WEST), new HexagonPosition(-0.5, 0.5)),
                Arguments.arguments(
                        List.of(HexagonDirection.NORTH_EAST, HexagonDirection.NORTH_EAST, HexagonDirection.NORTH_EAST),
                        new HexagonPosition(1.5, 1.5)
                ),
                Arguments.arguments(
                        List.of(HexagonDirection.NORTH_EAST, HexagonDirection.NORTH_EAST, HexagonDirection.SOUTH, HexagonDirection.SOUTH),
                        new HexagonPosition(1.0, -1.0)
                ),
                Arguments.arguments(
                        List.of(HexagonDirection.NORTH_EAST, HexagonDirection.NORTH_EAST, HexagonDirection.SOUTH_WEST, HexagonDirection.SOUTH_WEST),
                        new HexagonPosition(0, 0)
                ),
                //    se,sw,se,sw,sw is 3 steps away (s,s,sw).
                Arguments.arguments(
                        List.of(HexagonDirection.SOUTH_EAST, HexagonDirection.SOUTH_WEST,
                                HexagonDirection.SOUTH_EAST,
                                HexagonDirection.SOUTH_WEST, HexagonDirection.SOUTH_WEST),
                        new HexagonPosition(-0.5, -2.5)
                )
        );
    }

    public static Stream<Arguments> pointAndTravelDistance() {
        return Stream.of(
                Arguments.arguments(new HexagonPosition(0, 1), 1),
                Arguments.arguments(new HexagonPosition(0, -1), 1),
                Arguments.arguments(new HexagonPosition(1, -1), 2),
                Arguments.arguments(new HexagonPosition(1, -3), 4),
                Arguments.arguments(new HexagonPosition(-0.5, -2.5), 3),
                Arguments.arguments(new HexagonPosition(1.0, -1.0), 2)
        );
    }

    @ParameterizedTest
    @MethodSource("pointAndTravelDistance")
    void shouldTraceBackWithShortestDistance(HexagonPosition start, int expectedDistance) {
        final var actual = SolverDayEleven.calcShortestDistanceToZero(start);
        Assertions.assertEquals(expectedDistance, actual);
    }

    @ParameterizedTest
    @MethodSource("routeWithExpectedEndPosition")
    void shouldMoveToPositionByRoute(List<HexagonDirection> directions, HexagonPosition expectedPositions) {
        final var actual = SolverDayEleven.moveFromZeroByRoute(directions);
        Assertions.assertEquals(expectedPositions, actual);
    }
}
