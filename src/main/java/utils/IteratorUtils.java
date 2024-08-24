package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class IteratorUtils {
    private IteratorUtils() {
    }

    public static <T> List<T> iteratorToReadonlyList(Iterator<T> iterator) {
        var tmpBuffer = new ArrayList<T>();
        iterator.forEachRemaining(tmpBuffer::add);
        return tmpBuffer.stream().toList();
    }
}
