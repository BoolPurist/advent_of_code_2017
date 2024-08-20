package solutions.day_9;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzelInput;
import solutions.ProducesSolution;

public class ParserDayNine implements ParsePuzzelInput {
    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        return new SolverDayNine(input, task);

    }
}
