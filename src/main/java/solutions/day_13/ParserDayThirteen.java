package solutions.day_13;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzelInput;
import solutions.ProducesSolution;

public class ParserDayThirteen implements ParsePuzzelInput {
    private static FirewallLayerConfiguration parseLine(String line) {
        final var split = line.split(":");
        assert split.length >= 2;
        final var index = Integer.parseInt(split[0].trim());
        final var height = Integer.parseInt(split[1].trim());
        return new FirewallLayerConfiguration(index, height);
    }

    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsed = input.lines().map(ParserDayThirteen::parseLine).toList();

        return switch (task) {
            case FIRST -> new SolverDayThirteen(parsed);
            case SECOND -> new SolverDayPart2Thirteen(parsed);
        };
    }


}
