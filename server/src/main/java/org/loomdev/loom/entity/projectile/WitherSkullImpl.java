package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.WitherSkullEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.WitherSkull;

public class WitherSkullImpl extends ProjectileImpl implements WitherSkull {

    public WitherSkullImpl(WitherSkullEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<WitherSkull> getType() {
        return EntityType.WITHER_SKULL;
    }

    @Override
    public @NotNull WitherSkullEntity getMinecraftEntity() {
        return (WitherSkullEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isCharged() {
        return getMinecraftEntity().isCharged();
    }

    @Override
    public void setCharged(boolean flag) {
        getMinecraftEntity().setCharged(flag);
    }
}
