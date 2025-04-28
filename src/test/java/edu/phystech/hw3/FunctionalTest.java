package edu.phystech.hw3;

import java.util.List;
import java.util.function.Function;

import edu.phystech.hw3.shape.Disk;
import edu.phystech.hw3.shape.Shape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.BiFunction;

class Functional {

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> res = new ArrayList<>();
        for (T elem : list) {
            res.add(mapper.apply(elem));
        }
        return res;
    }

    public static <T, R> R reduce(List<T> list, BiFunction<R, T, R> reducer, R initial) {
        R res = initial;
        for (T elem : list) {
            res = reducer.apply(res, elem);
        }
        return res;
    }
}

public class FunctionalTest {

    @Test
    public void mapWorks() {
        Function<Integer, Integer> func = x -> x * x;
        Assertions.assertEquals(List.of(1, 4, 9, 16), Functional.map(List.of(1, 2, 3, 4), func));
        Assertions.assertEquals(List.of(), Functional.map(List.of(), func));
    }

    @Test
    public void mapHasRightSignatureTest() {
        List<Disk> diskList = List.of(new Disk(1), new Disk(2));
        Function<Shape, Double> getArea = Shape::getArea;

//        нужно подправить сигнутру метода map, чтобы этот код компилировался и отрабатывал
//        List<Double> expected = List.of(new Disk(1).getArea(), new Disk(2).getArea());
//        Assertions.assertEquals(expected, Functional.map(diskList, getArea));
    }

    @Test
    public void reduceTest() {
        Assertions.assertEquals(15, Functional.reduce(List.of(1, 2, 3, 4, 5), Integer::sum, 0));
        Assertions.assertEquals("herewegoagain", Functional.reduce(
                List.of("here", "we", "go", "again"), String::concat, ""));
        Assertions.assertEquals("", Functional.reduce(
                List.of(), String::concat, ""));
    }
}
