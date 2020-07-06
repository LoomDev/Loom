package org.loomdev.loom.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MovingAverage {

    private final int size;
    private long time;
    private int index;
    private final long[] times;

    private BigDecimal total;
    private final BigDecimal[] samples;

    private static final long SEC_IN_NANO = 1000000000;
    public static final int TPS = 20;

    public MovingAverage(int windowSize) {
        this.size = windowSize;
        this.time = windowSize * SEC_IN_NANO;
        this.total = getWindow(TPS).multiply(getWindow(SEC_IN_NANO)).multiply(getWindow(windowSize));
        this.samples = new java.math.BigDecimal[windowSize];
        this.times = new long[windowSize];
        for (int i = 0; i < windowSize; i++) {
            this.samples[i] = getWindow(TPS);
            this.times[i] = SEC_IN_NANO;
        }
    }

    private BigDecimal getWindow(long window) {
        return new BigDecimal(window);
    }

    public void add(BigDecimal value, long window) {
        time -= times[index];
        total = total.subtract(samples[index].multiply(getWindow(times[index])));
        samples[index] = value;
        times[index] = window;
        time += window;
        total = total.add(value.multiply(getWindow(window)));

        if (++index == size) {
            index = 0;
        }
    }

    public double getAverage() {
        return total.divide(getWindow(time), 30, RoundingMode.HALF_UP).doubleValue();
    }
}
