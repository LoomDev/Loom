package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.FishingBobberEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.FishingBobber;

public class FishingBobberImpl extends ProjectileImpl implements FishingBobber {

    public FishingBobberImpl(FishingBobberEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<FishingBobber> getType() {
        return EntityType.FISHING_BOBBER;
    }

    @Override
    public @NotNull FishingBobberEntity getMinecraftEntity() {
        return (FishingBobberEntity) super.getMinecraftEntity();
    }

}
