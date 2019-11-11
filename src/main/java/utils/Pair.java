package utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A simple pair class to store pairs of elements of different types
 *
 * @param <T> Type for the first element
 * @param <S> Type for the second element
 */
@Getter
@EqualsAndHashCode
public class Pair<T, S> {
    private T first;
    private S second;

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }
}
