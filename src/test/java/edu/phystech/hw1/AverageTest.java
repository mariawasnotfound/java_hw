package edu.phystech.hw1;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AverageTest {

    private static double average(int... numbers) {
        int n = numbers.length;
        if (n == 0) {
            return 0;
        }
        int sum = 0;
        for (int i: numbers) {
            sum += i;
        }
        double res = (double) sum / n;
        return res;
    }


    @Test
    public void calculateAverage() {
        Assertions.assertEquals(5d, average(5));
        Assertions.assertEquals(4d, average(3, 5));
        Assertions.assertEquals(4d, average(3, 4, 5));

        Assertions.assertEquals(0, average());
        Assertions.assertEquals(3.5, average(1, 2, 3, 4, 5, 6));
        Assertions.assertEquals(6.5, average(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
    }
}
