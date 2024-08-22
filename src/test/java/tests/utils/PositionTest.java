package tests.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.GridBoundary;
import utils.Position;

import java.util.stream.Stream;

class PositionTest {

    private static final GridBoundary smallGrid = new GridBoundary(0, 2, 0, 2);

    private static Stream<PositionWithBoundary> testCasesBoundaryChecks() {
        return Stream.of(
                new PositionWithBoundary(
                        new Position(1, 1), DirectionToGo.UP, smallGrid, new Position(1, 2)
                ),
                new PositionWithBoundary(
                        new Position(1, 2), DirectionToGo.UP, smallGrid, new Position(1, 2)
                ),
                new PositionWithBoundary(
                        new Position(1, -2), DirectionToGo.UP, smallGrid, new Position(1, 0)
                ),
                new PositionWithBoundary(
                        new Position(1, 1), DirectionToGo.LEFT, smallGrid, new Position(0, 1)
                ),
                new PositionWithBoundary(
                        new Position(0, 1), DirectionToGo.LEFT, smallGrid, new Position(0, 1)
                ),
                new PositionWithBoundary(
                        new Position(1, 1), DirectionToGo.RIGHT, smallGrid, new Position(2, 1)
                ),
                new PositionWithBoundary(
                        new Position(2, 1), DirectionToGo.RIGHT, smallGrid, new Position(2, 1)
                ),
                new PositionWithBoundary(
                        new Position(1, 1), DirectionToGo.DOWN, smallGrid, new Position(1, 0)
                ),
                new PositionWithBoundary(
                        new Position(1, 0), DirectionToGo.DOWN, smallGrid, new Position(1, 0)
                )
        );
    }


    @ParameterizedTest
    @MethodSource("testCasesBoundaryChecks")
    void moveAndDetectsBoundaryViolation(PositionWithBoundary testCase) {
        final var before = testCase.before();
        final var boundary = testCase.boundary();
        final var actual = switch (testCase.whereToGo()) {
            case UP -> before.upWithinBounding(boundary);
            case DOWN -> before.downWithinBounding(boundary);
            case LEFT -> before.leftWithinBounding(boundary);
            case RIGHT -> before.rightWithinBounding(boundary);
        };
        Assertions.assertEquals(testCase.expected(), actual, () -> String.format("Before: %s%nDirection %s", testCase.before(), testCase.whereToGo()));
    }

    private enum DirectionToGo {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private record PositionWithBoundary(Position before, DirectionToGo whereToGo, GridBoundary boundary,
                                        Position expected) {
    }

}
