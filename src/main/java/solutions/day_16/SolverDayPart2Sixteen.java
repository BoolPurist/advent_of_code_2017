package solutions.day_16;

import solutions.ProvidesPuzzleSolution;
import utils.Pair;

import java.util.List;

public class SolverDayPart2Sixteen extends AbstractSolverDaySixteen implements ProvidesPuzzleSolution {
    private static final int HOW_MANY_TIMES_REPEAT = 1000000000;

    public SolverDayPart2Sixteen(List<DanceMove> moves, int howManyTimes) {
        super(moves, howManyTimes);
    }

    private void moveTheRestAfterRepetition(DancingPrograms programs, int repetitionAt) {
        var leftTimes = HOW_MANY_TIMES_REPEAT % repetitionAt;
        for (
                int index = 0, leftCounter = 0;
                leftCounter < leftTimes;
                index++, leftCounter++, index %= moves.size()
        ) {
            final var nextMove = moves.get(index);
            nextMove.applyMoveTo(programs);
        }
    }

    private Pair<DancingPrograms, Integer> moveUntilRepetitionFound() {
        final var programs = new DancingPrograms(howManyTimes);
        final var atStart = programs.toString();
        var repetitionAt = 0;
        for (int i = 0; i < HOW_MANY_TIMES_REPEAT; i++) {
            for (var move : moves) {
                repetitionAt++;
                move.applyMoveTo(programs);
            }

            final var foundRepetition = programs.toString().equals(atStart);
            if (foundRepetition) {
                break;
            }
        }
        return new Pair<>(programs, repetitionAt);
    }

    @Override
    public String produce() {
        final var foundRepetition = moveUntilRepetitionFound();
        moveTheRestAfterRepetition(foundRepetition.first(), foundRepetition.second());
        return foundRepetition.first().toString();
    }
}
