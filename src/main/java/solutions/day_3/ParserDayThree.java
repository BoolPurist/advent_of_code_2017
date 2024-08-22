package solutions.day_3;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

public final class ParserDayThree implements ParsePuzzleInput {
    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        try {
            final var data = Integer.parseInt(input);
            return new SolverDayThree(data, task);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Digit is not an integer");
        }
    }

}
