package org.loomdev.loom.math;

public final class MathHelp {
    private MathHelp() {}

    public static float clamp(float value, float bounds1, float bounds2) {
        float upper = Math.max(bounds1, bounds2);
        float lower = Math.min(bounds1, bounds2);
        return Math.min(Math.max(value, lower), upper);
    }
}
