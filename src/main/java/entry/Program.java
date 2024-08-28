package entry;

import cli.CliArgs;
import cli.NoInputProvidedException;
import cli.ParsedCliArgs;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import solutions.GivenTask;
import solutions.ParsePuzzleInput;
import solutions.day_1.ParserDayOne;
import solutions.day_10.ParserDayTen;
import solutions.day_11.ParserDayEleven;
import solutions.day_12.ParserDayTwelve;
import solutions.day_13.ParserDayThirteen;
import solutions.day_14.ParserDayFourteen;
import solutions.day_15.ParserDayFifteen;
import solutions.day_16.ParserDaySixteen;
import solutions.day_2.ParserDayTwo;
import solutions.day_3.ParserDayThree;
import solutions.day_4.ParserDayFour;
import solutions.day_5.ParserDayFive;
import solutions.day_6.ParserDaySix;
import solutions.day_7.ParserDaySeven;
import solutions.day_8.ParserDayEight;
import solutions.day_9.ParserDayNine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Program {
    private static final ParsePuzzleInput[] solutionList = {new ParserDayOne(), new ParserDayTwo(), new ParserDayThree(),
            new ParserDayFour(), new ParserDayFive(), new ParserDaySix(), new ParserDaySeven(), new ParserDayEight(), new ParserDayNine(),
            new ParserDayTen(), new ParserDayEleven(), new ParserDayTwelve(), new ParserDayThirteen(), new ParserDayFourteen(),
            new ParserDayFifteen(), new ParserDaySixteen(), new solutions.day_17.Parser(),
    };

    public static void main(String[] args) {
        try {
            trySolveGivenTask(args);
        } catch (Exception e) {
            exitWithError(e.getMessage());
        }
    }

    private static String calculateSolution(int dayNumber, String fileInput, GivenTask task) {
        var parser = solutionList[dayNumber];
        var solver = parser.parse(fileInput, task);
        return solver.produce();
    }

    private static void printSolution(String solution) {
        System.out.println("Solution");
        System.out.println("=".repeat(10));
        System.out.println(solution);
    }

    private static String tryGetInput(ParsedCliArgs parsedArgs) throws IOException {
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

    private static void trySolveGivenTask(String[] args) throws IOException {
        var parsed = parseArgs(args);
        var parsedArgs = parsed.args();
        if (parsedArgs.isHelp()) {
            parsed.commander().usage();
        }
        var fileInput = Program.tryGetInput(parsed);
        var dayNumber = parsedArgs.getDay() - 1;
        var hasSolution = dayNumber < solutionList.length;
        if (!hasSolution) {
            exitWithError(String.format("There is no solution in the given day %s", (dayNumber + 1)));
        } else {
            final var solution = calculateSolution(dayNumber, fileInput, parsedArgs.getTask());
            printSolution(solution);
        }

    }
}
