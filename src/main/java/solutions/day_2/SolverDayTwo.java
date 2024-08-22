package solutions.day_2;

import solutions.GivenTask;
import solutions.ProvidesPuzzleSolution;

import java.util.List;

public final class SolverDayTwo implements ProvidesPuzzleSolution {
    private final List<List<Integer>> matrix;
    private GivenTask task;

    public SolverDayTwo(List<List<Integer>> matrix, GivenTask task) {
        this.matrix = matrix.stream().map(notImmutable -> notImmutable.stream().toList()).toList();
        this.task = task;
    }

    //5 9 2 8
//        9 4 7 3
//        3 8 6 5
//    In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
//    In the second row, the two numbers are 9 and 3; the result is 3.
//    In the third row, the result is 2.
//    In this example, the sum of the results would be 4 + 3 + 2 = 9.
    public static int calculateDifferenceForLine(List<Integer> currentLine) {
        var sum = 0;
        for (int index = 0; index < currentLine.size(); index++) {
            final var toDivide = currentLine.get(index);
            for (int subIndex = index - 1; -1 < subIndex; subIndex--) {
                var currentNom = currentLine.get(subIndex);
                sum += SolverDayTwo.division(toDivide, currentNom);
            }
            for (int subIndex = index + 1; subIndex < currentLine.size(); subIndex++) {
                var currentNom = currentLine.get(subIndex);
                sum += SolverDayTwo.division(toDivide, currentNom);
            }
        }
        return sum;
    }

    private static Integer division(Integer toDivide, Integer currentNom) {
        final var notTooBig = toDivide >= currentNom;
        if (notTooBig) {
            final var divisionRest = toDivide % currentNom;
            if (divisionRest == 0) {
                return toDivide / currentNom;
            }
        }
        return 0;
    }

    @Override
    public String produce() {
        switch (task) {
            case FIRST -> {
                return firstSolution();
            }
            case SECOND -> {
                return secondSolution();
            }
        }

        throw new IllegalArgumentException();
    }

    private String secondSolution() {
        var sum = 0;
        for (var currentLine : this.matrix) {
            sum += calculateDifferenceForLine(currentLine);
        }
        return String.valueOf(sum);
    }

    private String firstSolution() {
        var sum = 0;
        for (var currentLine : this.matrix) {
            final var min = currentLine.stream().min(Integer::compare).orElse(0);
            final var max = currentLine.stream().max(Integer::compare).orElse(0);

            final var varMinNotBiggerThanMax = min > max;
            if (!varMinNotBiggerThanMax) {
                sum += max - min;
            }
        }
        return String.valueOf(sum);
    }
}
