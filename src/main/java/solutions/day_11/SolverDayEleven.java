package solutions.day_11;

import solutions.ProducesSolution;

import java.util.List;

public final class SolverDayEleven implements ProducesSolution {

    private static final int NO_VERTICAL = 0;
    private static final int NO_HORIZONTAL = 0;
    private static final int MOVE_BY_ONE_HEXAGON = 1;
    private final List<HexagonDirection> route;

    public SolverDayEleven(List<HexagonDirection> route) {
        this.route = route;
    }

    /**
     * @param route sequence of directions to follow. Following all these directions results in a
     *              final position, end of the route
     * @return coordinate resulting by going through the route from the origin.
     */
    public static HexagonPosition moveFromZeroByRoute(List<HexagonDirection> route) {
        var currentPosition = HexagonPosition.origin();
        for (final var nextStep : route) {
            currentPosition = nextStep.move(currentPosition);
        }
        return currentPosition;
    }

    /**
     * @param start point from which the route to the origin (0, 0) starts
     * @return shortest distance between the parameter "start" and the origin
     */
    public static int calcShortestDistanceToZero(HexagonPosition start) {
        final var targetPosition = HexagonPosition.origin();

        var traveledDistance = 0;
        var currentPosition = start;

        while (!currentPosition.equals(targetPosition)) {
            final var currentX = currentPosition.x();
            final var currentY = currentPosition.y();

            var toAddToDistance = MOVE_BY_ONE_HEXAGON;

            if (currentX == NO_HORIZONTAL) {
                currentPosition = targetPosition;

                // Moving along the y-axis upwards or downwards 
                // is always the fastest way
                toAddToDistance = (int) Math.floor(Math.abs(currentY));
            } else if (currentY == NO_VERTICAL) {
                final var moveFactor = moveHalfUnitTowardsOrigin(currentX);
                final var newX = currentX + moveFactor;
                final var newY = currentY + moveFactor;

                currentPosition = new HexagonPosition(newX, newY);
            } else {
                final var newX = currentX + moveHalfUnitTowardsOrigin(currentX);
                final var newY = currentY + moveHalfUnitTowardsOrigin(currentY);

                currentPosition = new HexagonPosition(newX, newY);
            }

            traveledDistance += toAddToDistance;
        }

        assert traveledDistance > -1;

        return traveledDistance;
    }

    /**
     * Calculation for getting a number to add to an x or y coordinate.
     * <p>
     * By not adding it directly to a coordinate, one can reuse the calculation
     * in cases in which x and y coordinate should be moved into the same direction.
     *
     * @param coordinate From which one wants to get closer to the origin.
     * @return signed factor for adding to coordinate to get closer to the origin
     */
    private static double moveHalfUnitTowardsOrigin(double coordinate) {
        final var moveFactor = 0.5 * Math.signum(coordinate);
        return -moveFactor;
    }

    @Override
    public String produce() {
        final var start = moveFromZeroByRoute(route);
        final var distance = calcShortestDistanceToZero(start);
        return String.valueOf(distance);
    }

}
