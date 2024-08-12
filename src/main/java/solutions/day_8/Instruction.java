package solutions.day_8;


public record Instruction(String register, ModifierOperation modifier, OperationCondition condition) {
}
