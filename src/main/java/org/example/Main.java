package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MetricsCSVWriter csvWriter = new MetricsCSVWriter("metrics.csv");
        csvWriter.writeHeaderIfNeeded();

        int[] sizes = {100, 1000, 5000, 10000}; // можно менять

        for (int n : sizes) {
            int[] array1 = randomArray(n);
            int[] array2 = array1.clone();
            int[] array3 = array1.clone();


            runMergeSort(array1, n, csvWriter);


            runQuickSort(array2, n, csvWriter);


            runSelect(array3, n, csvWriter);


            runClosestPair(n, csvWriter);
        }

        System.out.println("All results are saved in metrics.csv");
    }

    private static int[] randomArray(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(1_000_000);
        }
        return array;
    }

    private static void runMergeSort(int[] array, int n, MetricsCSVWriter csvWriter) {
        Metrics metrics = new Metrics();
        long start = System.nanoTime();
        MergeSort.sort(array, metrics);
        long end = System.nanoTime();
        csvWriter.writeMetrics("mergesort", n, end - start, metrics);
    }

    private static void runQuickSort(int[] array, int n, MetricsCSVWriter csvWriter) {
        Metrics metrics = new Metrics();
        long start = System.nanoTime();
        QuickSort.sort(array, metrics);
        long end = System.nanoTime();
        csvWriter.writeMetrics("quicksort", n, end - start, metrics);
    }

    private static void runSelect(int[] array, int n, MetricsCSVWriter csvWriter) {
        Metrics metrics = new Metrics();
        int k = n / 2;
        long start = System.nanoTime();
        int kth = DeterministicSelect.select(array, k, metrics);
        long end = System.nanoTime();
        csvWriter.writeMetrics("select(k=" + k + ")", n, end - start, metrics);
    }

    private static void runClosestPair(int n, MetricsCSVWriter csvWriter) {
        Metrics metrics = new Metrics();
        Random random = new Random();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
        }
        long start = System.nanoTime();
        double dist = ClosestPair.closestPair(points, metrics);
        long end = System.nanoTime();
        csvWriter.writeMetrics("closestpair", n, end - start, metrics);
    }
}
