package utils;

import java.util.List;
import java.util.stream.Stream;

public record ReadOnlyList<T>(List<T> list) {

    public ReadOnlyList(List<T> list) {
        this.list = list.stream().toList();
    }

    public ReadOnlyList(Stream<T> stream) {
        this(stream.toList());
    }
}
