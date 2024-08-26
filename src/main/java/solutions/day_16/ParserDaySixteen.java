package solutions.day_16;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;
import solutions.ProvidesPuzzleSolution;
import utils.Pair;

import java.util.Arrays;
import java.util.List;

public final class ParserDaySixteen implements ParsePuzzleInput {


    public static DanceMove parseForMove(String component) {
        final var prefix = component.substring(0, 1);

        return switch (prefix) {
            case "s" -> suffixToSpin(toSuffix(component));
            case "p" -> suffixToPartnering(toSuffix(component));
            case "x" -> suffixToExchange(toSuffix(component));
            default -> throw new IllegalArgumentException("Unknown component " + component);
        };
    }

    private static String toSuffix(String input) {
        return input.substring(1);
    }

    private static ExchangeMove suffixToExchange(String suffix) {
        final var parsed = spiltIntoTwo(suffix);
        final var leftNumber = Integer.parseInt(parsed.first());
        final var rightNumber = Integer.parseInt(parsed.second());

        return new ExchangeMove(leftNumber, rightNumber);
    }

    private static PartnerMove suffixToPartnering(String suffix) {
        final var parsed = spiltIntoTwo(suffix);

        return new PartnerMove(parsed.first().charAt(0), parsed.second().charAt(0));
    }

    private static Pair<String, String> spiltIntoTwo(String toSplit) {
        final var leftAndRight = toSplit.split("/");
        if (leftAndRight.length != 2) {
            throw new IllegalArgumentException(String.format("%s is not seperated by a '/'", toSplit));
        }
        return new Pair<>(leftAndRight[0], leftAndRight[1]);
    }

    private static SpinMove suffixToSpin(String suffix) {
        final var number = Integer.parseInt(suffix);
        return new SpinMove(number);
    }

    private Pair<Integer, List<DanceMove>> parseTwoLineInto(String input) {

        final var EXPECTED_LINES = 2;
        final var lines = input.lines().limit(EXPECTED_LINES).toList();

        assert lines.size() == EXPECTED_LINES;

        final var firstLine = lines.getFirst();
        final var secondLine = lines.getLast();

        final var howManyTimes = Integer.parseInt(firstLine);
        final var parsed = Arrays.stream(secondLine.split(",")).map(ParserDaySixteen::parseForMove).toList();
        return new Pair<>(howManyTimes, parsed);
    }

    @Override
    public ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsed = parseTwoLineInto(input);
        return switch (task) {
            case FIRST -> new SolverDaySixteen(parsed.second(), parsed.first());
            case SECOND -> new SolverDayPart2Sixteen(parsed.second(), parsed.first());
        };
    }
}
