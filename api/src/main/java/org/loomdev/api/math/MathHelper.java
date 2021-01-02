package org.loomdev.api.math;

/**
 * Some useful math functions.
 */
public final class MathHelper {

    /**
     * Clamp a value between a minimum and maximum.
     *
     * @param value The value to clamp.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The clamped value.
     */
    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(min, value), max);
    }

    /**
     * Clamp a value between a minimum and maximum.
     *
     * @param value The value to clamp.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The clamped value.
     */
    public static long clamp(long value, long min, long max) {
        return Math.min(Math.max(min, value), max);
    }

    /**
     * Clamp a value between a minimum and maximum.
     *
     * @param value The value to clamp.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The clamped value.
     */
    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(min, value), max);
    }

    /**
     * Clamp a value between a minimum and maximum.
     *
     * @param value The value to clamp.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The clamped value.
     */
    public static float clamp(float value, float min, float max) {
        return Math.min(Math.max(min, value), max);
    }

    private MathHelper() {
        throw new UnsupportedOperationException("MathHelper shouldn't be initialized.");
    }
}
