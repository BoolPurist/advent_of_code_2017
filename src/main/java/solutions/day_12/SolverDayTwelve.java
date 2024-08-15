package solutions.day_12;

import solutions.ProducesSolution;

import java.util.*;

public class SolverDayTwelve implements ProducesSolution {
    private final List<ProgramWithPipes> parsed;

    public SolverDayTwelve(List<ProgramWithPipes> parsed) {
        this.parsed = parsed;
    }

    public static Set<String> calcProgramGroupFrom(String name, Map<String, Set<String>> programsAndPipes) {
        if (!programsAndPipes.containsKey(name)) {
            throw new IllegalArgumentException(name + "does not exits in the given map for programs with pipes");
        }

        var visitedNodes = new HashSet<String>();
        visitedNodes.add(name);
        Queue<String> toVisit = new ArrayDeque<>();
        toVisit.add(name);

        while (!toVisit.isEmpty()) {
            final var nextToVisit = toVisit.poll();
            final var children = programsAndPipes.get(nextToVisit);
            assert children != null;
            for (var child : children) {
                if (visitedNodes.add(child)) {
                    toVisit.add(child);
                }
            }
        }
        return visitedNodes;
    }

    public static Map<String, Set<String>> createGraphFromLines(Collection<ProgramWithPipes> programs) {
        var graph = new HashMap<String, Set<String>>();
        for (final var program : programs) {
            final var children = program.children();
            final var programId = program.id();
            graph.merge(programId, children, (oldValue, newValue) -> {
                oldValue.addAll(newValue);
                return oldValue;
            });
            for (final var child : children) {
                graph.merge(child, new HashSet<>(List.of(programId)), (oldValue, newValue) -> {
                    oldValue.addAll(newValue);
                    return oldValue;
                });
            }
        }

        return graph;
    }

    static String solutionAsOutput(Set<String> toConvert) {
        final var counter = toConvert.size();
        final var allNodes = toConvert.stream().sorted().toList();
        final var counterStr = String.valueOf(counter);
        return String.format("Counter: %s%nNodes: %s", counterStr, allNodes);
    }

    @Override
    public String produce() {
        final var programsConnectedWithPipes = createGraphFromLines(parsed);
        final var programGroupFrom = calcProgramGroupFrom("0", programsConnectedWithPipes);
        return solutionAsOutput(programGroupFrom);
    }
}
