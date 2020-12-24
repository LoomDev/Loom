package org.loomdev.api.monitoring;

/**
 * Represents server tick times.
 */
public interface TickTimes {

    double getAverage(TickTimesInterval interval);

    long[] getTimes(TickTimesInterval interval);

    enum TickTimesInterval {
        SECONDS_5,
        SECONDS_10,
        SECONDS_60
    }
}
