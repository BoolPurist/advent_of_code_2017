package solutions.day_8;

public enum ModifierOperationKind {
    INCREMENT,
    DECREMENT;

    public static ModifierOperationKind fromText(String input) {
        return switch (input) {
            case "inc" -> ModifierOperationKind.INCREMENT;
            case "dec" -> ModifierOperationKind.DECREMENT;
            default -> throw new IllegalArgumentException("Unknown modifier operation kind: " + input);
        };

    }
}
