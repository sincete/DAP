package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class SelectTest {

    @Test
    void testDeterministicSelectCorrectness() {
        Random rand = new Random();

        for (int t = 0; t < 100; t++) { // 100 случайных тестов
            int n = 20;
            int[] arr = rand.ints(n, -100, 100).toArray();
            int k = rand.nextInt(n);

            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            Metrics metrics = new Metrics();
            int selected = DeterministicSelect.select(arr.clone(), k, metrics);

            assertEquals(sorted[k], selected, "DeterministicSelect should return k-th smallest element");
        }
    }
}
