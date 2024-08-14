package solutions;

public interface ParseInput {
    ProducesSolution parse(String input, GivenTask task) throws InvalidInputException;
}
