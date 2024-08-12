package solutions.day_9;

import solutions.GivenTask;
import solutions.ProducesSolution;

public class SolverDayNine implements ProducesSolution {
    private final String input;

    public SolverDayNine(String input) {
        this.input = input;
    }


    @Override
    public String produce(GivenTask task) {
        return switch (task) {
            case FIRST -> String.valueOf(parseStream().groupCount());
            case SECOND -> String.valueOf(parseStream().garbageCount());
        };

    }


    @SuppressWarnings("java:S2589")
    // Sonarlint thinks that variable "isInGarbage" is always true
    private GroupGarbageStats parseStream() {
        var groupCounter = 0;
        var groupScore = 0;
        var skipNextChar = false;
        var isInGarbage = false;
        var garbageCount = 0;

        for (int i = 0; i < input.length(); i++) {
            final var currentChar = input.charAt(i);
            if (skipNextChar) {
                skipNextChar = false;
            } else if (currentChar == '!') {
                skipNextChar = true;

            } else if (isInGarbage) {
                if (currentChar == '>') {
                    isInGarbage = false;
                } else {
                    garbageCount++;
                }
            } else if (!isInGarbage) {
                if (currentChar == '<') {
                    isInGarbage = true;
                } else if (currentChar == '{') {
                    groupCounter++;
                } else if (currentChar == '}') {
                    groupScore += groupCounter;
                    groupCounter--;
                }
            }
        }
        return new GroupGarbageStats(groupScore, garbageCount);
    }
}
