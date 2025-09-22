package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class MetricsCSVWriter {
    private final String fileName;

    public MetricsCSVWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeHeaderIfNeeded() {
        try {
            java.io.File file = new java.io.File(fileName);
            if (!file.exists()) {
                try (FileWriter writer = new FileWriter(fileName, true)) {
                    writer.write("algorithm,n,time,comparisons,depth,allocations\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating CSV file", e);
        }
    }

    public void writeMetrics(String algorithm, int n, long timeNanos, Metrics metrics) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(String.format(
                    "%s,%d,%.6f,%d,%d,%d\n",
                    algorithm,
                    n,
                    timeNanos / 1_000_000.0,
                    metrics.comparisons,
                    metrics.maxDepth,
                    metrics.allocations
            ));
        } catch (IOException e) {
            throw new RuntimeException("Error writing to CSV", e);
        }
    }
}
