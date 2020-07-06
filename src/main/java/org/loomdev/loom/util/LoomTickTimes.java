package org.loomdev.loom.util;

import org.loomdev.api.monitoring.TickTimes;

public class LoomTickTimes implements TickTimes {

    public final InternalTickTimes tickTimes5s = new InternalTickTimes(100);
    public final InternalTickTimes tickTimes10s = new InternalTickTimes(200);
    public final InternalTickTimes tickTimes60s = new InternalTickTimes(1200);
    
    @Override
    public double getAverage(TickTimesInterval tickTimesInterval) {
        switch (tickTimesInterval){
            case SECONDS_5:
                return tickTimes5s.getAverage();
            case SECONDS_10:
                return tickTimes10s.getAverage();
            case SECONDS_60:
                return tickTimes60s.getAverage();
        }
        throw new IllegalArgumentException("Invalid TickTimesInterval");
    }

    @Override
    public long[] getTimes(TickTimesInterval tickTimesInterval) {
        switch (tickTimesInterval){
            case SECONDS_5:
                return tickTimes5s.getTimes();
            case SECONDS_10:
                return tickTimes10s.getTimes();
            case SECONDS_60:
                return tickTimes60s.getTimes();
        }
        throw new IllegalArgumentException("Invalid TickTimesInterval");
    }

    public void addTickTimes(int index, long time) {
        this.tickTimes5s.add(index, time);
        this.tickTimes10s.add(index, time);
        this.tickTimes60s.add(index, time);
    }
    
    private static class InternalTickTimes {
        private final long[] times;

        public InternalTickTimes(int length) {
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
}
