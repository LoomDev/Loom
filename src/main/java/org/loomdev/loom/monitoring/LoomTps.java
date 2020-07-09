package org.loomdev.loom.monitoring;

import org.loomdev.api.monitoring.Tps;
import org.loomdev.loom.math.MovingAverage;

import java.math.BigDecimal;

public class LoomTps implements Tps {

    public static final int SAMPLE_INTERVAL = 20;
    public static final int TICK_TIME = 1000000000 / org.loomdev.loom.math.MovingAverage.TPS;
    private static final BigDecimal TPS_BASE = new BigDecimal("1E9").multiply(new java.math.BigDecimal(SAMPLE_INTERVAL));

    public final MovingAverage tps1 = new MovingAverage(60);
    public final MovingAverage tps5 = new MovingAverage(300);
    public final MovingAverage tps15 = new MovingAverage(900);

    @Override
    public double getTps() {
        return getTps(TpsInterval.MINUTES_1);
    }

    @Override
    public double getTps(TpsInterval tpsInterval) {
        switch (tpsInterval) {
            case MINUTES_1:
                return tps1.getAverage();
            case MINUTES_5:
                return tps5.getAverage();
            case MINUTES_15:
                return tps15.getAverage();
        }
        throw new IllegalArgumentException("Invalid TpsInterval");
    }

    public void add(long diff) {
        BigDecimal currentTps = TPS_BASE.divide(new BigDecimal(diff), 30, java.math.RoundingMode.HALF_UP);
        tps1.add(currentTps, diff);
        tps5.add(currentTps, diff);
        tps15.add(currentTps, diff);
    }
}
