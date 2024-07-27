
package solutions.day_3;

import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

public final class ParserDayThree implements ParseInput {
    @Override
    public ProducesSolution parse(String input) throws InvalidInputException {
        try {
            final var data = Integer.parseInt(input);
            return new SolverDayThree(data);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Digit is not an integer");
        }
    }

}
