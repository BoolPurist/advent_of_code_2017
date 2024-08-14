package solutions.day_10;

import solutions.GivenTask;
import solutions.ProducesSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolverDayTen implements ProducesSolution {
    public static final int STANDARD_LENGTH = 256;

    private final List<Integer> lengthInput;
    private final int length;
    private GivenTask task;

    public SolverDayTen(List<Integer> data, int length, GivenTask task) {
        this.lengthInput = data;
        this.length = length;
        this.task = task;
    }

    private static Integer calcHash(int[] toCheck) {
        if (toCheck.length < 2) {
            throw new IllegalArgumentException("Input array has to have at least two elements");
        }
        return toCheck[0] * toCheck[1];
    }

    private static void reverseInLength(int range, int leftIndex, int[] toReverse) {
        final var length = toReverse.length;
        var rightIndex = (leftIndex + (range - 1)) % length;
        var steps = range / 2;
        for (int currentStep = 0; currentStep < steps; currentStep++) {
            final var tmp = toReverse[leftIndex];
            toReverse[leftIndex] = toReverse[rightIndex];
            toReverse[rightIndex] = tmp;
            leftIndex++;
            leftIndex %= length;
            rightIndex--;
            rightIndex = rightIndex < 0 ? length - 1 : rightIndex;
        }
    }

    public static AfterRound produceReversedSequence(final AfterRound forThisRound, final List<Integer> lengthSequence) {
        var toReverse = forThisRound.reversed();
        var length = toReverse.length;
        var currentSkipSize = forThisRound.skipSize();
        var leftIndex = forThisRound.currentPosition();
        for (final var nextReverseSize : lengthSequence) {
            reverseInLength(nextReverseSize, leftIndex, toReverse);
            final var jumpAmount = nextReverseSize + currentSkipSize;
            currentSkipSize++;
            leftIndex += jumpAmount;
            leftIndex %= length;
        }
        return new AfterRound(currentSkipSize, leftIndex, toReverse);
    }


    private static List<Integer> xorSequenceIn16Blocks(int[] input) {

        final var XOR_BLOCK_SIZE = 16;
        var counter = 0;
        final var length = input.length;
        var xoredBlocks = new ArrayList<Integer>();
        while (counter < length) {
            var upTo = counter + XOR_BLOCK_SIZE;
            var currentValue = input[counter];
            counter++;
            assert upTo <= length;
            while (counter < upTo) {
                currentValue ^= input[counter];
                counter++;
            }
            xoredBlocks.add(currentValue);
        }

        return xoredBlocks;
    }

    public static String solveTask2By(List<Integer> lengthSequence, int length) {
        final int ROUNDS = 64;

        var seq = IntStream.range(0, length).toArray();
        var lastReversed = seq;
        var forNextRound = AfterRound.firstRound(seq);
        for (int i = 0; i < ROUNDS; i++) {
            forNextRound = produceReversedSequence(forNextRound, lengthSequence);
            lastReversed = forNextRound.reversed();
        }
        final var hexaNumbers = xorSequenceIn16Blocks(lastReversed);
        assert hexaNumbers.size() == 16;
        final var output = hexaNumbers.stream().map(toHexa -> {
            final var maybeMissingZero = Integer.toHexString(toHexa);
            return maybeMissingZero.length() == 1 ? "0" + maybeMissingZero : maybeMissingZero;
        }).collect(Collectors.joining());
        assert output.length() == 32;

        return output;
    }

    public List<Integer> getLengthInput() {
        return this.lengthInput;
    }

    @Override
    public String produce() {
        return switch (task) {
            case FIRST -> solveTask1();
            case SECOND -> solveTask2();
        };
    }

    private String solveTask1() {
        var startSequence = IntStream.range(0, length).toArray();
        var toReverse = produceReversedSequence(AfterRound.firstRound(startSequence), this.lengthInput);
        return String.valueOf(calcHash(toReverse.reversed()));
    }

    private String solveTask2() {
        return solveTask2By(this.lengthInput, this.length);
    }

}
