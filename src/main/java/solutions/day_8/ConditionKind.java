package solutions.day_8;

public enum ConditionKind {
    EQUAL,
    NOT_EQUAL,
    GREATER_THAN,
    LESS_THAN,
    LESS,
    GREATER;

    public static ConditionKind parse(String input) {
        return switch (input) {
            case "==" -> EQUAL;
            case "!=" -> NOT_EQUAL;
            case ">" -> GREATER;
            case "<" -> LESS;
            case ">=" -> GREATER_THAN;
            case "<=" -> LESS_THAN;
            default -> throw new IllegalArgumentException("Unknown condition kind: " + input);
        };

    }
}
