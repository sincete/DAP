package org.example;

public class Metrics {
    public long comparisons = 0;
    public long allocations = 0;
    public int maxDepth = 0;

       public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
    }

    public void updateDepth(int depth) {
        maxDepth = Math.max(maxDepth, depth);
    }
}
