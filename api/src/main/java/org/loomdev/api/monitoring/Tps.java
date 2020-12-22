package org.loomdev.api.monitoring;

/**
 * Represents server TPS (ticks per second).
 */
public interface Tps {

    double getTps();

    double getTps(TpsInterval interval);

    enum TpsInterval {
        MINUTES_1,
        MINUTES_5,
        MINUTES_15
    }
}
