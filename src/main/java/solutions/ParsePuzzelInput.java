package solutions;

public interface ParsePuzzelInput {
    ProducesSolution parse(String input, GivenTask task) throws InvalidInputException;
}
