package solutions.day_5;

import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

public class ParserDayFive implements ParseInput {

    @Override
    public ProducesSolution parse(String input) throws InvalidInputException {
        final var data = input.lines().map(e -> Integer.parseInt(e)).toList();
        return new SolverDayFive(data);
    }

}
