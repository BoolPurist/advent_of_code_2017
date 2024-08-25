package solutions.day_15;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

public final class ParserDayFifteen implements ParsePuzzleInput {

    private static final String PREFIX_A = "Generator A starts with ";
    private static final String PREFIX_B = "Generator B starts with ";

    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var lines = input.lines().filter((line) -> !line.trim().isEmpty()).toList();
        assert lines.size() == 2;
        final var aGenerator = Long.parseLong(lines.getFirst().replaceFirst(PREFIX_A, ""), 10);
        final var bGenerator = Long.parseLong(lines.getLast().replaceFirst(PREFIX_B, ""), 10);
        final var generatorStarting = new GeneratorStartingValue(aGenerator, bGenerator);
        return switch (task) {
            case FIRST -> new SolverDayFifteen(generatorStarting);
            case SECOND -> new SolverDayPart2DayFifteen(generatorStarting);
        };
    }
}
