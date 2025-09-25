package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testClosestPairSmall() {
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(7, 7),
                new Point(1, 1)
        };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closestPair(points, metrics);

        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testClosestPairTwoPoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(5, 12)
        };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closestPair(points, metrics);

        assertEquals(13.0, result, 1e-6); // расстояние по теореме Пифагора
    }
}
