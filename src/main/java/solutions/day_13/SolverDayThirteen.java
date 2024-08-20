package solutions.day_13;

import solutions.ProducesSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class SolverDayThirteen extends AbstractSolverDayThirteen implements ProducesSolution {

    public SolverDayThirteen(List<FirewallLayerConfiguration> configurations) {
        super(configurations);
    }

    public static List<Integer> createScannerRoute(Integer range) {
        final var startToPeakPivot = IntStream.range(0, range).boxed();
        final var afterPeakPivot = range - 2;
        final var afterPeakPivotToEnd = IntStream.iterate(afterPeakPivot, current -> current > 0, current -> current - 1).boxed();
        return Stream.concat(startToPeakPivot, afterPeakPivotToEnd).toList();
    }

    public static Integer fetchPositionInFirewallLayer(Integer timeInPecoSecs, int height) {
        if (height < 2) {
            return 0;
        }
        final var interval = height + (height - 2);
        final var pointInInterval = timeInPecoSecs % interval;
        return pointInInterval < height ?
                pointInInterval : interval - pointInInterval;
    }

    public List<Integer> indicesWhenCaught(Map<Integer, Integer> configuration) {
        var indexWhereCaught = new ArrayList<Integer>();
        final int length = findHowManyFirewalls();
        if (length == 0) {
            return List.of();
        }
        for (int indexCurrentFirewall = 0; indexCurrentFirewall < length; indexCurrentFirewall++) {
            final var height = configuration.get(indexCurrentFirewall);
            if (height == null || height.equals(1)) {
                continue;
            }

            final var indexOfScanner = fetchPositionInFirewallLayer(indexCurrentFirewall, height);
            final var gotCaught = indexOfScanner.equals(0);
            if (gotCaught) {
                indexWhereCaught.add(indexCurrentFirewall);
            }
        }
        return indexWhereCaught.stream().toList();
    }

    private List<FoundIndicesAndHeight> matchIndexWithHeight(List<Integer> indices, Map<Integer, Integer> foundScanningFirewalls) {
        return indices.stream().map(index -> new FoundIndicesAndHeight(index, foundScanningFirewalls.get(index))).toList();
    }

    @Override
    public String produce() {
        final var mapped = createMapIndexToHeight();
        final var caught = indicesWhenCaught(mapped);
        final var caughtWithHeight = matchIndexWithHeight(caught, mapped);
        final var severityScore = caughtWithHeight.stream().mapToInt(element -> element.index() * element.height()).sum();

        return String.valueOf(severityScore);
    }

    private record FoundIndicesAndHeight(int index, int height) {
    }
}
