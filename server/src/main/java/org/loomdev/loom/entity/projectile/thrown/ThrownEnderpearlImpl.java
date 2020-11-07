package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ThrownEnderpearl;

public class ThrownEnderpearlImpl extends AbstractThrowableItemImpl implements ThrownEnderpearl {

    public ThrownEnderpearlImpl(net.minecraft.world.entity.projectile.ThrownEnderpearl entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ThrownEnderpearl> getType() {
        return EntityType.ENDER_PEARL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.ThrownEnderpearl getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.ThrownEnderpearl) super.getMinecraftEntity();
    }
}
