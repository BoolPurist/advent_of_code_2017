package solutions.day_9;

import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

public class ParserDayNine implements ParseInput {
    @Override
    public ProducesSolution parse(String input) throws InvalidInputException {
        return new SolverDayNine(input);
       
    }
}
