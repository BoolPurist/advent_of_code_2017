package solutions.day_16;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class DancingPrograms {
    private static final char START_OF_ALPHABET = 'a';
    private ArrayList<Character> programs;

    public DancingPrograms(final int howMany) {
        if (howMany == 0) {
            throw new IllegalArgumentException("How many programs must be positive");
        }
        programs = new ArrayList<>(howMany);
        final var upperLimit = START_OF_ALPHABET + howMany;
        for (int charCounter = START_OF_ALPHABET; charCounter < upperLimit; charCounter++) {
            final var nextToAdd = (char) charCounter;
            programs.add(nextToAdd);
        }
    }

    public List<Character> getPrograms() {
        return programs.stream().toList();
    }

    public void swap(final int left, final int right) {
        if (isOutOfBoundsAccess(left)) {
            throw new IllegalArgumentException(String.format("Left index (%d) out of bounds. Valid range is between %d and %d", left, 0, programs.size()));
        }
        if (isOutOfBoundsAccess(right)) {
            throw new IllegalArgumentException(String.format("Right index (%d) out of bounds. Valid range is between %d and %d", right, 0, programs.size()));
        }
        final var tmp = programs.get(left);
        programs.set(left, programs.get(right));
        programs.set(right, tmp);
    }

    public void swapByName(final char left, final char right) {
        final var indexLeft = programs.indexOf(left);
        if (indexLeft < 0) {
            throw new NotFoundProgramException(String.format("There is no program called (%s)", left));
        }
        final var indexRight = programs.indexOf(right);
        if (indexRight < 0) {
            throw new NotFoundProgramException(String.format("There is no program called (%s)", right));
        }

        swap(indexLeft, indexRight);
    }

    public void spinSoManyTimes(final int spinAmount) {
        final var newSequence = new ArrayDeque<>(programs);
        for (int i = 0; i < spinAmount; i++) {
            final var frontToAdd = newSequence.removeLast();
            newSequence.addFirst(frontToAdd);
        }
        assert newSequence.size() == programs.size();
        programs = new ArrayList<>(newSequence);
    }

    private boolean isOutOfBoundsAccess(final int index) {
        return index < 0 || programs.size() <= index;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder(programs.size());
        for (final var toAppend : programs) {
            builder.append(toAppend);
        }
        return builder.toString();
    }
}
