package org.loomdev.loom.util.transformer;

import net.minecraft.util.math.EulerAngle;

public final class EulerAngleTransformer {

    private EulerAngleTransformer() {
        throw new UnsupportedOperationException("EulerAngleTransformer shouldn't be initialized.");
    }

    public static EulerAngle toMinecraft(org.loomdev.api.math.EulerAngle loomEulerAngle) {
        return new EulerAngle(
                loomEulerAngle.getPitch(),
                loomEulerAngle.getYaw(),
                loomEulerAngle.getRoll()
        );
    }

    public static org.loomdev.api.math.EulerAngle toLoom(EulerAngle mcEulerAngle) {
        return org.loomdev.api.math.EulerAngle.of(
                mcEulerAngle.getPitch(),
                mcEulerAngle.getYaw(),
                mcEulerAngle.getRoll()
        );
    }
}
