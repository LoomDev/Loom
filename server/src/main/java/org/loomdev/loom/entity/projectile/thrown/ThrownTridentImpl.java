package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ThrownTrident;
import org.loomdev.loom.entity.projectile.AbstractArrowImpl;

public class ThrownTridentImpl extends AbstractArrowImpl implements ThrownTrident {

    public ThrownTridentImpl(net.minecraft.world.entity.projectile.ThrownTrident entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ThrownTrident> getType() {
        return EntityType.TRIDENT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.ThrownTrident getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.ThrownTrident) super.getMinecraftEntity();
    }
}
