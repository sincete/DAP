package org.example;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics metrics) {
        return select(arr, 0, arr.length - 1, k, 0, metrics);
    }

    private static int select(int[] arr, int left, int right, int k, int depth, Metrics metrics) {
        if (left == right) return arr[left];

        metrics.updateDepth(depth);

        int pivot = medianOfMedians(arr, left, right, metrics);
        int pivotIndex = partition(arr, left, right, pivot, metrics);

        int length = pivotIndex - left + 1;

        if (k == length) {
            return arr[pivotIndex];
        } else if (k < length) {
            return select(arr, left, pivotIndex - 1, k, depth + 1, metrics);
        } else {
            return select(arr, pivotIndex + 1, right, k - length, depth + 1, metrics);
        }
    }

    private static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        while (left <= right) {
            while (arr[left] < pivot) {
                metrics.comparisons++;
                left++;
            }
            while (arr[right] > pivot) {
                metrics.comparisons++;
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left - 1;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];
        metrics.allocations++;

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        return medianOfMedians(medians, 0, numMedians - 1, metrics);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
