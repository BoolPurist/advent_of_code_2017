package solutions.day_15;

import java.util.Iterator;
import java.util.Objects;

public final class Generator implements Iterator<Long> {

    private static final long DIVIDING_FACTOR = 2147483647;
    private final long productFactor;
    private int currentIndex = 0;
    private long currentValue;
    private int length;

    public Generator(long startingValue, long productFactor, int length) {
        this.currentValue = startingValue;
        this.productFactor = productFactor;
        this.length = length;
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
        currentIndex++;
        currentValue *= productFactor;
        currentValue %= DIVIDING_FACTOR;
        return currentValue;
    }
}
