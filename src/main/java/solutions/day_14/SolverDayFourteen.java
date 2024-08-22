package solutions.day_14;

import solutions.ProvidesPuzzleSolution;
import solutions.day_10.KnotHashing;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class SolverDayFourteen implements ProvidesPuzzleSolution {
    private static final String PREFIX = "-";
    private static final int HOW_MANY_TIMES = 128;
    private final String unPrefixedHash;

    public SolverDayFourteen(String unPrefixedHash) {
        this.unPrefixedHash = unPrefixedHash;
    }

    public static List<String> createNumberedPrefixedWith(final String input, final String prefix, int howManyTimes) {
        return IntStream.range(0, howManyTimes).boxed().map(nextNumber -> String.format("%s%s%d", input, prefix, nextNumber)).toList();
    }

    public static void traverseIntegerBitwise(int number, int upperLimit, Consumer<Boolean> onNextBit) {
        for (int nextBitIndex = 0, mask = 1; nextBitIndex < upperLimit; nextBitIndex++, mask <<= 1) {
            final var andResult = number & mask;
            final var isOneBit = andResult >= 1;
            onNextBit.accept(isOneBit);
        }
    }


    public static int extractNumberOfOnesFrom(int number) {
        final var consumer = new Consumer<Boolean>() {
            private int foundOneBits = 0;

            public int getFoundOneBits() {
                return foundOneBits;
            }

            @Override
            public void accept(Boolean isOneBit) {
                if (isOneBit.equals(Boolean.TRUE)) {
                    foundOneBits++;
                }

            }
        };

        traverseIntegerBitwise(number, 32, consumer);
        return consumer.getFoundOneBits();
    }

    private static int countHowManyOnesFromLines(Stream<String> lines) {
        return lines
                .map(KnotHashing::createKnotHashFrom)
                .map(tooLong -> tooLong.split(""))
                .map(knotHash -> Arrays.stream(knotHash)
                        .map(singleChar -> Integer.parseInt(singleChar, 16))
                        .map(SolverDayFourteen::extractNumberOfOnesFrom).reduce(0, Integer::sum))
                .reduce(0, Integer::sum);

    }


    @Override
    public String produce() {
        final var allLines = createNumberedPrefixedWith(unPrefixedHash, PREFIX, HOW_MANY_TIMES);

        final var counts = countHowManyOnesFromLines(allLines.stream());
        return String.valueOf(counts);
    }
}
