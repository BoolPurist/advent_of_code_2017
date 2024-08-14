package solutions.day_11;

import solutions.ProducesSolution;

import java.util.HashSet;
import java.util.List;

public class SolverDayPart2Eleven implements ProducesSolution {
    private final List<HexagonDirection> route;

    public SolverDayPart2Eleven(List<HexagonDirection> route) {
        this.route = route;
    }

    @Override
    public String produce() {
        var solvedRoutes = new HashSet<HexagonPosition>();
        var maxDistance = -1;
        var currentPosition = HexagonPosition.origin();
        for (final var nextStep : route) {
            currentPosition = nextStep.move(currentPosition);
            final var didNotCalcRouteBefore = solvedRoutes.add(currentPosition);
            if (didNotCalcRouteBefore) {
                final var potentialNewMax = SolverDayEleven.calcShortestDistanceToZero(currentPosition);
                maxDistance = Math.max(maxDistance, potentialNewMax);
            }
        }
        return String.valueOf(maxDistance);
    }
}
