package solutions.day_9;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

public class ParserDayNine implements ParsePuzzleInput {
    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        return new SolverDayNine(input, task);

    }
}
