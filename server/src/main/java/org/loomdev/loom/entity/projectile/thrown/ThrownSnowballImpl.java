package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.ThrownSnowball;

public class ThrownSnowballImpl extends AbstractThrowableItemImpl implements ThrownSnowball {

    public ThrownSnowballImpl(net.minecraft.world.entity.projectile.Snowball entity) {
        super(entity);
    }

    @Override
    public EntityType<ThrownSnowball> getType() {
        return EntityType.SNOWBALL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.Snowball getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.Snowball) super.getMinecraftEntity();
    }
}
