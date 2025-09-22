package org.example;

import java.util.Arrays;

public class MergeSort {

    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics metrics) {
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length - 1, 0, metrics);
    }

    private static void sort(int[] arr, int[] buffer, int left, int right, int depth, Metrics metrics) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        int mid = (left + right) / 2;
        sort(arr, buffer, left, mid, depth + 1, metrics);
        sort(arr, buffer, mid + 1, right, depth + 1, metrics);

        merge(arr, buffer, left, mid, right, metrics);

        metrics.updateDepth(depth);
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                metrics.comparisons++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.comparisons++;
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int p = left; p <= right; p++) {
            arr[p] = buffer[p];
            metrics.allocations++;
        }
    }
}
