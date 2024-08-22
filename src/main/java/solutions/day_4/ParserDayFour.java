package solutions.day_4;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserDayFour implements ParsePuzzleInput {

    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        var data = input.lines()
                .map(line -> Stream.of(line.split("\\s")).collect(Collectors.toList())

                ).collect(Collectors.toList());
        return new SolverDayFour(data, task);

    }

}
