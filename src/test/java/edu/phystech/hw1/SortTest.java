package edu.phystech.hw1;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SortTest {

    private static int[] merge(int[] left, int[] right) {
        int l = left.length, r = right.length;
        int[] res = new int[l + r];
        int i = 0, j = 0, k = 0;
        while (i < l && j < r) {
            if (left[i] <= right[j]) {
                res[k++] = left[i++];
            } else {
                res[k++] = right[j++];
            }
        }
        while (i < l) {
            res[k++] = left[i++];
        }
        while (j < r) {
            res[k++] = right[j++];
        }
        return res;
    }

    private static int[] sort(int[] nums) {
        int n = nums.length;
        int mid = n / 2;
        if (n <= 1) {
            return nums;
        }
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = nums[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = nums[i];
        }
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    @Test
    public void sortWorks() {
        Assertions.assertArrayEquals(new int[]{1}, sort(new int[]{1}));
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void sortReturnsNewArray() {
        int[] input = {9, 1, 3, 11, 45, 499};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] sorted = sort(input);

        Assertions.assertArrayEquals(new int[]{1, 3, 9, 11, 45, 499}, sorted);
        Assertions.assertArrayEquals(copy, input);
    }
}
