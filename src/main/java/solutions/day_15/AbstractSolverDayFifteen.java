package solutions.day_15;

public class AbstractSolverDayFifteen {

    public static final long A_MULTIPLY_FACTOR = 16807;
    public static final long B_MULTIPLY_FACTOR = 48271;
    protected static final int MILLIONS = (int) Math.pow(10, 6);
    private static final long NUMBER_BITS_JUDGING_MASK = (((long) Math.pow(2, 16)) - 1);
    protected final GeneratorStartingValue judge;

    public AbstractSolverDayFifteen(GeneratorStartingValue judge) {
        this.judge = judge;
    }

    protected int howManyTimesThePairsMatch(Generator generatorA, Generator generatorB) {
        var howManyTimesTheSame = 0;
        while (generatorA.hasNext() && generatorB.hasNext()) {
            final var currentAValue = generatorA.next() & NUMBER_BITS_JUDGING_MASK;
            final var currentBValue = generatorB.next() & NUMBER_BITS_JUDGING_MASK;
            if (currentAValue == currentBValue) {
                howManyTimesTheSame++;
            }

        }
        return howManyTimesTheSame;
    }
}
