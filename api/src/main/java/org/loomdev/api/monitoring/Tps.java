package org.loomdev.api.monitoring;

public interface Tps {

    double getTps();

    double getTps(TpsInterval interval);

    enum TpsInterval {
        MINUTES_1,
        MINUTES_5,
        MINUTES_15
    }
}
