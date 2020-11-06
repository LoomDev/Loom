package org.loomdev.loom.util.transformer;

import net.minecraft.core.Rotations;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.math.EulerAngle;

public final class EulerAngleTransformer {

    private EulerAngleTransformer() {
    }

    @NotNull
    public static Rotations toMinecraft(@NotNull EulerAngle loomEulerAngle) {
        return new Rotations(
                loomEulerAngle.getPitch(),
                loomEulerAngle.getYaw(),
                loomEulerAngle.getRoll()
        );
    }

    @NotNull
    public static org.loomdev.api.math.EulerAngle toLoom(@NotNull Rotations mcEulerAngle) {
        return EulerAngle.of(
                mcEulerAngle.getX(),
                mcEulerAngle.getY(),
                mcEulerAngle.getZ()
        );
    }
}
