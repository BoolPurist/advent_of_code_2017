package solutions.day_1;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

public final class ParserDayOne implements ParseInput {
    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        try {
            final var data = input.chars().map(digit -> digit - '0').toArray();
            return new SolverDayOne(data, task);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Digit is not an integer");
        }
    }

}
