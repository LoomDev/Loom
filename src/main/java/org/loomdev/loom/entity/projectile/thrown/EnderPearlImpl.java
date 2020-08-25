package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.EnderPearl;

public class EnderPearlImpl extends ThrownItemImpl implements EnderPearl {

    public EnderPearlImpl(EnderPearlEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDER_PEARL;
    }

    @Override
    public @NotNull EnderPearlEntity getMinecraftEntity() {
        return (EnderPearlEntity) super.getMinecraftEntity();
    }
}
