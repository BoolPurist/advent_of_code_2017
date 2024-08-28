package solutions.day_17;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

public final class Parser implements ParsePuzzleInput {
    public static PixelatedSpinLockParams parseLines(String input) {
        final var lines = input.lines().limit(2).toList();
        assert lines.size() == 2;
        final var endValue = Integer.parseInt(lines.getFirst());
        final var steps = Integer.parseInt(lines.getLast());
        return new PixelatedSpinLockParams(endValue, steps);
    }

    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsed = parseLines(input);
        return switch (task) {
            case FIRST -> new SolverPart1(parsed);
            case SECOND -> new SolverPart2(parsed);
        };
    }
}
