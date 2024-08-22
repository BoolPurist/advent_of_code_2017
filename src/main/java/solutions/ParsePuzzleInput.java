package solutions;

public interface ParsePuzzleInput {
    ProvidesPuzzleSolution parse(String input, GivenTask task) throws InvalidInputException;
}
