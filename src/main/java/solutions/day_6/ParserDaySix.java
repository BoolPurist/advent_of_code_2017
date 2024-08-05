package solutions.day_6;

import java.util.Arrays;

import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

public class ParserDaySix implements ParseInput {

    @Override
    public ProducesSolution parse(String input) throws InvalidInputException {
        final var data = Arrays.stream(input.trim().split("\\s+")).map(e -> Integer.parseInt(e)).toList();
        return new SolverDaySix(data);
    }

}
