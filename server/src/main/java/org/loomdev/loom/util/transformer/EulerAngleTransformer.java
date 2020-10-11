package org.loomdev.loom.util.transformer;


import org.jetbrains.annotations.NotNull;
import org.loomdev.api.math.EulerAngle;

public final class EulerAngleTransformer {

    private EulerAngleTransformer() {
        throw new UnsupportedOperationException("EulerAngleTransformer shouldn't be initialized.");
    }

    public static net.minecraft.util.math.EulerAngle toMinecraft(@NotNull EulerAngle loomEulerAngle) {
        return new net.minecraft.util.math.EulerAngle(
                loomEulerAngle.getPitch(),
                loomEulerAngle.getYaw(),
                loomEulerAngle.getRoll()
        );
    }

    public static org.loomdev.api.math.EulerAngle toLoom(@NotNull net.minecraft.util.math.EulerAngle mcEulerAngle) {
        return EulerAngle.of(
                mcEulerAngle.getPitch(),
                mcEulerAngle.getYaw(),
                mcEulerAngle.getRoll()
        );
    }
}
