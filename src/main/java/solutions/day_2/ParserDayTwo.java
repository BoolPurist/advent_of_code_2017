package solutions.day_2;

import solutions.ParseInput;
import solutions.ProducesSolution;

import java.util.Arrays;

public final class ParserDayTwo implements ParseInput {
    @Override
    public ProducesSolution parse(String input) {
        var parsedLines = input.lines()
                .map(nextLine -> Arrays.stream(nextLine.split("\\s+"))
                        .map(Integer::parseInt).toList())
                .toList();
        return new SolverDayTwo(parsedLines);
    }
}
