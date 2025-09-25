package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class SortingTest {

    @Test
    void testQuickSortCorrectness() {
        int[] arr = {5, 3, 8, 4, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);

        assertArrayEquals(expected, arr, "QuickSort should correctly sort the array");
    }

    @Test
    void testMergeSortCorrectness() {
        int[] arr = {10, -1, 3, 7, 5};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr, "MergeSort should correctly sort the array");
    }
}
