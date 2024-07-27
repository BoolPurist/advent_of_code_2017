package solutions.day_3;

import solutions.GivenTask;
import solutions.ProducesSolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class SolverDayThree implements ProducesSolution {
    private static final int ALL_CORNERS = 4;
    private static final int INCREASE_OF_EDGE_LENGTH = 2;
    private final int number;

    public SolverDayThree(int number) {
        this.number = number;
    }

    private static int getNextSquare(HashMap<Position, Integer> currentNeighbors,
                                     HashMap<Integer, List<Position>> savedNeighbors, int ringLevel, Position currentPosition,
                                     Position... neighbors) {
        var sum = 0;
        for (var next : neighbors) {
            if (currentNeighbors.containsKey(next)) {
                sum += currentNeighbors.get(next);
            }
        }

        currentNeighbors.put(currentPosition, sum);
        var toAdjust = savedNeighbors.get(ringLevel);
        toAdjust.add(currentPosition);
        return sum;
    }

    private static int greaterSumSquareThan(int number) {

        if (number < 2) {
            return 2;
        }
        final var startPosition = new Position(0, 0);

        var currentNeighbors = new HashMap<Position, Integer>();
        var currentSavedNeightbors = new HashMap<Integer, List<Position>>();

        currentNeighbors.put(startPosition, 1);
        currentSavedNeightbors.put(0, new ArrayList<Position>(List.of(startPosition)));

        var edgeLength = 1;
        var currentPostition = startPosition.right();
        var sum = 0;
        for (int currentRing = 1; currentRing < Integer.MAX_VALUE; currentRing++) {
            currentSavedNeightbors.put(currentRing, new ArrayList<Position>());
            // righ edge going up
            for (int alongEdge = 0; alongEdge < edgeLength; alongEdge++) {
                var left = currentPostition.left();
                var down = currentPostition.down();
                var leftUp = left.up();
                var leftDown = left.down();
                sum = getNextSquare(currentNeighbors, currentSavedNeightbors, currentRing, currentPostition, left,
                        down, leftUp, leftDown);
                if (sum > number) {
                    return sum;
                }
                currentPostition = currentPostition.up();
            }
            final var edgeLengthUpLeft = edgeLength + 1;
            // upper edge going left
            for (int alongEdge = 0; alongEdge < edgeLengthUpLeft; alongEdge++) {
                var right = currentPostition.right();
                var down = currentPostition.down();
                var rightDown = right.down();
                var leftDown = currentPostition.left().down();
                sum = getNextSquare(currentNeighbors, currentSavedNeightbors, currentRing, currentPostition, right,
                        down, rightDown, leftDown);
                if (sum > number) {
                    return sum;
                }
                currentPostition = currentPostition.left();
            }
            // left edge going down
            for (int alongEdge = 0; alongEdge < edgeLengthUpLeft; alongEdge++) {
                var up = currentPostition.up();
                var right = currentPostition.right();
                var upRight = right.up();
                var downRight = right.down();
                sum = getNextSquare(currentNeighbors, currentSavedNeightbors, currentRing, currentPostition, up,
                        right, downRight, upRight);
                if (sum > number) {
                    return sum;
                }
                currentPostition = currentPostition.down();
            }
            // down edge going right
            final var edgeLengthRight = edgeLengthUpLeft + 1;
            for (int alongEdge = 0; alongEdge < edgeLengthRight; alongEdge++) {
                var up = currentPostition.up();
                var left = currentPostition.left();
                var leftUp = left.up();
                var rightUp = up.right();
                sum = getNextSquare(currentNeighbors, currentSavedNeightbors, currentRing, currentPostition, up,
                        left, leftUp, rightUp);
                if (sum > number) {
                    return sum;
                }
                currentPostition = currentPostition.right();
            }

            for (var toRemove : currentSavedNeightbors.remove(currentRing - 1)) {
                currentNeighbors.remove(toRemove);
            }
            edgeLength += 2;
        }

        return 0;
    }

    public static FoundRing calculateRing(int number) {
        if (number < 2) {
            return new FoundRing(0, 1, 1);
        }

        var edgeLength = 1;
        var highestNumberInRing = 9;
        var startNumber = 2;
        var currentRing = 1;
        while (highestNumberInRing < number) {
            edgeLength += INCREASE_OF_EDGE_LENGTH;
            final var toAdd = edgeLength * ALL_CORNERS + ALL_CORNERS;
            startNumber = highestNumberInRing + 1;
            highestNumberInRing += toAdd;
            currentRing++;
        }
        return new FoundRing(currentRing, startNumber, edgeLength);
    }

    private static Position calculateEndPosition(int number, FoundRing ring) {
        if (number == 1) {
            return new Position(0, 0);
        }
        final var startNumber = ring.startNumber();
        final var upWardsRange = startNumber + ring.range();
        final var ringLevel = ring.level();
        if (upWardsRange >= number) {
            final var difference = number - ring.startNumber();
            final var yFactor = ringLevel - 1;
            final var startPosition = new Position(ringLevel, -yFactor);
            return new Position(startPosition.x(), startPosition.y() + difference);
        }
        final var addToRanges = ring.range() + 1;
        final var leftWardRange = upWardsRange + addToRanges;
        if (leftWardRange >= number) {
            final var difference = number - upWardsRange;
            final var startPosition = new Position(ringLevel, ringLevel);
            return new Position(startPosition.x() - difference, startPosition.y());
        }
        final var downWardRange = leftWardRange + addToRanges;
        if (downWardRange >= number) {
            final var difference = number - leftWardRange;
            final var startPosition = new Position(-ringLevel, ringLevel);
            return new Position(startPosition.x(), startPosition.y() - difference);
        }
        final var rightWardRange = downWardRange + addToRanges;
        if (rightWardRange >= number) {
            final var difference = number - downWardRange;
            final var startPosition = new Position(-ringLevel, -ringLevel);
            return new Position(startPosition.x() + difference, startPosition.y());
        }
        throw new RuntimeException("Not reachable");
    }

    private static int getDistance(Position position) {
        var xAbs = Math.abs(position.x());
        var yAbs = Math.abs(position.y());
        return xAbs + yAbs;
    }

    @Override
    public String produce(GivenTask task) {

        switch (task) {
            case GivenTask.First: {
                final var ring = SolverDayThree.calculateRing(this.number);
                final var finalPosition = SolverDayThree.calculateEndPosition(number, ring);
                final var toReturn = SolverDayThree.getDistance(finalPosition);
                return String.valueOf(toReturn);
            }
            default:
                return String.valueOf(SolverDayThree.greaterSumSquareThan(this.number));
        }
    }

}
