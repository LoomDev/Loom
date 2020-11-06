package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.FishingHook;

public class FishingHookImpl extends ProjectileImpl implements FishingHook {

    public FishingHookImpl(net.minecraft.world.entity.projectile.FishingHook entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<FishingHook> getType() {
        return EntityType.FISHING_BOBBER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.FishingHook getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.FishingHook) super.getMinecraftEntity();
    }
}
