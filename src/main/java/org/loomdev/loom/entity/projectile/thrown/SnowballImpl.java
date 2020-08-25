package org.loomdev.loom.entity.projectile.thrown;

import net.minecraft.entity.projectile.thrown.SnowballEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.thrown.Snowball;

public class SnowballImpl extends ThrownItemImpl implements Snowball {

    public SnowballImpl(SnowballEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SNOWBALL;
    }

    @Override
    public @NotNull SnowballEntity getMinecraftEntity() {
        return (SnowballEntity) super.getMinecraftEntity();
    }

}
