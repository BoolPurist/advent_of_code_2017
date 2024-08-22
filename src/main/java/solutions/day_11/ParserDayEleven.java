package solutions.day_11;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

import java.util.Arrays;

public class ParserDayEleven implements ParsePuzzleInput {
    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsed = Arrays.stream(input.split(",")).map(HexagonDirection::fromStr).toList();
        return switch (task) {
            case FIRST -> new SolverDayEleven(parsed);
            case SECOND -> new SolverDayPart2Eleven(parsed);
        };

    }
}
