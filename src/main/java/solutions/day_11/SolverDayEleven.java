package solutions.day_11;

import solutions.ProducesSolution;

import java.util.List;

public final class SolverDayEleven implements ProducesSolution {

    private final List<HexagonDirection> route;

    public SolverDayEleven(List<HexagonDirection> route) {
        this.route = route;
    }

    public static HexagonPosition moveFromZeroByRoute(List<HexagonDirection> route) {
        var currentPosition = HexagonPosition.zero();
        for (final var nextStep : route) {
            currentPosition = nextStep.move(currentPosition);
        }
        return currentPosition;
    }

    public static int calcShortestDistanceToZero(HexagonPosition start) {
        var traveledDistance = 0;
        var currentPosition = start;
        final var targetPosition = HexagonPosition.zero();
        while (!currentPosition.equals(targetPosition)) {
            final var currentX = currentPosition.x();
            final var currentY = currentPosition.y();
            if (currentX == 0) {
                traveledDistance += (int) Math.floor(Math.abs(currentY));
                break;
            } else if (currentY == 0) {
                final var moveFactor = moveHalfUnitTowardsOrigin(currentX);
                final var newX = currentX + moveFactor;
                final var newY = currentY + moveFactor;
                currentPosition = new HexagonPosition(newX, newY);
            } else {
                final var newX = currentX + moveHalfUnitTowardsOrigin(currentX);
                final var newY = currentY + moveHalfUnitTowardsOrigin(currentY);
                currentPosition = new HexagonPosition(newX, newY);
            }
            traveledDistance++;
        }
        return traveledDistance;
    }

    private static double moveHalfUnitTowardsOrigin(double direction) {
        final var moveFactor = 0.5 * Math.signum(direction);
        return -moveFactor;
    }

    @Override
    public String produce() {
        final var start = moveFromZeroByRoute(route);
        final var distance = calcShortestDistanceToZero(start);
        return String.valueOf(distance);
    }

}
