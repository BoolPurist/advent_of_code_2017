package solutions.day_6;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzelInput;
import solutions.ProducesSolution;

import java.util.Arrays;

public class ParserDaySix implements ParsePuzzelInput {

    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var data = Arrays.stream(input.trim().split("\\s+")).map(e -> Integer.parseInt(e)).toList();
        return new SolverDaySix(data, task);
    }

}
