package org.loomdev.loom.transformer;

import net.minecraft.core.Rotations;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.math.EulerAngle;

public final class EulerAngleTransformer implements Transformer<Rotations, EulerAngle> {

    protected EulerAngleTransformer() {
    }

    @Override
    @NotNull
    public Rotations toMinecraft(@NotNull EulerAngle eulerAngle) {
        return new Rotations(eulerAngle.getPitch(), eulerAngle.getYaw(), eulerAngle.getRoll());
    }

    @Override
    @NotNull
    public EulerAngle toLoom(@NotNull Rotations rotations) {
        return new EulerAngle(rotations.getX(), rotations.getY(), rotations.getZ());
    }
}
