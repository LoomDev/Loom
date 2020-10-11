package org.loomdev.loom.math;

public class TickTimesTracker {
    private final long[] times;

    public TickTimesTracker(int length) {
        times = new long[length];
    }

    public void add(int index, long time) {
        times[index % times.length] = time;
    }

    public long[] getTimes() {
        return times.clone();
    }

    public double getAverage() {
        long total = 0L;
        for (long value : times) {
            total += value;
        }
        return ((double) total / (double) times.length) * 1.0E-6D;
    }
}
