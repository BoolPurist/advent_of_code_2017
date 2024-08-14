package solutions.day_7;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;
import utils.ReadOnlyList;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class ParserDaySeven implements ParseInput {
    private static final Pattern regexLineProgramOfTower = Pattern.compile(
            "(?<programName>\\w+)\\s+\\((?<weight>\\d+)\\)(\\s+->\\s+(?<children>(\\w+,\\s*)*+(\\w+)))?");

    public static ProgramOfTower parseLine(String line) {
        Objects.requireNonNull(line);
        var match = regexLineProgramOfTower.matcher(line);
        if (!match.matches()) {
            throw new IllegalArgumentException("Invalid line for a program of tower: " + line);
        }
        final var programName = match.group("programName");
        final var textWeight = match.group("weight");
        final var weight = Integer.parseInt(textWeight);
        final var childrenUnparsed = match.group("children");
        final List<String> children = childrenUnparsed == null ? List.of() : splitByComma(childrenUnparsed);
        return new ProgramOfTower(programName, weight, children);
    }

    private static List<String> splitByComma(String line) {
        return Arrays.stream(line.split(",")).map(String::trim).toList();
    }

    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsedLines = input.lines().map(ParserDaySeven::parseLine);
        return new SolverDaySeven(new ReadOnlyList<>(parsedLines), task);
    }
}
