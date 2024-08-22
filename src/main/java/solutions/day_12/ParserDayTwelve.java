package solutions.day_12;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

public class ParserDayTwelve implements ParsePuzzleInput {
    private static final Pattern regexForLine = Pattern.compile("(?<parent>\\d+) <-> (?<children>(\\d+, )*+(\\d+))");

    public static ProgramWithPipes parseOneLine(String line) {
        final var resultOfRegex = regexForLine.matcher(line);
        if (!resultOfRegex.matches()) {
            throw new IllegalArgumentException("Line: " + line + " is not valid for program with pipes");
        }
        final var parent = resultOfRegex.group("parent");
        final var childrenUnsplitted = resultOfRegex.group("children");
        final var children = new HashSet<>(Arrays.stream(childrenUnsplitted.split(", ")).toList());

        return new ProgramWithPipes(parent, children);
    }

    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsedLines = input.lines().map(ParserDayTwelve::parseOneLine).toList();
        return switch (task) {
            case FIRST -> new SolverDayTwelve(parsedLines);
            case SECOND -> new SolverDayPart2Twelve(parsedLines);
        };
    }
}
