package solutions.day_5;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

public class ParserDayFive implements ParsePuzzleInput {

    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var data = input.lines().map(e -> Integer.parseInt(e)).toList();
        return new SolverDayFive(data, task);
    }

}
