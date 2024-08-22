package solutions.day_12;

import solutions.ProvidesPuzzleSolution;

import java.util.*;

public final class SolverDayPart2Twelve implements ProvidesPuzzleSolution {
    private final List<ProgramWithPipes> parsed;

    public SolverDayPart2Twelve(List<ProgramWithPipes> parsed) {
        this.parsed = parsed;
    }

    public static List<Set<String>> calcAllGroups(Map<String, Set<String>> allPrograms) {
        var leftProgramsWithoutGroup = new ArrayDeque<>(allPrograms.keySet().stream().sorted().toList());
        var visitedPrograms = new HashSet<>();
        var foundGroups = new ArrayList<Set<String>>();
        while (!leftProgramsWithoutGroup.isEmpty()) {
            final var nextStart = leftProgramsWithoutGroup.poll();
            if (!visitedPrograms.contains(nextStart)) {
                final var toMergeInto = SolverDayTwelve.calcProgramGroupFrom(nextStart, allPrograms);
                visitedPrograms.addAll(toMergeInto);
                foundGroups.add(toMergeInto);
            }
        }
        return foundGroups.stream().toList();
    }


    private static String allGroupsToTxt(List<Set<String>> toConvert) {
        var output = new StringBuilder();
        final var firstLine = String.format("Group Count: %s%n", toConvert.size());
        output.append(firstLine);
        for (final var nextGroup : toConvert) {
            final var toAppend = SolverDayTwelve.solutionAsOutput(nextGroup);
            output.append(String.format("%s%n", toAppend));
        }
        return output.toString();

    }

    @Override
    public String produce() {
        final var programsPiped = SolverDayTwelve.createGraphFromLines(parsed);
        final var allGroups = calcAllGroups(programsPiped);

        return allGroupsToTxt(allGroups);
    }
}
