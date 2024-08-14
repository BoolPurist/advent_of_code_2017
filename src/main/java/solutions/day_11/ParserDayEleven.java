package solutions.day_11;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

import java.util.Arrays;

public class ParserDayEleven implements ParseInput {
    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsed = Arrays.stream(input.split(",")).map(HexagonDirection::fromStr).toList();
        return switch (task) {
            case FIRST -> new SolverDayEleven(parsed);
            case SECOND -> new SolverDayPart2Eleven(parsed);
        };

    }
}
