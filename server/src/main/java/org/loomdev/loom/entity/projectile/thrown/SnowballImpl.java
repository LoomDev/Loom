package org.loomdev.loom.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.Snowball;

public class SnowballImpl extends AbstractThrowableItemImpl implements Snowball {

    public SnowballImpl(net.minecraft.world.entity.projectile.Snowball entity) {
        super(entity);
    }

    @Override
    public EntityType<Snowball> getType() {
        return EntityType.SNOWBALL;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.Snowball getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.Snowball) super.getMinecraftEntity();
    }
}
