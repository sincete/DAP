package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closestPair(Point[] points, Metrics metrics) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        return closestPairRecursive(sortedByX, metrics, 0);
    }

    private static double closestPairRecursive(Point[] points, Metrics metrics, int depth) {
        metrics.updateDepth(depth);
        int n = points.length;

        if (n <= 3) {
            return bruteForce(points, metrics);
        }

        int mid = n / 2;
        Point midPoint = points[mid];

        double dl = closestPairRecursive(Arrays.copyOfRange(points, 0, mid), metrics, depth + 1);
        double dr = closestPairRecursive(Arrays.copyOfRange(points, mid, n), metrics, depth + 1);

        double d = Math.min(dl, dr);

        Point[] strip = Arrays.stream(points)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .sorted(Comparator.comparingDouble(p -> p.y))
                .toArray(Point[]::new);

        return Math.min(d, stripClosest(strip, d, metrics));
    }

    private static double bruteForce(Point[] points, Metrics metrics) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                metrics.comparisons++;
                double dist = distance(points[i], points[j]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    private static double stripClosest(Point[] strip, double d, Metrics metrics) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                metrics.comparisons++;
                double dist = distance(strip[i], strip[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
