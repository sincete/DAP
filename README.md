## Architecture
- Each algorithm is in a separate class.
- `Metrics` class collects data.
- `MetricsCSVWriter` saves results into `metrics.csv`.
- Entry point: `Main.java`.

## Algorithms and Recurrence
- **MergeSort**  
  Splits array into 2 parts, sorts recursively, then merges.  
  T(n) = 2T(n/2) + O(n) → O(n log n).

- **QuickSort**  
  Splits array by pivot, sorts recursively.  
  Average: O(n log n), worst: O(n²).

- **Deterministic Select (Median of Medians)**  
  Splits array into groups of 5, finds medians, recurses.  
  O(n).

- **Closest Pair of Points**  
  Splits points into 2 halves, finds min in each, checks middle strip.  
  O(n log n).

## Results
Metrics are saved in `metrics.csv`.  
Example columns:
- comparisons
- allocations
- recursion depth
- time (ms)

Data can be used for charts.

## Summary
Theory aligns with practice, as MergeSort and QuickSort perform efficiently on large arrays, Deterministic Select is linear but slower on small inputs, and Closest Pair demonstrates the expected $O(n \log n)$ complexity.
