package solutions.day_15;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Generator implements Iterator<Long> {

    private static final long DIVIDING_FACTOR = 2147483647;
    private final long productFactor;
    private final int length;
    private int currentIndex = 0;
    private long currentValue;
    private long multipleDivider = 1;

    public Generator(long startingValue, long productFactor, int length) {
        this.currentValue = startingValue;
        this.productFactor = productFactor;
        this.length = length;
    }

    public void setMultipleDivider(long multipleDivider) {
        this.multipleDivider = multipleDivider;
    }


    @Override
    public String toString() {
        return "Generator{" +
                "currentValue=" + currentValue +
                ", currentIndex=" + currentIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Generator generator = (Generator) o;
        return currentValue == generator.currentValue && currentIndex == generator.currentIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentValue, currentIndex);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < length;
    }

    @Override
    public Long next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        currentValue = calcNewCurrentValue();
        return currentValue;
    }

    private long calcNewCurrentValue() {
        var newCurrentValue = currentValue;
        do {
            newCurrentValue *= productFactor;
            newCurrentValue %= DIVIDING_FACTOR;
        } while (newCurrentValue % this.multipleDivider != 0);
        return newCurrentValue;
    }

}
