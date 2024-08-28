package solutions.day_17;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public final class EvolvingSpinLock {
    private final int stepsPerCycle;
    private final LinkedList<Integer> currentState = new LinkedList<>(List.of(0));
    private int currentIndex = 0;
    private ListIterator<Integer> currentPosition;

    public EvolvingSpinLock(int stepsPerCycle) {
        this.stepsPerCycle = stepsPerCycle;
        this.currentPosition = currentState.listIterator();
    }

    public int getFirstElement() {
        if (currentState.size() < 2) {
            throw new IllegalArgumentException("Spinning lock must get to size of 2 at least. Size is just " + currentState.size());
        }
        return currentState.get(1);
    }

    public int getAfterCurrent() {
        final var upperLimit = currentState.size() - 1;
        final var positionAfterCurrent = Math.min(upperLimit, currentIndex + 1);
        return currentState.get(positionAfterCurrent);
    }

    private void appendBlock(StringBuilder buffer, int start, int end) {
        for (int i = start; i < end; i++) {
            buffer.append(currentState.get(i));
            buffer.append(" ");
        }
    }

    public void nextCycle() {
        final var modFactor = currentState.size();
        assert !currentState.isEmpty() : "Empty state would lead to division by zero";

        final var endPosition = (currentIndex + stepsPerCycle) % modFactor;

        final var needToTraverseFromTheStart = endPosition < currentIndex;

        if (!needToTraverseFromTheStart) {
            traverseToRightBySteps(endPosition);
        } else {
            traverseFromStartByStep(endPosition);
        }

        insertAfterLastStep(modFactor);
    }

    private void traverseFromStartByStep(int endPosition) {
        currentPosition = currentState.listIterator();
        for (int i = 0; i < endPosition; i++) {
            currentPosition.next();
        }
    }

    private void traverseToRightBySteps(int endPosition) {
        final var difference = endPosition - currentIndex;
        assert endPosition > -1;
        for (int i = 0; i < difference; i++) {
            currentPosition.next();
        }
    }

    private void insertAfterLastStep(int modFactor) {
        currentPosition.next();

        currentPosition.add(modFactor);

        currentIndex = currentPosition.previousIndex();
        currentPosition.previous();
    }

    @Override
    public String toString() {
        if (currentState.isEmpty()) {
            return "";
        }
        final var stringBuilder = new StringBuilder();


        appendBlock(stringBuilder, 0, currentIndex);
        stringBuilder.append(String.format("(%d) ", currentState.get(currentIndex)));
        appendBlock(stringBuilder, currentIndex + 1, currentState.size());

        return stringBuilder.toString().trim();
    }
}
