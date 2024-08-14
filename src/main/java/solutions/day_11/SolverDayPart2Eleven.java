package solutions.day_11;

import solutions.ProducesSolution;

import java.util.HashMap;
import java.util.List;

public class SolverDayPart2Eleven implements ProducesSolution {
    private final List<HexagonDirection> route;

    public SolverDayPart2Eleven(List<HexagonDirection> route) {
        this.route = route;
    }

    @Override
    public String produce() {
        var solvedRoutes = new HashMap<HexagonPosition, Integer>();
        var maxDistance = -1;
        var currentPosition = HexagonPosition.zero();
        for (final var nextStep : route) {
            currentPosition = nextStep.move(currentPosition);
            solvedRoutes.computeIfAbsent(currentPosition, SolverDayEleven::calcShortestDistanceToZero);
            maxDistance = Math.max(maxDistance, solvedRoutes.get(currentPosition));
        }
        return String.valueOf(maxDistance);
    }
}
