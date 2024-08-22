package solutions.day_14;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

public class ParserDayFourteen implements ParsePuzzleInput {
    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        return switch (task) {
            case FIRST -> new SolverDayFourteen(input);
            case SECOND -> new SolverDayPart2Fourteen(input);
        };
    }
}
