package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ThrownEgg;

public class ThrownEggImpl extends AbstractThrowableItemImpl implements ThrownEgg {

    public ThrownEggImpl(net.minecraft.world.entity.projectile.ThrownEgg entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ThrownEgg> getType() {
        return EntityType.EGG;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.ThrownEgg getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.ThrownEgg) super.getMinecraftEntity();
    }
}
