package solutions.day_2;

import solutions.GivenTask;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

import java.util.Arrays;

public final class ParserDayTwo implements ParsePuzzleInput {
    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) {
        var parsedLines = input.lines()
                .map(nextLine -> Arrays.stream(nextLine.split("\\s+"))
                        .map(Integer::parseInt).toList())
                .toList();
        return new SolverDayTwo(parsedLines, task);
    }
}
