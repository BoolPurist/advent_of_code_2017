package solutions.day_14;

import solutions.day_10.KnotHashing;
import utils.Position;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

final class Mark2DGrid {
    private static final int UPPER_LIMIT = 4;
    // Using boxed Boolean over primitive boolean since an ArrayStoreException is thrown 
    // if the lines are collected into a final matrix array.
    private final Boolean[][] rawData;

    Mark2DGrid(Boolean[][] rawData) {
        this.rawData = rawData;
    }

    // Suppress    
    public static Mark2DGrid fromKnotHashes(List<String> knotHashes) {
        final var grid = knotHashes.stream()
                .map(KnotHashing::createKnotHashFrom)
                .map(Mark2DGrid::turnTextIntoBoolRow)
                .toArray(Boolean[][]::new);
        return new Mark2DGrid(grid);
    }

    private static boolean[] turnNumberIntoBoolRow(int number) {
        final var reverseSavingConsumer = new FourBitFromRightSequencer();
        SolverDayFourteen.traverseIntegerBitwise(number, UPPER_LIMIT, reverseSavingConsumer);
        return reverseSavingConsumer.getSequence();
    }

    private static Boolean[] turnTextIntoBoolRow(String line) {
        final var neededSize = line.length() * 4;
        var allBooleans = new Boolean[neededSize];
        var columnCounter = 0;
        for (int indexChar = 0; indexChar < line.length(); indexChar++) {
            final var currentChar = line.charAt(indexChar);
            final var charAsInt = Integer.parseInt(Character.toString(currentChar), 16);
            final var booleanValues = turnNumberIntoBoolRow(charAsInt);

            for (final var next : booleanValues) {
                allBooleans[columnCounter] = next;
                columnCounter++;
            }
        }
        return allBooleans;
    }

    public boolean accessGridCell(Position cell) {
        return rawData[cell.x()][cell.y()];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Mark2DGrid) obj;
        return Objects.equals(this.rawData, that.rawData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawData);
    }

    @Override
    public String toString() {
        return "Mark2DGrid[" +
                "rawData=" + rawData + ']';
    }

    private static class FourBitFromRightSequencer implements Consumer<Boolean> {
        private final boolean[] sequence = new boolean[UPPER_LIMIT];
        private int index = UPPER_LIMIT - 1;

        public boolean[] getSequence() {
            return sequence;

        }

        @Override
        public void accept(Boolean isOneBit) {
            sequence[index] = isOneBit;
            index--;
        }
    }
}
