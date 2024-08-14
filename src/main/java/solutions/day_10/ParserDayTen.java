package solutions.day_10;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

import java.util.Arrays;
import java.util.List;

public class ParserDayTen implements ParseInput {
    private static final String SUFFIX = "17,31,73,47,23";
    private final int length;

    public ParserDayTen(int length) {
        this.length = length;
    }

    public ParserDayTen() {
        this.length = SolverDayTen.STANDARD_LENGTH;
    }

    public static String convertLineToACIICodes(String line) {
        if (line.isEmpty()) {
            return "";
        }
        var output = new StringBuilder();
        final var finalIndex = line.length() - 1;
        int currentCharIndex = 0;
        while (true) {
            final char currentChar = line.charAt(currentCharIndex);
            final int ACIICode = currentChar;
            if (ACIICode > 255) {
                throw new InvalidInputException("Invalid character '" + currentChar + "' at index " + currentCharIndex + ". Must not be greater than 255.");
            }
            output.append(ACIICode);
            if (currentCharIndex == finalIndex) {
                break;
            }
            output.append(",");
            currentCharIndex++;
        }
        return output.toString();

    }

    public static String createACIICodesWithSuffix(String input) {
        final var codes = convertLineToACIICodes(input);
        return codes + "," + SUFFIX;
    }

    public static List<Integer> createParsedInputForTask2(String input) {

        final var codes = convertLineToACIICodes(input);
        final var text = codes.isEmpty() ? SUFFIX : codes + "," + SUFFIX;
        return splitInputToParse(text);

    }

    public static List<Integer> splitInputToParse(String line) {
        if (line.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(line.split(",\\s*")).mapToInt(Integer::parseUnsignedInt).boxed().toList();
    }


    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        final var parsed = switch (task) {
            case FIRST -> splitInputToParse(input);
            case SECOND -> splitInputToParse(createACIICodesWithSuffix(input));
        };
        return new SolverDayTen(parsed, length, task);
    }
}
