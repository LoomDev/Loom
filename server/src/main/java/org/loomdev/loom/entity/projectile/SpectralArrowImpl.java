package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.SpectralArrow;

public class SpectralArrowImpl extends AbstractArrowImpl implements SpectralArrow {

    public SpectralArrowImpl(net.minecraft.world.entity.projectile.SpectralArrow entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<SpectralArrow> getType() {
        return EntityType.SPECTRAL_ARROW;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.SpectralArrow getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.SpectralArrow) super.getMinecraftEntity();
    }

    @Override
    public int getGlowingDuration() {
        return getMinecraftEntity().duration;
    }

    @Override
    public void setGlowingDuration(int ticks) {
        getMinecraftEntity().duration = ticks;
    }
}
