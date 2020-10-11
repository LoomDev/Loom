package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.SpectralArrowEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.SpectralArrow;

public class SpectralArrowImpl extends PersistentProjectileImpl implements SpectralArrow {

    public SpectralArrowImpl(SpectralArrowEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SPECTRAL_ARROW;
    }

    @Override
    public @NotNull SpectralArrowEntity getMinecraftEntity() {
        return (SpectralArrowEntity) super.getMinecraftEntity();
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
