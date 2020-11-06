package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.WitherSkull;

public class WitherSkullImpl extends ProjectileImpl implements WitherSkull {

    public WitherSkullImpl(net.minecraft.world.entity.projectile.WitherSkull entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<WitherSkull> getType() {
        return EntityType.WITHER_SKULL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.WitherSkull getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.WitherSkull) super.getMinecraftEntity();
    }

    @Override
    public boolean isCharged() {
        return getMinecraftEntity().isDangerous();
    }

    @Override
    public void setCharged(boolean flag) {
        getMinecraftEntity().setDangerous(flag);
    }
}
