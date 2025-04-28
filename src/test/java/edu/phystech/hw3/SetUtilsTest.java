package edu.phystech.hw3;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SetUtils {

    public static <T> Set<T> union(Set<T> first, Set<T> second) {
        Set<T> res = new HashSet<>(first);
        res.addAll(second);
        return res;
    }

    public static <T> Set<T> intersection(Set<T> first, Set<T> second) {
        Set<T> res = new HashSet<>(first);
        res.retainAll(second);
        return res;
    }

    public static <T> Set<T> difference(Set<T> first, Set<T> second) {
        Set<T> res = new HashSet<>(first);
        res.removeAll(second);
        return res;
    }

    public static <T> Set<T> symmetricDifference(Set<T> first, Set<T> second) {
        Set<T> union = union(first, second);
        Set<T> intersection = intersection(first, second);
        union.removeAll(intersection);
        return union;
    }
}

public class SetUtilsTest {

    @Test
    void unionTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5), SetUtils.union(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(firstSet, SetUtils.union(firstSet, secondSet));

    }

    @Test
    void intersectionTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.intersection(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(firstSet, SetUtils.intersection(firstSet, secondSet));

        firstSet = Set.of(1);
        secondSet = Set.of(1, 2, 3);

        Assertions.assertEquals(firstSet, SetUtils.intersection(firstSet, secondSet));

    }

    @Test
    void differenceTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(firstSet, SetUtils.difference(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.difference(firstSet, secondSet));

        firstSet = Set.of(1);
        secondSet = Set.of(1, 2, 3);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.difference(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 3);

        Assertions.assertEquals(Set.of(1), SetUtils.difference(firstSet, secondSet));
    }

    @Test
    void symmetricDifferenceTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5), SetUtils.symmetricDifference(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.symmetricDifference(firstSet, secondSet));

        firstSet = Set.of(1);
        secondSet = Set.of(1, 2, 3);

        Assertions.assertEquals(Set.of(2, 3), SetUtils.symmetricDifference(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 3);

        Assertions.assertEquals(Set.of(1, 3), SetUtils.symmetricDifference(firstSet, secondSet));

    }
}