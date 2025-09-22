package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingTest {
    private final Random random = new Random();

    @Test
    void testMergeSortCorrectness() {
        for (int t = 0; t < 50; t++) {
            int[] arr = random.ints(100, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            MergeSort sorter = new MergeSort();
            sorter.sort(arr);

            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testQuickSortCorrectnessAndDepth() {
        for (int t = 0; t < 50; t++) {
            int[] arr = random.ints(100, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            QuickSort sorter = new QuickSort(metrics);
            sorter.sort(arr, 0, arr.length - 1, 1);

            assertArrayEquals(expected, arr);

            int bound = (int) (2 * Math.floor(Math.log(arr.length) / Math.log(2)) + 5);
            assertTrue(metrics.maxDepth <= bound, "QuickSort recursion too deep");
        }
    }
}
