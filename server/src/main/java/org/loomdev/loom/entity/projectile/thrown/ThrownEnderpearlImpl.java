package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.Enderpearl;

public class ThrownEnderpearlImpl extends AbstractThrowableItemImpl implements Enderpearl {

    public ThrownEnderpearlImpl(ThrownEnderpearl entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Enderpearl> getType() {
        return EntityType.ENDER_PEARL;
    }

    @Override
    @NotNull
    public ThrownEnderpearl getMinecraftEntity() {
        return (ThrownEnderpearl) super.getMinecraftEntity();
    }
}
