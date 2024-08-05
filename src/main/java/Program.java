import cli.CliArgs;
import cli.NoInputProvidedException;
import cli.ParsedCliArgs;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import solutions.InvalidInputException;
import solutions.GivenTask;
import solutions.ParseInput;
import solutions.day_1.ParserDayOne;
import solutions.day_2.ParserDayTwo;
import solutions.day_3.ParserDayThree;
import solutions.day_4.ParserDayFour;
import solutions.day_5.ParserDayFive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Program {
    private static final ParseInput[] solutionList = { new ParserDayOne(), new ParserDayTwo(), new ParserDayThree(),
            new ParserDayFour(), new ParserDayFive() };

    public static void main(String[] args) {
        try {
            var parsed = parseArgs(args);
            var parsedArgs = parsed.args();
            if (parsedArgs.isHelp()) {
                parsed.commander().usage();
            }
            var fileInput = Program.tryGetInput(parsed);
            var dayNumber = parsedArgs.getDay() - 1;
            var hasSolution = dayNumber < solutionList.length;
            if (!hasSolution) {
                exitWithError(String.format("There is no solution in the given day " + (dayNumber + 1)));
            } else {
                calculateSolution(dayNumber, fileInput, parsedArgs.getTask());
            }
        } catch (IOException | NoInputProvidedException | InvalidInputException | ParameterException e) {
            exitWithError(e.getMessage());
        }
    }

    private static void calculateSolution(int dayNumber, String fileInput, GivenTask task)
            throws InvalidInputException {
        var parser = solutionList[dayNumber];
        var solver = parser.parse(fileInput);
        var solution = solver.produce(task);
        System.out.println("Solution");
        System.out.println("=".repeat(10));
        System.out.println(solution);
    }

    private static String tryGetInput(ParsedCliArgs parsedArgs) throws NoInputProvidedException, IOException {
        final var path = parsedArgs.args().getPath();
        final var expression = parsedArgs.args().getExpression();
        final var noFileGiven = path == null;
        final var noExpressionGiven = expression == null;

        if (noFileGiven && noExpressionGiven) {
            throw new NoInputProvidedException("No file path or expression provided for the input");
        } else if (!noExpressionGiven) {
            return expression;
        } else {
            return Files.readString(Paths.get(path));
        }
    }

    private static void exitWithError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
        System.exit(1);
    }

    private static ParsedCliArgs parseArgs(String[] args) throws ParameterException {
        var parsed = new CliArgs();
        var commander = JCommander.newBuilder()
                .addObject(parsed)
                .build();
        commander
                .parse(args);

        return new ParsedCliArgs(parsed, commander);

    }
}
