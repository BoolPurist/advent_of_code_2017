package solutions.day_5;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzelInput;
import solutions.ProducesSolution;

public class ParserDayFive implements ParsePuzzelInput {

    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var data = input.lines().map(e -> Integer.parseInt(e)).toList();
        return new SolverDayFive(data, task);
    }

}
