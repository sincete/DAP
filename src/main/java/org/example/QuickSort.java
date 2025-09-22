package org.example;

import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, 0, metrics);
    }

    private static void quickSort(int[] arr, int left, int right, int depth, Metrics metrics) {
        if (left >= right) return;

        metrics.updateDepth(depth);

        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);

        int partitionIndex = partition(arr, left, right, pivot, metrics);


        if (partitionIndex - 1 - left < right - (partitionIndex + 1)) {
            quickSort(arr, left, partitionIndex - 1, depth + 1, metrics);
            quickSort(arr, partitionIndex + 1, right, depth + 1, metrics);
        } else {
            quickSort(arr, partitionIndex + 1, right, depth + 1, metrics);
            quickSort(arr, left, partitionIndex - 1, depth + 1, metrics);
        }
    }

    private static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        int i = left;
        for (int j = left; j < right; j++) {
            metrics.comparisons++;
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
